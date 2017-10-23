package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller;

import java.net.Socket;

/**
 * Created by sameer on 10-Oct-17.
 */

public class Conection {
    boolean connectionEstablishing( String ipAddr,int p)
    {
        final String ipAddress = ipAddr;
        final int port = p;

        Thread conThread =new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {

                    //  Log.i(TAG, "Attempting to connect to server");
                    MainActivity.socket = new Socket(ipAddress, port);
                    // Log.i(TAG, "Connection established");
                    SocketHandler.setSocket(MainActivity.socket);
                    // Log.i(TAG,"Socket is Connected: "+MainMenu.socket.isConnected());



                } catch (Exception ex)

                {
                    ex.printStackTrace();
                    //Toast.makeText(getApplicationContext(), "Exception occur" + ex, Toast.LENGTH_SHORT).show();
                }

            }
        });
        conThread.start();
       // if(ConnectionStatus.getConnectionStatus())
        return true;
       // else
           // return false;
    }
}
