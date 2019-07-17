package sg.edu.np.g69.madassignment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Main4Activity extends Main6Activity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup root = findViewById(R.id.drawer_layout);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_main4, root, false);
        drawer.addView(contentView, 0);


        imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.ic_launcher_background);
    }
    public void onClick(View v){
        Intent intent =  new Intent(Main4Activity.this,UserChart.class);
        startActivity(intent);
    }
    public void onAnotherClick(View view){
        Intent intent = new Intent(Main4Activity.this,LoginInAttempt.class);
        startActivity(intent);
    }

}
