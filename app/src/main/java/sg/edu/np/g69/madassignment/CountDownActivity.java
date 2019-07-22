package sg.edu.np.g69.madassignment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CountDownActivity extends MainNavDrawer {
    Intent intent;
    TextView tv;
    String timeString;
    String useruid;
    FirebaseFirestore db;
    Calendar calendar;
    SimpleDateFormat dateFormat;
    ProgressBar mProgressBar;
    CountDownTimer cdt;
    int time;
    ImageView imageView;
    String formattedDate;
    long timetouse;
    FirebaseAuth user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup root = findViewById(R.id.drawer_layout);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_countdowntimer, root, false);
        drawer.addView(contentView, 0);


        intent = getIntent();
        timeString = intent.getStringExtra("SeekBar");
        useruid = intent.getStringExtra("useruid");
        tv = findViewById(R.id.countTV);
        tv.setText(timeString);

        user = FirebaseAuth.getInstance();


        mProgressBar=findViewById(R.id.progressBar);
        mProgressBar.setProgress(time);
        mProgressBar.setMax(120);

        time = Integer.parseInt(timeString);


        //firebase code:
        db = FirebaseFirestore.getInstance();
        calendar = Calendar.getInstance();
        Toast.makeText(CountDownActivity.this,user.getUid(),Toast.LENGTH_LONG).show();

        imageView = findViewById(R.id.circularImageView2);
        if(time>=30 && time<=50){
            imageView.setImageResource(R.drawable.woodbld);
        }else if(time>= 51 && time<=70){
            imageView.setImageResource(R.drawable.smallbld);
        }else if(time>= 71 && time<=90){
            imageView.setImageResource(R.drawable.mediumbld);
        }else if(time>= 91 && time<=120){
            imageView.setImageResource(R.drawable.skybld);
        }
        startTimer(time);
    }

    public void onClickPause(View v){
        cdt.cancel();
        tv.setText("0:00");
        mProgressBar.setProgress(0);

        Intent intent = new Intent(CountDownActivity.this,GiveUp.class);
        startActivity(intent);
    }



    //you can do your timer here
    private void startTimer(int duration) {
        yesfirebase();

        if(cdt != null){
            cdt.cancel();
        }

        cdt = new CountDownTimer(duration*60000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                tv.setText((int)millisUntilFinished/60000 + " : " + (int)millisUntilFinished%60000/1000);
                mProgressBar.setProgress((int)millisUntilFinished/60000);
            }

            @Override
            public void onFinish() {
                tv.setText("0 : 00");
                mProgressBar.setProgress(0);
                updateOnFinished();

                Toast tt = Toast.makeText(CountDownActivity.this,
                        "Times Up!", Toast.LENGTH_LONG);
                tt.show();

                new AlertDialog.Builder(CountDownActivity.this)
                        .setTitle("Times Up!")
                        .setMessage("Times up")
                        .setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {

                                    }
                                })
                        .show();
            }
        }.start();
    }
    //Hi,Please don't change the code below, it is just for firebase purpose :) - yuxuan
    public void yesfirebase(){
        final String useruid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        timetouse= System.currentTimeMillis();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        formattedDate = df.format(c);
        Map<String, Object> buildHistory = new HashMap<>();
        buildHistory.put("date", formattedDate);
        buildHistory.put("duration",timeString);
        buildHistory.put("isComplete", false);
        buildHistory.put("timeStamp",timetouse);



// Add a new document with a generated ID
        db.collection("Users").document(user.getUid()).collection("BuildHistory").document( timetouse+"______"+ formattedDate).set(buildHistory)

                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void avoid) {
                        Log.d("yes", "DocumentSnapshot added with ID: " + useruid);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("qwe", "Error adding document", e);
                    }
                });

    }
    public void updateOnFinished(){
        final String useruid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Map<String,Object> updateStatus= new HashMap<>();
        updateStatus.put("isComplete",true);
        db.collection("Users").document(user.getUid()).collection("BuildHistory").document(timetouse +"______"+formattedDate).update(updateStatus)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("yes", "DocumentSnapshot added with ID: " + useruid);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("qwe", "Error adding document", e);
                    }
                });
    }
    //firebase code ends here uwu
}

