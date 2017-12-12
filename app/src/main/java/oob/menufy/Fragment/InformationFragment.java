package oob.menufy.Fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import oob.menufy.R;

public class InformationFragment extends Fragment {

    private FloatingActionButton fabInformation;

    public InformationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);

        this.fabInformation = view.findViewById(R.id.fabInformation);
        this.fabInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buildDialog();
            }
        });

        return view;
    }

    private void buildDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_information, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView);
        builder.setNeutralButton(getString(R.string.dialog_information_neutral_label), null);
        builder.create().show();
    }
}
