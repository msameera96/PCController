package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller.FileDownload;

import android.os.AsyncTask;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import file.AvatarFile;
import pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller.CallBackReceiver;
import pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller.MainActivity;
import pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller.SocketHandler;

/**
 * Created by sameer on 11-Dec-17.
 */

public abstract class GetFilesListFromServer extends AsyncTask<String, Void, ArrayList<AvatarFile>> implements CallBackReceiver {

    public abstract void receiveData(Object result);

    @Override
    protected ArrayList<AvatarFile> doInBackground(String... params) {
        String path = params[0];
        ArrayList<AvatarFile> myFiles = null;
        try {

            //if (SocketHandler.getSocket() != null) {
                if (MainActivity.objectInputStream == null) {
                    MainActivity.objectInputStream = new ObjectInputStream(SocketHandler.getSocket().getInputStream());
                }
                myFiles = (ArrayList<AvatarFile>) MainActivity.objectInputStream.readObject();
            //}
        } catch(Exception e) {
            e.printStackTrace();
        }
        return myFiles;
    }

    protected void onPostExecute(ArrayList<AvatarFile> myFiles) {
        receiveData(myFiles);
    }

}
