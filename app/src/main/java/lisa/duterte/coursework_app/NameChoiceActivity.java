package lisa.duterte.coursework_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class NameChoiceActivity extends AppCompatActivity {

    DataBaseActivity myDbA;
    Integer idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_choice);
        idUser = Objects.requireNonNull(getIntent().getExtras()).getInt("IDUSER", -1);
        Log.d("NameChoiceActivity", "id récupéré" + idUser);
        myDbA = new DataBaseActivity(this);


        Button next_btn = findViewById(R.id.nextBtn);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameActivity = findViewById(R.id.edit_name);
                final String name = nameActivity.getText().toString();

                if (!checkName(name)) {
                    addActivity(idUser,name);
                    Intent i = new Intent(getBaseContext(), Create_Activity.class);
                    i.putExtra("NAME", name);
                    i.putExtra("USER", idUser);
                    startActivity(i);
                } else {
                    Toast.makeText(NameChoiceActivity.this, "An activity has already this name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addActivity(Integer idUser, String nameActivity) {
        boolean isInserted = myDbA.insertActivity(idUser,nameActivity);
        if (isInserted)
            Toast.makeText(NameChoiceActivity.this, "Activity Inserted", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(NameChoiceActivity.this, "Error", Toast.LENGTH_SHORT).show();
    }

    private boolean checkName(String name) {

        //Check si champsText bien rempli
        if (!name.equals("")) {
            //Check si le nom est déjà utilisé
            return myDbA.checkName(name);
        }
        return false;
    }
}
