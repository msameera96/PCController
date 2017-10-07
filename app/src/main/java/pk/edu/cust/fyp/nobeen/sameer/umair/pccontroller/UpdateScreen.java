package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller;



import android.os.AsyncTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Created by sameer on 05-Oct-17.
 */

public abstract class UpdateScreen extends AsyncTask<Void, Void, String> implements CallBackReceiver{

    @Override
    protected String doInBackground(Void... voids) {
        FileOutputStream fos = null;
        Socket clientSocket = SocketHandler.getSocket();
        String path = new FileEnvironment().getExternalStoragePath();
        path = path + "/RemoteControlPC/screenshot.png";
        //System.out.println("Screenshot url: " + path);
        File file = new File(path);
        File dirs = new File(file.getParent());
        if (!dirs.exists()) {
            dirs.mkdirs();
        }

        try {
            if (clientSocket != null) {
                if (MainMenu.objectInputStream == null) {
                    MainMenu.objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                }
                fos = new FileOutputStream(file);
                byte buffer[] = new byte[4096];
                int fileSize = (int) MainMenu.objectInputStream.readObject();
                int read = 0;
                int remaining = fileSize;
                while ((read = MainMenu.objectInputStream.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
                    remaining -= read;
                    fos.write(buffer, 0, read);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return path;
    }

    protected void onPostExecute(String path) {
        receiveData(path);
    }
}
