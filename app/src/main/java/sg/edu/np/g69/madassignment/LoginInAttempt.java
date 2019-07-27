package sg.edu.np.g69.madassignment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static java.util.logging.Logger.global;

public class LoginInAttempt extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextView tv_NewUser;
    private EditText email;
    private EditText password;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_in_attempt);
        mAuth = FirebaseAuth.getInstance();
        tv_NewUser = findViewById(R.id.sign_up_touch);
        button = findViewById(R.id.button5);

        tv_NewUser.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent intent = new Intent(LoginInAttempt.this,SignUp.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                return true;
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void onclick(View view){
        email = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        if(!(email.getText().toString().equals("")) && !(password.getText().toString().equals(""))){
            mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("yes", "signInWithEmail:success");
                                Toast.makeText(LoginInAttempt.this, "Login Successful",
                                        Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent intent = new Intent(LoginInAttempt.this,MainActivity.class);
                                intent.putExtra("useruid",user.getUid());
                                startActivity(intent);

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("no", "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginInAttempt.this, "Invalid Email/Password",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }

    }


}

