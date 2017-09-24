package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller;

/**
 * Created by Sameer on 5/23/2017.
 */

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

public class Client extends AsyncTask<Void, Void, Void> {

    String dstAddress;
    int dstPort;
    String response = "";
    Context context;
    boolean isConnect = false;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String con;

  //  TextView textResponse;

    Client(Context context, String addr, int port) {
        dstAddress = addr;
        dstPort = port;
        this.context = context;
        Toast.makeText(context.getApplicationContext(),"IP = "+dstAddress+"\nPort = "+dstPort,Toast.LENGTH_SHORT).show();
       // this.textResponse=textResponse;
    }

    @Override
    protected Void doInBackground(Void... arg0) {

        Socket socket = null;

        try {
            socket = new Socket(dstAddress, dstPort);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            con=dataInputStream.readUTF();
            boolean isCon=socket.isConnected();
            isConnect = isCon;
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "UnknownHostException: " + e.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "IOException: " + e.toString();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        //textResponse.setText(response);
        Toast.makeText(context.getApplicationContext()," "+response,Toast.LENGTH_SHORT).show();
        Toast.makeText(context.getApplicationContext()," Con = "+con,Toast.LENGTH_SHORT).show();
        super.onPostExecute(result);
    }
    boolean isConnectMeth()
    {
        return isConnect;
    }

}
