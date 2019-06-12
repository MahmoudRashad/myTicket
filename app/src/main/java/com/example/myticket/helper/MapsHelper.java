package com.example.myticket.helper;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

import com.example.myticket.Enum.Finals;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MapsHelper {
    private Context context;


    public MapsHelper(Context context) {
        this.context = context;
    }

    public boolean isServicesOk(Activity activity){
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context);
        if (available == ConnectionResult.SUCCESS){
            return true;
        }
        Finals finals = new Finals();
        if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(activity,available, finals.getERROR_DIALOG_REQUEST() );
            dialog.show();
        }
        else {
            Toast.makeText(context,"Please make sure you have the latest google play services installed on your device",Toast.LENGTH_LONG).show();
        }
        return false;
    }


}
