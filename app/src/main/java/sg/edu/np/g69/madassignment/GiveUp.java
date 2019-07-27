package sg.edu.np.g69.madassignment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GiveUp extends MainNavDrawer {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup root = findViewById(R.id.drawer_layout);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_giveup, root, false);
        drawer.addView(contentView, 0);

        intent = getIntent();

    }


    //BUTTON TO TRY ANOTHER BUILDING
    //BRINGS USER TO MainActivity.java THROUGH INTENT
    public void onClickAgain(View view){
        intent = new Intent(GiveUp.this,MainActivity.class);
        startActivity(intent);
    }


}
