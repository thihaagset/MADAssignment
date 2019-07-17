package sg.edu.np.g69.madassignment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Main2Activity extends Main6Activity {
    Intent i;
    TextView tv;
    String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup root = findViewById(R.id.drawer_layout);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_main2, root, false);
        drawer.addView(contentView, 0);


        i = getIntent();
        time = i.getStringExtra("SeekBar");
        tv = findViewById(R.id.testTV);
        tv.setText(time);
    }
}
