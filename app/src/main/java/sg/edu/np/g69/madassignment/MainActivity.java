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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class MainActivity extends MainNavDrawer {
    ImageView imageView;
    SeekBar sb;
    TextView tv;
    Intent i;
    String useruid;
    FirebaseFirestore db;
    FirebaseAuth user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup root = findViewById(R.id.drawer_layout);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_main, root, false);
        drawer.addView(contentView, 0);
        user = FirebaseAuth.getInstance();

        //code for firebase
        i = getIntent();
        useruid = i.getStringExtra("useruid");
        //Firebase codes end here

        imageView = findViewById(R.id.circularImageView2);
        sb = findViewById(R.id.seekBarTime);
        tv = findViewById(R.id.countTV);
        sb.setProgress(0);
        sb.incrementProgressBy(10);
        sb.setMax(90);

        db = FirebaseFirestore.getInstance();
        Toast.makeText(MainActivity.this,user.getUid(),Toast.LENGTH_LONG).show();


        //DETECTS IF SLIDER VALUE HAS CHANGED
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            //THIS CODE CHANGES THE PICTURE IN THE
            //CIRCLE BASED ON THE AMOUNT
            //OF TIME THE USER HAS CHOSEN
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int ps = progress+30;
                String progressString = String.valueOf(ps);
                tv.setText(progressString);

                if(ps>=30 && ps<=50){
                    imageView.setImageResource(R.drawable.woodbld);
                }else if(ps>= 51 && ps<=70){
                    imageView.setImageResource(R.drawable.smallbld);
                }else if(ps>= 71 && ps<=90){
                    imageView.setImageResource(R.drawable.mediumbld);
                }else if(ps>= 91 && ps<=120){
                    imageView.setImageResource(R.drawable.skybld);
                }

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


    //STARTS COUNTDOWN TIMER
    //LAUNCHES CountDownActivity.java
    //SENDS INTENT WITH SeekBar AND UserUID
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
