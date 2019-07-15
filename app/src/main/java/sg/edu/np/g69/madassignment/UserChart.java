package sg.edu.np.g69.madassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class UserChart extends AppCompatActivity {
    BarChart user_Barchart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_chart);
        user_Barchart = findViewById(R.id.User_bar);
        ArrayList<BarEntry> user_hours_focused = new ArrayList<>();
        user_hours_focused.add(new BarEntry(0f,1.5f));
        user_hours_focused.add(new BarEntry(1f,2.5f));
        user_hours_focused.add(new BarEntry(2f,0f));
        user_hours_focused.add(new BarEntry(3f,5f));
        user_hours_focused.add(new BarEntry(4f,4f));
        user_hours_focused.add(new BarEntry(5f,2f));
        user_hours_focused.add(new BarEntry(6f,1f));
        BarDataSet barDataSet = new BarDataSet(user_hours_focused,"");

        ArrayList<String> week_days = new ArrayList<>();
        week_days.add("Monday");
        week_days.add("Tuesday");
        week_days.add("Wednesday");
        week_days.add("Thursday");
        week_days.add("Friday");
        week_days.add("Saturday");
        week_days.add("Sunday");

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.9f); // set custom bar width
        user_Barchart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(week_days));
        user_Barchart.setFitBars(true);
        user_Barchart.getXAxis().setDrawGridLines(false);
        user_Barchart.getAxisLeft().setDrawGridLines(false);
        user_Barchart.getAxisRight().setDrawGridLines(false);// make the x-axis fit exactly all bars
        user_Barchart.invalidate(); // refresh
        user_Barchart.setData(barData);


    }
}
