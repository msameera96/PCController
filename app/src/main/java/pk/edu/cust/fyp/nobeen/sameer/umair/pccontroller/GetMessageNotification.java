package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by sameer on 04-Jan-18.
 */

public class GetMessageNotification extends GetMessageFromServer {
    Context context;
    GetMessageNotification (Context context)
    {
        this.context = context;
    }
    @Override
    public void callBackString(String s) {

            Toast.makeText(context, " "+s, Toast.LENGTH_LONG).show();
            if(s.matches("Login_Successfully"))
            {
                Intent intent;
                intent = new Intent(context,MainMenu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }

    }



}
