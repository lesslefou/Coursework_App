package lisa.duterte.coursework_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Create_Contact extends AppCompatActivity {
    DatabaseContact myDbC;
    EditText editName, editSurname, editEmail, editPseudo;
    Button btnAddData,btnBack;
    String name,surname,email,pseudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__contact);

        myDbC = new DatabaseContact(this);

        editName = findViewById(R.id.editText_name);
        editSurname = findViewById(R.id.editText_surname);
        editEmail = findViewById(R.id.editText_email);
        editPseudo = findViewById(R.id.editText_pseudo);


        btnAddData = findViewById(R.id.btn_create);
        btnBack = findViewById(R.id.btn_back);

        AddData();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void AddData() {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = editName.getText().toString();
                surname =editSurname.getText().toString();
                email = editEmail.getText().toString();
                pseudo = editPseudo.getText().toString();

                if (!name.matches("") || !surname.matches("") || !email.matches("") || !pseudo.matches("")) {

                    boolean isInserted = myDbC.insertData(name,surname,email,pseudo);
                    if(isInserted)
                        Toast.makeText(Create_Contact.this,"Data Inserted",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(Create_Contact.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(Create_Contact.this,"You need to fill all the information",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
