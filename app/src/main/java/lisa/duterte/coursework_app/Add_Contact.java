package lisa.duterte.coursework_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_Contact extends AppCompatActivity {

    DatabaseContact myDbC;
    EditText editPseudo;
    Button btnAddData,btnBack;
    String pseudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__contact);

        myDbC = new DatabaseContact(this);

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

               pseudo = editPseudo.getText().toString();

                if (!pseudo.matches("")) {

                    /*boolean isInserted = myDbC.insertData(pseudo);
                    if(isInserted)
                        Toast.makeText(Add_Contact.this,"Data Inserted",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(Add_Contact.this,"Data not Inserted",Toast.LENGTH_LONG).show();*/
                }
                else{
                    Toast.makeText(Add_Contact.this,"You need to fill all the information",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
