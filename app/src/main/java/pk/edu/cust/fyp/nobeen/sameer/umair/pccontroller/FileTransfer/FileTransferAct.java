package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller.FileTransfer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.net.Socket;

import file.AvatarFile;
import pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller.FileEnvironment;
import pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller.R;
import pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller.MainActivity;
import pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller.SocketHandler;

public class FileTransferAct extends AppCompatActivity implements View.OnClickListener {

    private Button backButton;
    private TextView pathTextView;
    private ListView fileTransferListView;
    private File currentDirectory;
    private String currentPath, rootPath;

    public FileTransferAct() {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_transfer);

        backButton = (Button) findViewById(R.id.backButton);
        pathTextView = (TextView) findViewById(R.id.pathTextView);
        fileTransferListView = (ListView) findViewById(R.id.fileTransferListView);
        currentPath = new FileEnvironment().getExternalStoragePath();
        rootPath = currentPath;
        currentDirectory = new File(currentPath);
        backButton.setEnabled(false);
        backButton.setOnClickListener(this);
        new GetFilesList(fileTransferListView, getApplicationContext()).execute(currentPath);

        fileTransferListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                AvatarFile file = (AvatarFile) parent.getItemAtPosition(position);
                String path = file.getPath();
                File tempDirectoryOrFile = new File(path);
                if (tempDirectoryOrFile.isDirectory()) {
                    File tempArray[] = tempDirectoryOrFile.listFiles();
                    //to avoid crash when 0 item
                    if (tempArray != null && tempArray.length > 0) {
                        backButton.setEnabled(true);
                        currentPath = path;
                        currentDirectory = tempDirectoryOrFile;
                        pathTextView.setText(currentPath);
                        new GetFilesList(fileTransferListView, getApplicationContext()).execute(currentPath);
                    }
                } else {
                    transferFile(file.getHeading(), file.getPath());
                }
            }

        });
        pathTextView.setText(currentPath);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.backButton) {
            currentPath = currentDirectory.getParent();
            currentDirectory = new File(currentPath);
            new GetFilesList(fileTransferListView, getApplicationContext()).execute(currentPath);
            pathTextView.setText(currentPath);
            if (! currentPath.equals(rootPath)) {
            } else {
                backButton.setEnabled(false);
            }
        }
    }

    private void transferFile(String name, String path) {
        Socket clientSocket = SocketHandler.getSocket();
        if (clientSocket == null) {
            Toast.makeText(getApplicationContext(), "Not Connected", Toast.LENGTH_LONG).show();
        } else {

            MainActivity.sendMessageToServer("FILE_TRANSFER_REQUEST");
            MainActivity.sendMessageToServer(name);
            new TransferFileToServer(FileTransferAct.this){
                @Override
                public void receiveData(Object result) {
                }
            }.execute(new String[]{name, path});
        }
    }

}
