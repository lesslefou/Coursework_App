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

public class Add_Contact extends AppCompatActivity {

    DatabaseUser myDbU;
    DataBaseContact myDbC;
    EditText editPseudo;
    Button btnAddData,btnBack;
    String pseudo;
    Integer user = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__contact);

        int idUser = Objects.requireNonNull(getIntent().getExtras()).getInt("IDUSER",-1);
        Log.d("Add_Contact", "id récupéré" + idUser);
        myDbU = new DatabaseUser(this);
        myDbC = new DataBaseContact(this);

       editPseudo = findViewById(R.id.editText_pseudo);


        btnAddData = findViewById(R.id.btn_add);
        btnBack = findViewById(R.id.btn_back);

        AddData(idUser);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void AddData(final Integer user) {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               pseudo = editPseudo.getText().toString();
                if (!pseudo.matches("")) {

                    boolean isFound = myDbU.checkPseudo(pseudo);
                    if (isFound) {
                        boolean isInserted = myDbC.insertPseudo(pseudo,user);
                        if (isInserted)
                            Toast.makeText(Add_Contact.this, "Contact Inserted", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(Add_Contact.this, "Pseudo already added", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Add_Contact.this, "Pseudo not Found", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(Add_Contact.this,"You need to fill all the information",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
