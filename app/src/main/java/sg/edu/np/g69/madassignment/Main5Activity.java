package sg.edu.np.g69.madassignment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Main5Activity extends MainNavDrawer {


    //CONTACT PAGE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Adding Nav drawer to all the layout
        super.onCreate(savedInstanceState);
        ViewGroup root = findViewById(R.id.drawer_layout);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_main5, root, false);
        drawer.addView(contentView, 0);

        Intent i = getIntent();

    }
}
