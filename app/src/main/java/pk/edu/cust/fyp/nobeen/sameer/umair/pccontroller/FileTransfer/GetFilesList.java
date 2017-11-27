package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller.FileTransfer;

/**
 * Created by sameer on 21-Nov-17.
 */

import android.content.Context;
import android.widget.ListView;

import java.util.ArrayList;

import file.AvatarFile;
import pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller.AvatarFileAdapter;
import pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller.R;

class GetFilesList extends FilesList {
    ListView fileTransferListView;
    Context context;
    GetFilesList(ListView fileTransferListView, Context context) {
        this.fileTransferListView = fileTransferListView;
        this.context = context;
    }
    @Override
    public void receiveData(Object result) {
        ArrayList<AvatarFile> filesInFolder = (ArrayList<AvatarFile>) result;
        fileTransferListView.setAdapter(new AvatarFileAdapter(context,
                R.layout.activity_music_image_avatar, filesInFolder));

    }

}
