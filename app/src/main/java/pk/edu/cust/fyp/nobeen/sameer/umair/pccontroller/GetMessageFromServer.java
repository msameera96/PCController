package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller;

import android.os.AsyncTask;

import java.io.ObjectInputStream;

/**
 * Created by sameer on 04-Jan-18.
 */

public abstract class GetMessageFromServer  extends AsyncTask<String, Void, String> implements CallBackString{

   public abstract void callBackString(String s);
    @Override
    protected String doInBackground(String... strings) {

        String  message_from_server ="";
        try {
            if (MainActivity.objectInputStream == null) {
                MainActivity.objectInputStream = new ObjectInputStream(SocketHandler.getSocket().getInputStream());
            }
            message_from_server = (String) MainActivity.objectInputStream.readObject();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return message_from_server;
    }

    @Override
    protected void onPostExecute(String s) {
        callBackString(s);

    }


}
