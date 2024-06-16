package com.inzsoft.petshopee.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.inzsoft.petshopee.R;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    private EditText cust_name;
    private EditText cust_surname;
    private EditText cust_mobile;
    private EditText cust_email;
    private EditText cust_password;
    private EditText cust_re_password;
    private Button reg_btn;
    private TextView mobnumberval;
    private FirebaseAuth mAuth;
    String[] messages = {"Fill all fields", "Successfully registered", "Passwords must much", "Invalid Email", "Invalid Phone number", "Invalid Phone number and Email address"};
    Boolean strongPass, passwordsPass , validEmail, validPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        cust_name=findViewById(R.id.customer_name);
        cust_surname=findViewById(R.id.cust_surname);
        cust_mobile=findViewById(R.id.cust_mobile);
        cust_email=findViewById(R.id.cust_email);
        cust_password=findViewById(R.id.password);
        cust_re_password=findViewById(R.id.repassword);
        reg_btn=findViewById(R.id.reg_btn);
        mobnumberval=findViewById(R.id.mobnumberval);
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Customer_register(view);
            }
        });
        mobnumberval.setVisibility(View.GONE);
        cust_mobile.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                checkmobile(s.toString());
            }
        });
        cust_email.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }
                    @Override
                    public void afterTextChanged(Editable editable) {
                       /* if(!isValidEmail(cust_email.getText().toString())){
                            Log.e("ERRR",isValidEmail(cust_email.getText().toString()) + "");
                            cust_email.setError("Not a valid email address, email must be @sign, @gmail.com, @yahoo.com, @hotmail.com, @Outlook.com");
                        }
                        validEmail = true;*/
                    }
                }
        );
        cust_mobile.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                       /* if(!isValidPhone(cust_mobile.getText().toString())){
                            cust_mobile.setError("Not a valid phone numbers, the number must be of prefix 083,085,086,087,089, it should also have 10 digits  ");
                        }
                        validPhone = true;*/
                    }
                }
        );
        cust_password.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        if(!Password_Validation(cust_password.getText().toString())){
                            cust_password.setError("Password not strong enough, the password must include  digits, special characters, Upper and lowercase letters, with at least 8 digits ");
                        }
                        strongPass = true;
                    }
                }
        );
        cust_re_password.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        if(!cust_re_password.getText().toString().equals(cust_re_password.getText().toString())){
                            cust_re_password.setError("Passwords must");
                        }
                        passwordsPass = true;
                    }
                }
        );

        cust_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                {

                    if(!isValidPhone(cust_mobile.getText().toString())){
                        cust_mobile.startAnimation(shakeError());
                        cust_mobile.setError("Not a valid phone numbers, it should also have 10 digits  ");
                    }
                    validPhone = true;

                }
                else{
                    // Do something when Focus is not on the EditText
                }

            }
        });

        cust_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                {
                    if(!isValidEmail(cust_email.getText().toString())){
                        Log.e("ERRR",isValidEmail(cust_email.getText().toString()) + "");
                        cust_email.startAnimation(shakeError());
                        cust_email.setError("Not a valid email address, email must be @sign, @gmail.com, @yahoo.com, @hotmail.com, @Outlook.com");
                    }
                    validEmail = true;

                }
                else{
                    // Do something when Focus is not on the EditText
                }

            }
        });

    }

    String customer_name,customer_surname,customer_mobile,customer_email,customer_password;
    public void Customer_register(View view)
    {

        customer_name=cust_name.getText().toString();
        customer_surname=cust_surname.getText().toString();
        customer_mobile=cust_mobile.getText().toString();
        customer_email=cust_email.getText().toString();
        customer_password=cust_password.getText().toString();
        String repassword=cust_re_password.getText().toString();

        if (TextUtils.isEmpty(customer_name))
        {
            cust_name.setError("Enter Customer name");
            cust_name.startAnimation(shakeError());
        }
        else if (TextUtils.isEmpty(customer_surname))
        {
            cust_surname.setError("Enter Customer surname");
            cust_surname.startAnimation(shakeError());

        }
        else if (TextUtils.isEmpty(customer_mobile))
        {
            cust_mobile.setError("Enter Mobile Number");
            cust_mobile.startAnimation(shakeError());

        }

        else if (TextUtils.isEmpty(customer_email))
        {
            cust_email.setError("Enter Email");
            cust_email.startAnimation(shakeError());

        }

        else if (TextUtils.isEmpty(customer_password))
        {
            cust_password.setError("Enter password");
            cust_password.startAnimation(shakeError());

        }

        else if (TextUtils.isEmpty(repassword))
        {
            cust_re_password.setError("Enter confirm password");
            cust_re_password.startAnimation(shakeError());

        }
        else if (customer_password.equals(repassword))
        {
            if (strongPass && passwordsPass && validPhone && validEmail) {
                Log.e("err", "passed");
                mAuth=FirebaseAuth.getInstance();
                mAuth.createUserWithEmailAndPassword(customer_email, customer_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            saveData();
                        }
                        else
                        {
                            String error;
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e) {
                                error = "The password is to week. At least 8 digits.";
                            } catch (FirebaseAuthUserCollisionException e) {
                                error = "This account already exist.";
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                error = "Invalid e-mail.";
                            }
                            catch (Exception e) {
                                error = "Error to create a new user.";
                            }
                            Snackbar snackbar = Snackbar.make(view, error, Snackbar.LENGTH_SHORT);
                            snackbar.setBackgroundTint(Color.WHITE);
                            snackbar.setTextColor(Color.BLACK);
                            snackbar.show();
                        }
                    }
                });

            }else{
                Snackbar snackbar = Snackbar.make(view, messages[0], Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(Color.WHITE);
                snackbar.setTextColor(Color.BLACK);
                snackbar.show();
            }


        }
        else
        {
            cust_re_password.setError("Password and confirm password does not match");
            cust_re_password.startAnimation(shakeError());


        }


    }


    public TranslateAnimation shakeError() {
        TranslateAnimation shake = new TranslateAnimation(0, 10, 0, 0);
        shake.setDuration(500);
        shake.setInterpolator(new CycleInterpolator(7));
        return shake;
    }
    private boolean isValidMail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.matches(emailPattern))
        {
            return false;

        }
        return true;
    }

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +
                    "(?=\\S+$)" +
                    ".{4,}" +
                    "$");


    public static boolean isValidPhoneNumber(CharSequence target) {
        if (target.length() != 10) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(target).matches();
        }
    }
    public static boolean Strong_password(CharSequence target) {
        if (!Patterns.EMAIL_ADDRESS.matcher(target).matches()) {

            return false;
        } else {

            return true;
        }
    }

    public void saveData()
    {
        String Custid=mAuth.getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> Customer = new HashMap<>();
        // user.put("name",stnames );
        Customer.put("Customer_name",customer_name);
        Customer.put("Customer_surnam",customer_surname);
        Customer.put("Customer_mobile",customer_mobile);
        Customer.put("Customer_email",customer_email);
        Customer.put("Customer_password",customer_password);


        DocumentReference documentReference = db.collection("Customer").document(Custid);
        documentReference.set(Customer).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("db", "Data saved successfully.");
                Toast.makeText(getApplicationContext(),"Successfully registered",Toast.LENGTH_SHORT).show();

                Intent login=new Intent(getApplicationContext(),Login.class);
                startActivity(login);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("db_error", "Error to save data. " + e.toString());
            }
        });
    }
    public void  checkmobile(String mob)
    {
        if (mob.length()>=3) {
            int n = 3;
            String s = mob.substring(0,n);
            if (s.equals("083")|| s.equals("085")||s.equals("086")||s.equals("087")|| s.equals("089"))
            {
                mobnumberval.setVisibility(View.GONE);
            }
            else
            {

                cust_mobile.setError("The number must be of prefix 083,085,086,087,089, it should also have 10 digits \"");
            }

        }
    }


    public static boolean Password_Validation(String password)
    {

        if(password.length()>=8)
        {
            Pattern letter = Pattern.compile("[a-zA-z]");
            Pattern digit = Pattern.compile("[0-9]");
            Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
            //Pattern eight = Pattern.compile (".{8}");
            Matcher hasLetter = letter.matcher(password);
            Matcher hasDigit = digit.matcher(password);
            Matcher hasSpecial = special.matcher(password);

            return hasLetter.find() && hasDigit.find() && hasSpecial.find();

        }
        else
            return false;

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


    public static boolean isValidPhone(String s)
    {

        // The given argument to compile() method
        // is regular expression. With the help of
        // regular expression we can validate mobile
        // number.
        // The number should be of 10 digits.

        // Creating a Pattern class object
        Pattern p = Pattern.compile("^\\d{10}$");

        // Pattern class contains matcher() method
        // to find matching between given number
        // and regular expression for which
        // object of Matcher class is created
        Matcher m = p.matcher(s);
        String[] prefix = {"99","98","97","96","95","94","94","80","86","70","71","72", "73", "62"};
        List ls = Arrays.asList(prefix);
        // Returning boolean value
        Log.d("PHone", "phone");
        Log.d("STAT", (m.matches() && ls.contains(s.substring(0, 2))) + "");

        return (m.matches() && ls.contains(s.substring(0, 2)));
    }


}