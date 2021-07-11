package com.example.edulex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class sign_up extends AppCompatActivity {

    EditText name,email,username,password;
    Button sign_up,sign_in;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        sign_up = (Button) findViewById(R.id.sign_up);
        sign_in = (Button) findViewById(R.id.sign_in);

        db = new DBHelper(this);



        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String em = email.getText().toString();
                String user = username.getText().toString();
                String pass = password.getText().toString();



                if(em.equals("")||user.equals("")||pass.equals(""))
                {
                    Toast.makeText(sign_up.this, "Please enter all details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean checkuser = db.checkusername(user);
                    Boolean checkemail = db.checkemail(em);
                    Boolean validatemail = db.validatemail(em);
                    if (validatemail == true)
                    {
                        if (checkuser==false && checkemail==false )
                        {
                            Boolean insert = db.insertData(em, user, pass);

                            if (insert == true)
                            {
                                Toast.makeText(sign_up.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), dashboard.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(sign_up.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }


                        }
                        else
                        {
                            Toast.makeText(sign_up.this, "User exists,please log in or change details", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(sign_up.this, "Enter valid Email address", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),sign_in.class);
                startActivity(intent);

            }
        });
    }
}

