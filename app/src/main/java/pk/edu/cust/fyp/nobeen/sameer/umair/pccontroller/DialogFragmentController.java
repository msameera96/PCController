package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller.FileDownload.FileDownloadAct;
import pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller.FileTransfer.FileTransferAct;

/**
 * Created by sameer on 20-Nov-17.
 */

public class DialogFragmentController extends DialogFragment {
    Activity activity;
     public void customActivity(Activity act)
     {
         activity = act;
     }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.data_transfer)
                .setItems(R.array.data, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        if(which ==0)
                        {
                            MainActivity.sendMessageToServer("log_started");
                            MainActivity.sendMessageToServer("File Transfer");
                            Toast.makeText(activity.getApplicationContext(),"File Transfer", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(activity,FileTransferAct.class);
                            activity.startActivity(intent);
                        }
                        else if(which ==1)
                        {
                            MainActivity.sendMessageToServer("log_started");
                            MainActivity.sendMessageToServer("File Download");
                            Toast.makeText(activity.getApplicationContext(), "File Download", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(activity,FileDownloadAct.class);
                            activity.startActivity(intent);
                        }
                        dialog.dismiss();

                    }
                });
        return builder.create();
    }
}
