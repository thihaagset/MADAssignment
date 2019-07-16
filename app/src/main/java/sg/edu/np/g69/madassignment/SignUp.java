package sg.edu.np.g69.madassignment;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    EditText email;
    EditText password;
    Button button;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        email = findViewById(R.id.email_address_sign_up);
        button = findViewById(R.id.button4);
        password = findViewById(R.id.user_password_sign_up);
        firebaseAuth = FirebaseAuth.getInstance();



    }
    public void onclick(View v){
        firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),
                password.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignUp.this,"Successfully Sign up",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(SignUp.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
