package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller.FileTransfer.FileTransferAct;

public class MainMenu extends AppCompatActivity implements AdapterView.OnItemClickListener {
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        gridView = (GridView) findViewById(R.id.grdView);
        gridView.setAdapter(new MainMenuAdapter(this));
        gridView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        Intent intent;
                switch (position)
                {
                    case 0:
                        Toast.makeText(getApplicationContext(), "CMD", Toast.LENGTH_SHORT).show();
                        intent = new Intent(this,CMD.class);
                        startActivity(intent);

                        break;


                    case 1:
                        //Toast.makeText(getApplicationContext(), "File Transfer", Toast.LENGTH_SHORT).show();
                        DialogFragmentController dialogFragmentController = new DialogFragmentController();
                        dialogFragmentController.customActivity(this);
                        dialogFragmentController.show(getFragmentManager(),"MyDialog");


                        break;

                    case 2:
                        Toast.makeText(getApplicationContext(), "Remote Keyboard", Toast.LENGTH_SHORT).show();

                        intent = new Intent(this, Keyboard.class);
                        startActivity(intent);
                        break;


                    case 3:
                        Toast.makeText(getApplicationContext(), "Remote Mouse", Toast.LENGTH_SHORT).show();

                        intent = new Intent(this, Mouse.class);
                        startActivity(intent);
                        break;


                    case 4:
                        Toast.makeText(getApplicationContext(), "Power Control", Toast.LENGTH_SHORT).show();

                        intent = new Intent(this, PowerControl.class);
                        startActivity(intent);
                        break;


                    case 5:
                        Toast.makeText(getApplicationContext(), "Power Point", Toast.LENGTH_SHORT).show();

                        intent = new Intent(this, PowerPoint.class);
                        startActivity(intent);
                        break;


                    case 6:
                        Toast.makeText(getApplicationContext(), "Remote Desktop", Toast.LENGTH_SHORT).show();

                        intent = new Intent(this,LiveScreen.class);
                        startActivity(intent);
                        break;


                    case 7:
                        Toast.makeText(getApplicationContext(), "Volume Control", Toast.LENGTH_SHORT).show();
                        intent = new Intent(this,VolumeActivity.class);
                        startActivity(intent);

                        break;


                    case 8:
                        Toast.makeText(getApplicationContext(), "Microsoft Word", Toast.LENGTH_SHORT).show();
                        intent = new Intent(this,Word.class);
                        startActivity(intent);

                        break;

                }


    }




class ActivityIcon
{
    int images;
    String iconName;
    ActivityIcon(int images, String iconName)
    {
        this.images = images;
        this.iconName = iconName;
    }
}
class ViewHolder
        {
            ImageView actIcons;
            TextView iconLabelTxt;
            ViewHolder (View v)
            {
                actIcons = (ImageView) v.findViewById(R.id.imageView);
                iconLabelTxt = (TextView) v.findViewById(R.id.itemTextView);


            }
        }
class MainMenuAdapter extends BaseAdapter {

    Context context;
    ArrayList<ActivityIcon> list;

    MainMenuAdapter(Context context) {
        this.context = context;
        int noOfIcons = 9;
        list = new ArrayList<ActivityIcon>();
        Resources res = context.getResources();
        String[] tempIconName = res.getStringArray(R.array.remote_activities);
        int[] activitiesThumbnails = {R.drawable.cmd, R.drawable.filetransfer, R.drawable.keyboard, R.drawable.mouse, R.drawable.power, R.drawable.powerpoint, R.drawable.desktop, R.drawable.volume, R.drawable.word};
        for (int i = 0; i < noOfIcons; i++) {
            ActivityIcon activityIcon = new ActivityIcon(activitiesThumbnails[i], tempIconName[i]);
            list.add(activityIcon);
        }

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_item, parent, false);
            holder = new ViewHolder(row);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();

        }
        ActivityIcon icn = list.get(position);
        holder.actIcons.setImageResource(icn.images);
        holder.iconLabelTxt.setText(icn.iconName);

        return row;
    }

}}
