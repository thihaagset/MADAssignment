package sg.edu.np.g69.madassignment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends MainNavDrawer {
    ImageView imageView;
    SeekBar sb;
    TextView tv;
    Intent i;
    String useruid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup root = findViewById(R.id.drawer_layout);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_main, root, false);
        drawer.addView(contentView, 0);
        //code for firebase
        i = getIntent();
        useruid = i.getStringExtra("useruid");
        //Firebase codes end here


        sb = findViewById(R.id.seekBarTime);
        tv = findViewById(R.id.testTV);
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
        Intent i = new Intent(MainActivity.this, CountDownActivity.class);
        i.putExtra("SeekBar",tv.getText());
        //The following code is just for firebase :)
        i.putExtra("useruid",useruid);
        //Firebase code ends here :(
        startActivity(i);

    }

    public void onclick(View v2){
        Intent intent = new Intent(this,Main4Activity.class);
        startActivity(intent);
    }

}
