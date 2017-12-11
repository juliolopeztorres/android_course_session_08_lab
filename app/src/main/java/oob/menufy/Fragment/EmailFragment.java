package oob.menufy.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import oob.menufy.R;

public class EmailFragment extends Fragment {

    private TextView textViewEmail;
    private FloatingActionButton fabEmail;

    public EmailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_email, container, false);

        this.textViewEmail = view.findViewById(R.id.textViewEmail);
        this.fabEmail = view.findViewById(R.id.fabEmail);
        this.fabEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buildDialog();
            }
        });

        return view;
    }

    private void buildDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_email, null);
        final EditText editText = dialogView.findViewById(R.id.editTextDialogEmail);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView);
        builder.setPositiveButton(getString(R.string.dialog_email_positive_label), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String email = editText.getEditableText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getActivity(), getString(R.string.dialog_email_empty_warning), Toast.LENGTH_SHORT).show();
                    return;
                }
                textViewEmail.setText(email);
            }
        }).setNegativeButton(getString(R.string.dialog_email_negative_label), null);
        builder.create().show();
    }
}
