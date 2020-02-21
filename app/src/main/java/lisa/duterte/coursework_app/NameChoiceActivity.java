package lisa.duterte.coursework_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

public class NameChoiceActivity extends AppCompatActivity {

    DataBaseActivity myDbA;
    Integer idUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_choice);
        idUser = Objects.requireNonNull(getIntent().getExtras()).getInt("IDUSER",-1);
        Log.d("NameChoiceActivity", "id récupéré" + idUser);
        myDbA = new DataBaseActivity(this);


        Button next_btn = findViewById(R.id.nextBtn);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameActivity = findViewById(R.id.edit_name);
                final String name = nameActivity.getText().toString();
                Intent i = new Intent(getBaseContext(), Create_Activity.class);
                i.putExtra("NAME",name);
                i.putExtra("USER",idUser);
                startActivity(i);
            }
        });
    }
}
