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
                    MainActivity.sendMessageToServer(progressFloat);

                }

                @Override
                public void getProgressOnFinally(int progress, float progressFloat) {

                   // text.setText("Volume :" + progress_value + "/" + seek.getMax());


                }
            });
        }


}


       /* seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_value=progress;

                text.setText("Volume :"+progress+"/"+seek.getMax());
                Toast.makeText(MainActivity.this,"sek bar is in progress",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this,"seekbar is in progress ",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                text.setText("Volume :"+progress_value+"/"+seek.getMax());
                Toast.makeText(MainActivity.this,"stop",Toast.LENGTH_LONG).show();

            }
        });
        }

   protected  void onResume(){
        super.onResume();
        mediaPlayer.start();;

    }
    protected  void onPause()
    {
        super.onPause();
        mediaPlayer.stop();
    }
    protected  void onDestroy()
    {
        super.onDestroy();
        mediaPlayer.release();
        handler.removeCallbacks(runnable);
    }
*/


