package com.mahavira.arcanum.store.presentation.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.mahavira.arcanum.store.BR;
import com.mahavira.arcanum.store.R;
import com.mahavira.arcanum.store.databinding.ActivityStoreDetailBinding;
import com.mahavira.arcanum.store.domain.entity.EncryptedString;
import com.mahavira.arcanum.store.domain.entity.Store;
import com.mahavira.arcanum.store.domain.entity.VisitNotificationManager;
import com.mahavira.arcanum.store.presentation.StoreRouter;
import com.mahavira.base.presentation.BaseActivity;
import com.mahavira.base.presentation.ExtraInjectable;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.inject.Inject;
import org.parceler.Parcels;

public class StoreDetailActivity extends BaseActivity<ActivityStoreDetailBinding, StoreDetailViewModel> implements
        ExtraInjectable {

    public static final String STORE_EXTRA = "store";

    private StoreDetailAdapter mAdapter;

    private Store mStore;

    @Inject
    FirebaseAuth mFirebaseAuth;

    @Inject
    StoreRouter mStoreRouter;

    @Inject
    VisitNotificationManager mManager;

    private String mStoreEmail;

    private String mUserEmail;

    @Override
    public int getViewModelBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_store_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getDataBinding().setStore(mStore);

        setupAdapter();
        setupRecyclerView(mAdapter);

        updateAvailableGames();

        getViewModel().getPlayHereClickedEvent().observe(this, __ -> new IntentIntegrator(this).initiateScan());

        getViewModel().getProductClickedEvent()
                .observe(this, productName -> mStoreRouter.goToProductDetail(this, productName));

        getViewModel().getSetVisitResult().observe(this, result -> {
            if (result != null) {
                switch (result.status) {
                    case SUCCESS: {
                        mManager.setupRepeatingAlarm(mUserEmail);
                        break;
                    }
                }
            }
        });

    }

    private void updateAvailableGames() {
        mAdapter.addData(mStore.getBorrowedGames());
    }

    private void setupAdapter() {
        mAdapter = new StoreDetailAdapter(this, getViewModel());
    }

    private void setupRecyclerView(final StoreDetailAdapter adapter) {
        getDataBinding().storeGameList.setLayoutManager(new GridLayoutManager(this, 3));
        getDataBinding().storeGameList.setAdapter(adapter);
    }

    @Override
    public void injectExtras(@NonNull final Bundle extras) {
        if (extras.containsKey(STORE_EXTRA)) {
            mStore = Parcels.unwrap(extras.getParcelable(STORE_EXTRA));
        }
    }

    // Get the results:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                FirebaseUser user = mFirebaseAuth.getCurrentUser();

                EncryptedString s = new EncryptedString(result.getContents());
                try {
                    mStoreEmail = s.decryptMsg().getValue();
                    mUserEmail = user.getEmail();
                    getViewModel().attemptSetVisit(mUserEmail, mStoreEmail);
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
