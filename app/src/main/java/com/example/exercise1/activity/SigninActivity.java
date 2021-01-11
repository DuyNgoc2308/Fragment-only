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

public class SigninActivity extends AppCompatActivity {

    List<Note> notes = new ArrayList<>();
    db dbb;
    private EditText txtUsername;
    private EditText txtPwd;
    private Button btnSignin;

    public SigninActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_signin);

        dbb = new db(getApplicationContext());
        notes.addAll(dbb.getAllNotes());
        btnSignin = findViewById(R.id.btnSignin);
        //check get a note
//        long x = 1;
//        Note nt = dbb.getNote(x);
//        txtTest.setText(nt.getUsername());
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtUsername   = (EditText)findViewById(R.id.txtEmailadd);
                txtPwd = (EditText)findViewById(R.id.txtPassw);
                String username = txtUsername.getText().toString();
                String password = txtPwd.getText().toString();


                if(checkLogin(username,password)){
                    Intent nextScreen = new Intent(getApplicationContext(), RecyclerViewActivity.class);
                    nextScreen.putExtra("username", username);
                    startActivityForResult(nextScreen, 0);
                }else{
                    TextView txtWrong = (TextView) findViewById(R.id.txtWrong);
                    Toast.makeText(getApplicationContext(),"Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
            public boolean checkLogin(String username,String password){
                for(int i=0; i<notes.size(); i++){
                    if(username.equals(notes.get(i).getUsername()) && password.equals(notes.get(i).getPassword())){
                        return  true;
                    }
                }

                return false;
            }
        });



        TextView next = (TextView) findViewById(R.id.txtSignup);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), SignupActivity.class);
                startActivityForResult(myIntent, 0);
            }

        });
//        Button signin = (Button) findViewById(R.id.btnSignin);
//        EditText email=findViewById(R.id.txtEmailadd);
//        EditText pwd=findViewById(R.id.txtPassw);
//        String emailValue=email.getText().toString();
//        String passwValue=pwd.getText().toString();



    }

}