package com.example.edulex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class sign_in extends AppCompatActivity {

    EditText email_in,username_in,password_in;
    Button signin_in;
    DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        email_in = (EditText) findViewById(R.id.email_in);
        username_in = (EditText) findViewById(R.id.username_in);
        password_in = (EditText) findViewById(R.id.password_in);
        signin_in = (Button) findViewById(R.id.sign_inin);
        db = new DBHelper(this);


        signin_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String em = email_in.getText().toString();
                String user = username_in.getText().toString();
                String pass = password_in.getText().toString();


                if(em.equals("")||user.equals("")||pass.equals(""))
                {
                    Toast.makeText(sign_in.this, "Please enter all details", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    Boolean checkuserpassemail = db.checkusernamepasswordemail(user,pass,em);
                    Boolean validatemail = db.validatemail(em);
                    if(validatemail==true)
                    {
                        if (checkuserpassemail == true) {
                            Toast.makeText(sign_in.this, "Log in successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), dashboard.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(sign_in.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(sign_in.this, "Enter valid Email address", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

    }

}

