package com.example.librarymanagementsystem.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.librarymanagementsystem.MainActivity;
import com.example.librarymanagementsystem.R;
import com.example.librarymanagementsystem.data.DataHandling;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);

                    DataHandling.initListWithData();

                    startActivity(mainIntent);
                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed! Try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
