package com.example.exercise1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.exercise1.Note;
import com.example.exercise1.R;
import com.example.exercise1.data.db;
import java.util.ArrayList;
import java.util.List;

public class SignupActivity extends AppCompatActivity {
    List<Note> notes = new ArrayList<>();
    private Button btnSignup;
    private EditText txtUsernam;
    private EditText txtPassw;
    private EditText txtConfirm;
    db dbb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_signup);
        btnSignup = findViewById(R.id.btnDone);
        dbb= new db(getApplicationContext());
        notes.addAll(dbb.getAllNotes());
        btnSignup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                txtUsernam = (EditText)findViewById(R.id.txtUsern);
                txtPassw = (EditText)findViewById(R.id.txtPwd);
                txtConfirm = (EditText)findViewById(R.id.txtRepeat);

                String username = txtUsernam.getText().toString();
                String password = txtPassw.getText().toString();
                String confirm = txtConfirm.getText().toString();


                if(checkSignup(username,password,confirm)){
                    dbb.insertNote(username,password);
                    Toast.makeText(getApplicationContext(),"Sign up successfully", Toast.LENGTH_SHORT).show();
                    Intent nextScreen = new Intent(getApplicationContext(), RecyclerViewActivity.class);
                    startActivityForResult(nextScreen, 0);
                }else{
                    Toast.makeText(getApplicationContext(),"Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
            public boolean checkSignup(String username,String password,String confirm){

                //check sign up
                //if confirm password pass move to check exist username
                // if have same username return false
                //
                if(password.equals(confirm)){
                    for(int i=0; i<notes.size(); i++){
                        if(username.equals(notes.get(i).getUsername())){
                            return  false;
                        }
                    }
                    // after all check cases return true
                    return true;
                }
                return false;
            }
        });
        TextView next = (TextView) findViewById(R.id.txtSignin);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), SigninActivity.class);
                startActivityForResult(myIntent, 0);
            }

        });
    }
}