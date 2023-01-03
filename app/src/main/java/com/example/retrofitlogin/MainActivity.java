package com.example.retrofitlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText email,pass;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        login=findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(pass.getText().toString())){
                    Toast.makeText(MainActivity.this,"Email and password Required",Toast.LENGTH_SHORT).show();
                }else{
                    login();
                }
            }
        });




    }

    private void login() {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(email.getText().toString());
        loginRequest.setPassword(pass.getText().toString());

        Call<LoginResponse> loginResponseCall = ApiService.getUserService().userLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Login Successfull"+response,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Login Failed!!!!!!!"+response,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Login Failed!!!!!!!",Toast.LENGTH_SHORT).show();
            }
        });

    }
}






















