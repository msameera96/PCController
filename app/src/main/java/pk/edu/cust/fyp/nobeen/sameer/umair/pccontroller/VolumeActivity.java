package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.AudioManager;
import android.content.Context;
import android.widget.TextView;
import com.xw.repo.BubbleSeekBar;

public class VolumeActivity extends AppCompatActivity {
    TextView text;
    public BubbleSeekBar seek = null;
    public AudioManager mgr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        seekbarr();
    }

    public void seekbarr() {



            //text.setText("Volume :" + seek.getProgress() + "/" + seek.getMax());

            seek = (BubbleSeekBar) findViewById(R.id.seekBar);

            seek.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
                int progress_value;

                @Override
                public void onProgressChanged(int progress, float progressFloat) {
                    //text.setText(String.format("Volume ", progress));





                }

                @Override
                public void getProgressOnActionUp(int progress, float progressFloat) {
                    MainActivity.sendMessageToServer("VolumeController");
                    MainActivity.sendMessageToServer(progress);

                }

                @Override
                public void getProgressOnFinally(int progress, float progressFloat) {

                   // text.setText("Volume :" + progress_value + "/" + seek.getMax());


                }
            });
        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MainActivity.sendMessageToServer("log_ended");

    }


}



