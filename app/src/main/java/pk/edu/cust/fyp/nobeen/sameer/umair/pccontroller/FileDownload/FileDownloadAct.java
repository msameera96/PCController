package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller.FileDownload;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Stack;

import file.AvatarFile;
import pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller.FileTransfer.FileTransferAct;
import pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller.MainActivity;

import pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller.R;
import pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller.SocketHandler;

public class FileDownloadAct extends AppCompatActivity implements View.OnClickListener {
    private Button backButton;
    private TextView pathTextView;
    private ListView fileDownloadListView;
    private Stack<String> pathStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_download);

        backButton = (Button) findViewById(R.id.backButton);
        pathTextView = (TextView) findViewById(R.id.pathTextView);
        fileDownloadListView = (ListView) findViewById(R.id.fileTransferListView);
        pathStack = new Stack<String>();
        pathStack.push("/");
        pathTextView.setText(pathStack.peek());
        backButton.setEnabled(false);
        backButton.setOnClickListener(this);
        getFiles();
        fileDownloadListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                AvatarFile file = (AvatarFile) parent.getItemAtPosition(position);
                String path = file.getPath();
                if (file.getType().equals("folder")) {
                    pathStack.push(path);
                    String currentPath = pathStack.peek();
                    pathTextView.setText(currentPath);
                    backButton.setEnabled(true);
                    getFiles();
                } else {
                    //Toast.makeText(getActivity(), "Downloading " + file.getHeading(), Toast.LENGTH_LONG).show();
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                        downloadFile(file.getHeading(), file.getPath());
                    } else {
                        checkForPermissionAndDownload(file.getHeading(), file.getPath());
                    }
                }
            }

        });

    }
    private void getFiles() {
        String message = "FILE_DOWNLOAD_LIST_FILES";
        MainActivity.sendMessageToServer(message);
        message = pathStack.peek();
        //Toast.makeText(getApplicationContext()," "+message,Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(),"Socket "+SocketHandler.getSocket(),Toast.LENGTH_SHORT).show();
        MainActivity.sendMessageToServer(message);
        new GetFilesList(fileDownloadListView, getApplicationContext()).execute(pathStack.peek());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.backButton) {
            pathStack.pop();
            String currentPath = pathStack.peek();
            getFiles();
            pathTextView.setText(currentPath);
            if (! currentPath.equals("/")) {
            } else {
                backButton.setEnabled(false);
            }
        }

    }
    @TargetApi(Build.VERSION_CODES.M)
   private void checkForPermissionAndDownload(String name, String path) {
        Toast.makeText(getApplicationContext(), "Permission ", Toast.LENGTH_LONG).show();
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (this.shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(getApplicationContext(), "Write Permission is necessary for downloading", Toast.LENGTH_LONG).show();
            } else {
                this.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                //2 is integer constant for WRITE_EXTERNAL_STORAGE permission, uses in onRequestPermissionResult
            }
        } else {
            downloadFile(name, path);
        }
    }


    protected void downloadFile(String name, String path) {

        if (SocketHandler.getSocket() != null){
            MainActivity.sendMessageToServer("FILE_DOWNLOAD_REQUEST");
        MainActivity.sendMessageToServer(path);
        new DownloadFileFromServer(FileDownloadAct.this).execute(name);
        }else{
            Toast.makeText(getApplicationContext(), "File didn't download", Toast.LENGTH_LONG).show();
        }



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MainActivity.sendMessageToServer("log_ended");

    }

}
