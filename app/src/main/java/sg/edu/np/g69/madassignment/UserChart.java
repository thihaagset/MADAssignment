package sg.edu.np.g69.madassignment;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< HEAD
import android.util.Log;
import android.view.Display;
=======
import android.view.LayoutInflater;
>>>>>>> b595457701dcce6f56590e35c1919bcf2478a132
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UserChart extends MainNavDrawer {
    BarChart user_Barchart;
    FirebaseFirestore db;
    FirebaseAuth user;
    Intent i;
    String currentUser;
    int totalMin;
    int month;
    String currentMonth;
    int daysInMonth;
    TextView nextMonth;
    TextView prevMonth;
    int x=1;
    CollectionReference data;
    Query timeQuery;
    boolean status = false;
    int day = 1;
    ArrayList<BarEntry> user_hours_focused;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup root = findViewById(R.id.drawer_layout);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_user_chart, root, false);
        drawer.addView(contentView, 0);

        user_Barchart = findViewById(R.id.User_bar);
        user_hours_focused = new ArrayList<>();
        i = getIntent();
        currentUser = i.getStringExtra("UserUid");
        Calendar calendar = Calendar.getInstance();
<<<<<<< HEAD
=======

>>>>>>> b595457701dcce6f56590e35c1919bcf2478a132
         month = calendar.get(Calendar.MONTH);

         month = calendar.get(Calendar.MONTH)+1;
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
<<<<<<< HEAD
=======

>>>>>>> b595457701dcce6f56590e35c1919bcf2478a132
        db = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance();

        Toast.makeText(UserChart.this,user.getUid(),Toast.LENGTH_LONG).show();
        data = db.collection("Users").document(user.getUid()).collection("BuildHistory");

        totalMin = 0;

        monthMethod();
        nextMonth=findViewById(R.id.textView2);
        prevMonth=findViewById(R.id.textView3);
        getFromFirebase();

        for(int n=daysInMonth;n>0;n--){
            final int yes = n;

            queryFirebase(currentMonth,Integer.toString(day));

            Log.d("----actually----","no"+ Thread.currentThread().getName());
            day += 1;
        }
        user_hours_focused.add(new BarEntry(1,2));


        /*user_hours_focused.add(new BarEntry(0f,1.5f));
        user_hours_focused.add(new BarEntry(1f,2.5f));
        user_hours_focused.add(new BarEntry(2f,0f));
        user_hours_focused.add(new BarEntry(3f,5f));
        user_hours_focused.add(new BarEntry(4f,4f));
        user_hours_focused.add(new BarEntry(5f,2f));
        user_hours_focused.add(new BarEntry(6f,1f));*/
        BarDataSet barDataSet = new BarDataSet(user_hours_focused,"");

        final ArrayList<String> week_days = new ArrayList<>();

        /*week_days.add("Monday");
        week_days.add("Tuesday");
        week_days.add("Wednesday");
        week_days.add("Thursday");
        week_days.add("Friday");
        week_days.add("Saturday");
        week_days.add("Sunday");*/

        nextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                month+=1;
                monthMethod();
                Toast.makeText(UserChart.this, "You are viewing "+currentMonth,
                        Toast.LENGTH_SHORT).show();
            }
        });

        prevMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                month-=1;
                monthMethod();
                Toast.makeText(UserChart.this, "You are viewing "+currentMonth,
                        Toast.LENGTH_SHORT).show();
            }
        });


        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.9f); // set custom bar width
        user_Barchart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(week_days));
        user_Barchart.setFitBars(true);
        user_Barchart.getXAxis().setDrawGridLines(false);
        user_Barchart.getAxisLeft().setDrawGridLines(false);
        user_Barchart.getAxisRight().setDrawGridLines(false);// make the x-axis fit exactly all bars
        user_Barchart.invalidate(); // refresh
        user_Barchart.setData(barData);
        Log.d("----fuckkkk----","no"+ totalMin + status);


<<<<<<< HEAD

    }
=======
    }


>>>>>>> b595457701dcce6f56590e35c1919bcf2478a132


    public int monthMethod(){
        if(month>12){
            month=1;
            monthMethod();
        }
        else{
            switch(month){
                case 1:
                    currentMonth="Jan";
                    daysInMonth=31;
                    break;

                case 2:
                    currentMonth="Feb";
                    daysInMonth=28;
                    break;

                case 3:
                    currentMonth="Mar";
                    daysInMonth=31;
                    break;

                case 4:
                    currentMonth="Apr";
                    daysInMonth=30;
                    break;

                case 5:
                    currentMonth="May";
                    daysInMonth=31;
                    break;

                case 6:
                    currentMonth="Jun";
                    daysInMonth=30;
                    break;

                case 7:
                    currentMonth="Jul";
                    daysInMonth=31;
                    break;

                case 8:
                    currentMonth="Aug";
                    daysInMonth=31;
                    break;

                case 9:
                    currentMonth="Sep";
                    daysInMonth=30;
                    break;

                case 10:
                    currentMonth="Oct";
                    daysInMonth=31;
                    break;

                case 11:
                    currentMonth="Nov";
                    daysInMonth=30;
                    break;

                case 12:
                    currentMonth="Dec";
                    daysInMonth=31;
                    break;


            }
        }return daysInMonth;
<<<<<<< HEAD
=======

>>>>>>> b595457701dcce6f56590e35c1919bcf2478a132
    }
    public String getFromFirebase(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        return formattedDate;
    }
    public int queryFirebase(String months, String day){
        totalMin = 0;
        status = true;

        timeQuery = data.whereEqualTo("month",months).whereEqualTo("isComplete",true).whereEqualTo("day",day);
        Log.d("----actually----","no" + totalMin);
            timeQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    status = false;

                    if(task.isSuccessful()){

                        for(QueryDocumentSnapshot documentSnapshot:task.getResult()){

                            String tempCal = documentSnapshot.getString("duration");
                            totalMin += Integer.parseInt(tempCal);
                            Log.d("----work----","no" + totalMin);
                        }

                    }else {
                        totalMin = 0;
                        Log.d("----actually----","no" + totalMin);
                    }

                }

            });

        Log.d("-------tes----","no" + totalMin);
            return totalMin;

    }

}
