package lisa.duterte.coursework_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sign_Up extends AppCompatActivity {
    DatabaseUser myDbU;
    boolean validInfo = false;
    boolean validPassword = false;
    boolean validEmail = false;
    boolean hasNumbers = false;
    boolean hasLowerCase = false;
    boolean hasUpperCase = false;

    EditText nameField, surnameField, pseudoField, emailField, passwordField;
    Button signUp, back;
    String name,surname,pseudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);
        myDbU = new DatabaseUser(this);


        nameField = findViewById(R.id.edit_name);
        surnameField = findViewById(R.id.edit_surname);
        pseudoField = findViewById(R.id.edit_pseudo);
        emailField = findViewById(R.id.edit_email);
        passwordField = findViewById(R.id.edit_password);


        signUp = findViewById(R.id.btn_sign);
        back = findViewById(R.id.btn_back);

        //Log Button, go to the next activity only if all the information are OK
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = nameField.getText().toString();
                surname = surnameField.getText().toString();
                pseudo = pseudoField.getText().toString();

                if (!name.matches("") || !surname.matches("") || !pseudo.matches(""))
                {
                    validInfo = true;
                }

                //Check if email is correct
                String emailText = emailField.getText().toString();
                if (Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
                    validEmail = true;
                }

                //Check if all the constraints for the password are respected
                validPassword = false;
                hasNumbers = false;
                hasLowerCase = false;
                hasUpperCase = false;
                String pwdText = passwordField.getText().toString();
                for (int i = 0; i < pwdText.length(); i++) {
                    if (Character.isDigit(pwdText.charAt(i))) {
                        hasNumbers = true;
                    } else if (Character.isLowerCase(pwdText.charAt(i))) {
                        hasLowerCase = true;
                    } else if (Character.isUpperCase(pwdText.charAt(i))) {
                        hasUpperCase = true;
                    }
                }
                if (hasNumbers && hasLowerCase && hasUpperCase && (pwdText.length() >= 8)) {
                    validPassword = true;
                }

                //Different reactions for the submitButton
                if (validPassword && validEmail && validInfo) {
                    addUser();
                    Intent i = new Intent(Sign_Up.this, Welcome.class);
                    startActivity(i);
                    notificationDialog();
                }
                if (validInfo) {
                    if (!validPassword && validEmail) {
                        Toast.makeText(getBaseContext(), R.string.wrongPassword, Toast.LENGTH_SHORT).show();
                        passwordField.getText().clear();
                    }
                    if (validPassword && !validEmail) {
                        Toast.makeText(getBaseContext(), R.string.wrongEmail, Toast.LENGTH_SHORT).show();
                    }
                    if (!validPassword && !validEmail) {
                        Toast.makeText(getBaseContext(), R.string.bothWrong, Toast.LENGTH_SHORT).show();
                        passwordField.getText().clear();
                    }
                } else
                {
                    Toast.makeText(getBaseContext(), R.string.notInfo, Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void addUser() {
        boolean isInserted = myDbU.insertData(
                nameField.getText().toString(),
                surnameField.getText().toString(),
                emailField.getText().toString(),
                pseudoField.getText().toString(),
                passwordField.getText().toString());
        if(isInserted)
            Toast.makeText(getBaseContext(), R.string.welcome, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(Sign_Up.this,"User not Inserted",Toast.LENGTH_LONG).show();
    }

    private void notificationDialog() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "CourseWork";

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        notificationBuilder.setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("SuperApp")
                //PENSER A MODIFIER LE NOM
                .setContentText("Welcome on Workshop");

        if(notificationManager != null) {
            notificationManager.notify(1, notificationBuilder.build());
        }
    }
}
