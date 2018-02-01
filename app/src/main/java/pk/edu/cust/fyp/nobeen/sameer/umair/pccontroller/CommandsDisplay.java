package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by sameer on 01-Feb-18.
 */

public class CommandsDisplay extends GetMessageFromServer {
    Context context;
    CommandsDisplay(Context context){
        this.context = context;
    }


    @Override
    public void callBackString(String s) {
        //Toast.makeText(context,""+s,Toast.LENGTH_SHORT).show();
        //CMD.cmdOutputET.setText();
        CMD.cmdOutputTV.setText(s);
    }
}
