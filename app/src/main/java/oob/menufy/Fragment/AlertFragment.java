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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import oob.menufy.R;

public class AlertFragment extends Fragment {

    private TextView textViewAlert;
    private FloatingActionButton fabAlert;

    public AlertFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alert, container, false);

        this.textViewAlert = view.findViewById(R.id.textViewAlert);
        this.fabAlert = view.findViewById(R.id.fabAlert);
        this.fabAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buildDialog();
            }
        });

        return view;
    }

    private void buildDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_alert, null);
        final Switch switchAlert = dialogView.findViewById(R.id.switchAlert);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView);
        builder.setPositiveButton(getString(R.string.dialog_alert_positive_label), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                textViewAlert.setText(
                    getString(R.string.alert_label_with_format, switchAlert.isChecked() ? "Enabled" : "Disabled")
                );
            }
        }).setNegativeButton(getString(R.string.dialog_alert_negative_label), null);
        builder.create().show();
    }
}
