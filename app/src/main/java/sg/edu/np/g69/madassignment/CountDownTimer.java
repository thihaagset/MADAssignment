package sg.edu.np.g69.madassignment;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CountDownTimer extends MainNavDrawer {
    Intent i;
    TextView tv;
    String time;
    String useruid;
    FirebaseFirestore db;
    Calendar calendar;
    SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup root = findViewById(R.id.drawer_layout);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_countdowntimer, root, false);
        drawer.addView(contentView, 0);


        i = getIntent();
        time = i.getStringExtra("SeekBar");
        useruid = i.getStringExtra("useruid");
        tv = findViewById(R.id.testTV);
        tv.setText(time);

        //firebase code:
        db = FirebaseFirestore.getInstance();
        calendar = Calendar.getInstance();
    }

    //Hi,Please don't change the code below, it is just for firebase purpose :) - yuxuan
    public void yesfirebase(){
        final String useruid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        Map<String, Object> buildHistory = new HashMap<>();
        buildHistory.put("date", formattedDate);
        buildHistory.put("duration",time);
        buildHistory.put("isComplete", false);
        long time= System.currentTimeMillis();


// Add a new document with a generated ID
        db.collection("Users").document(useruid).collection("BuildHistory").document( time+"______"+ formattedDate).set(buildHistory)

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
    //firebase code ends here uwu

    //you can do your timer here
    public void getuser(View view) {
        yesfirebase();

    }
}

