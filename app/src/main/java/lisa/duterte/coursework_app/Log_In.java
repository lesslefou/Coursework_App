package lisa.duterte.coursework_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Log_In extends Activity implements View.OnClickListener {
    DatabaseUser myDbU;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log__in);
        myDbU = new DatabaseUser(this);
        View back = findViewById(R.id.btn_back);
        back.setOnClickListener(this);
        View logIn = findViewById(R.id.btn_log);
        logIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_log:
                //Checker si la personne à bien un compte sur l'app
                if (checkUser()) {
                    Intent i = new Intent(this, Welcome.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(this,"Wrong email or Password",Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    private boolean checkUser() {
        EditText emailText = findViewById(R.id.edit_email);
        EditText passwordText = findViewById(R.id.edit_password);

        boolean isFound = myDbU.search(
                emailText.getText().toString(),
                passwordText.getText().toString());

        return isFound;
    }


}
