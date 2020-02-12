package lisa.duterte.coursework_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Log_In extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log__in);

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
                Intent i = new Intent(this, Welcome.class);
                startActivity(i);
                break;
        }

    }
}
