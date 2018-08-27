package com.mahavira.arcanum.friends.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import com.mahavira.arcanum.friends.R;

/**
 * Created by norman on 26/08/18.
 *
 */

public class AddFriendDialogFragment extends DialogFragment {

    private Button mAddButton;

    private Button mCancelButton;

    private EditText mNameEt;

    public AddFriendDialogFragment() {}

    public static AddFriendDialogFragment newInstance() {
        return new AddFriendDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container,
            final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add_friend, container);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAddButton = view.findViewById(R.id.add_btn);
        mCancelButton = view.findViewById(R.id.cancel_btn);
        mNameEt = view.findViewById(R.id.name_friend_et);

        getDialog().setTitle("Add Friend");

        mNameEt.requestFocus();
        getDialog().getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        mAddButton.setOnClickListener(v -> sendBackResult());
        mCancelButton.setOnClickListener(v -> dismiss());
    }

    private void sendBackResult() {
        NameDialogListener listener = (NameDialogListener) getTargetFragment();
        if (listener != null) {
            listener.onAdd(mNameEt.getText().toString());
        }
        dismiss();
    }

    public interface NameDialogListener {
        void onAdd(String input);
    }
}
