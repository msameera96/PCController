package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller.FileDownload;

import android.content.Context;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.logging.SocketHandler;

import file.AvatarFile;
import pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller.AvatarFileAdapter;
import pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller.R;
/**
 * Created by sameer on 11-Dec-17.
 */

class GetFilesList extends GetFilesListFromServer {
    ListView fileDownloadListView;
    Context context;
    GetFilesList(ListView fileDownloadListView, Context context) {
        this.fileDownloadListView = fileDownloadListView;
        this.context = context;

        //Toast.makeText(context, "Socket "+ pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller.SocketHandler.getSocket(), Toast.LENGTH_LONG).show();
    }
    @Override
    public void receiveData(Object result) {
        ArrayList<AvatarFile> filesInFolder = (ArrayList<AvatarFile>) result;
        if (filesInFolder != null) {
            fileDownloadListView.setAdapter(new AvatarFileAdapter(context,
                    R.layout.activity_music_image_avatar, filesInFolder));
        } else {
            Toast.makeText(context, "Folder List is not recieving from PC", Toast.LENGTH_LONG).show();
        }

    }

}
