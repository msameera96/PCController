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
                    MainActivity.clientsocket = new Socket(ipAddress, port);
                    SocketHandler.setSocket(MainActivity.clientsocket);
                    OutputStreamSocketInitializer ossi;
                    ossi = new OutputStreamSocketInitializer();
                    ossi.setSocketToOOS();

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
