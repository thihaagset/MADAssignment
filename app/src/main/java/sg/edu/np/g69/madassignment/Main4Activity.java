package sg.edu.np.g69.madassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Main4Activity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
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
