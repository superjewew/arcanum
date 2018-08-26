package com.mahavira.arcanum.friends.presentation;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import com.mahavira.arcanum.friends.R;

/**
 * Created by norman on 26/08/18.
 */

public class AddFriendDialogFragment extends DialogFragment {

    private Button mAddButton;

    private EditText mNameEt;

    public AddFriendDialogFragment() {}

    public static AddFriendDialogFragment newInstance() {
        return new AddFriendDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
            final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add_friend, container);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAddButton = view.findViewById(R.id.add_btn);
        mNameEt = view.findViewById(R.id.name_friend_et);

        getDialog().setTitle("Add Friend");

        mNameEt.requestFocus();
        getDialog().getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        mAddButton.setOnClickListener(v -> sendBackResult());
    }

    private void sendBackResult() {
        NameDialogListener listener = (NameDialogListener) getTargetFragment();
        listener.onAdd(mNameEt.getText().toString());
        dismiss();
    }

    public interface NameDialogListener {
        void onAdd(String input);
    }
}
