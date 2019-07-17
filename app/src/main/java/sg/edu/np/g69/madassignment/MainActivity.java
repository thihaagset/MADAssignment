package sg.edu.np.g69.madassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    SeekBar sb;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sb = findViewById(R.id.seekBarTime);
        tv = findViewById(R.id.timeTV);
        sb.setProgress(0);
        sb.incrementProgressBy(10);
        sb.setMax(90);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int ps = progress+30;
                String progressString = String.valueOf(ps);
                tv.setText(progressString);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.e("------------", "Start！");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.e("------------", "Stop！");
            }
        });

    }



    public void onClickBuild(View v){
        Intent i = new Intent(MainActivity.this,Main2Activity.class);
        i.putExtra("SeekBar",tv.getText());
        startActivity(i);
    }

    public void onclick(View v2){
        Intent intent = new Intent(this,Main4Activity.class);
        startActivity(intent);
    }
}
