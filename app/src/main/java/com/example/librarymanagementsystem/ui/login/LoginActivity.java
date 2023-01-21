package com.example.librarymanagementsystem.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.librarymanagementsystem.MainActivity;
import com.example.librarymanagementsystem.R;
import com.example.librarymanagementsystem.data.DataHandling;
import com.example.librarymanagementsystem.model.User;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;
    ArrayList<User> adminUserList;
    boolean isUser = false;
    private int visibleCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = findViewById(R.id.username);
        username.setText("");
        password = findViewById(R.id.password);
        password.setText("");

        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (password.getRight() - password.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                        if(visibleCounter%2==0) {
                            password.setTransformationMethod(null);
                            password.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_baseline_lock_24), null, getResources().getDrawable(R.drawable.ic_baseline_hide_source_24) ,null);
                        }
                        else {
                            password.setTransformationMethod(new PasswordTransformationMethod());
                            password.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_baseline_lock_24),null,getResources().getDrawable(R.drawable.ic_baseline_remove_red_eye_24) ,null);
                        }
                        visibleCounter++;
                        return true;
                    }
                }
                return false;
            }
        });

        loginButton = findViewById(R.id.loginButton);;


        DataHandling.initListWithData();
        adminUserList = DataHandling.adminUserList;

        password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    for (User user : adminUserList) {
                        if (username.getText().toString().equals(user.getUsername()) && password.getText().toString().equals(user.getPassword())) {
                            Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            //DataHandling.setCurrentUser(user);
                            //DataHandling.initListWithData();

                            startActivity(intent);
                            isUser = true;
                        }
                    }
                    if(!isUser) {
                        Toast.makeText(LoginActivity.this, "Login Failed! Try again", Toast.LENGTH_SHORT).show();
                        username.setText("");
                        password.setText("");
                    }
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (User user : adminUserList) {
                    if (username.getText().toString().equals(user.getUsername()) && password.getText().toString().equals(user.getPassword())) {
                        Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        DataHandling.setCurrentUser(user);
                        startActivity(intent);
                        isUser = true;
                    }
                }
                if(!isUser) {
                    Toast.makeText(LoginActivity.this, "Login Failed! Try again", Toast.LENGTH_SHORT).show();
                    username.setText("");
                    password.setText("");
                }
            }
        });
    }
}
