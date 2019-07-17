package sg.edu.np.g69.madassignment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends Main6Activity {
    ImageView imageView;
    SeekBar sb;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup root = findViewById(R.id.drawer_layout);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_main, root, false);
        drawer.addView(contentView, 0);


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
