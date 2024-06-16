package com.inzsoft.petshopee.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.inzsoft.petshopee.GlobalVar;
import com.inzsoft.petshopee.MainActivity;
import com.inzsoft.petshopee.MainHome;
import com.inzsoft.petshopee.R;

import java.util.regex.Pattern;

public class Login extends AppCompatActivity {
    private EditText login_email;
    private EditText login_password;
    private Button login_btn;
    private TextView log_error;
    private  TextView reglink;
    private TextView forpassword,log_error2;
    CollectionReference loginRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_email=findViewById(R.id.login_email);
        login_password=findViewById(R.id.login_password);
        login_btn=findViewById(R.id.login_btn);
        log_error=findViewById(R.id.log_error);
        reglink=findViewById(R.id.reglink);
        forpassword=findViewById(R.id.forpassword);
        log_error2=findViewById(R.id.log_error2);
        log_error2.setVisibility(View.GONE);
        forpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent Resetpassword=new Intent(getApplicationContext(),Passwordreset.class);
                //startActivity(Resetpassword);
            }
        });
        reglink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Register=new Intent(getApplicationContext(), Register.class);
                startActivity(Register);
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Customer_login();
            }
        });
        login_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                {
                    if(!isValidEmail(login_email.getText().toString())){
                        Log.e("ERRR",isValidEmail(login_email.getText().toString()) + "");
                        login_email.setError("Not a valid email address, email must be @sign, @gmail.com, @yahoo.com, @hotmail.com, @Outlook.com");
                    }
                    else
                    {
                        checkemil(login_email.getText().toString());
                    }
                    validEmail = true;
                    // Toast.makeText(Login.this, login_email.getText().toString(), Toast.LENGTH_SHORT).show();

                }
                else{
                    // Do something when Focus is not on the EditText
                }

            }
        });

        login_email.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }
                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                }
        );
    }
    Boolean strongPass, passwordsPass , validEmail, validPhone;
    public void Customer_login()
    {
        String login_emailid=login_email.getText().toString();
        String login_pass=login_password.getText().toString();
        if (TextUtils.isEmpty(login_emailid))
        {
            login_email.startAnimation(shakeError());
            login_email.setError("Enter email");
        }
        else if(isValidMail(login_emailid))
        {
            login_email.startAnimation(shakeError());
            login_email.setError("Enter valid email");
        }
        else if (TextUtils.isEmpty(login_pass))
        {
            login_password.startAnimation(shakeError());
            login_password.setError("Enter password");

        }
        else
        {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            loginRef = db.collection("Customer");
            Query query;
            query= loginRef.whereEqualTo("Customer_email",login_emailid).whereEqualTo("Customer_password",login_pass);
            query.addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    if (value.isEmpty())
                    {
                        log_error.setText(" Invalid Password");
                    }
                    else
                    {
                        //Toast.makeText(getApplicationContext(),value.getDocuments().get(0).getId(),Toast.LENGTH_SHORT).show();
                        GlobalVar.email=login_emailid;
                        GlobalVar.Customer_ID=value.getDocuments().get(0).getId();
                        Intent mains=new Intent(getApplicationContext(), MainHome.class);
                        startActivity(mains);
                        //walletCheck();
                    }
                }
            });
        }

    }

    private boolean isValidMail(String email)
    {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.matches(emailPattern))
        {
            return false;

        }
        return true;
    }
    public TranslateAnimation shakeError()
    {
        TranslateAnimation shake = new TranslateAnimation(0, 10, 0, 0);
        shake.setDuration(500);
        shake.setInterpolator(new CycleInterpolator(7));
        return shake;
    }


    public void checkemil(String login_emailid)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        loginRef = db.collection("Customer");
        Query query;
        query= loginRef.whereEqualTo("Customer_email",login_emailid);
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value.isEmpty())
                {
                    log_error2.setVisibility(View.VISIBLE);
                    log_error2.setText("This email is not registered");
                }
                else
                {
                    log_error2.setVisibility(View.GONE);
                    log_error2.setText("");
                    //Toast.makeText(getApplicationContext(),value.getDocuments().get(0).getId(),Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    public static boolean isValidEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}