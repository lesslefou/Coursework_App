package lisa.duterte.coursework_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class LocationChoice extends AppCompatActivity {
    DataBaseActivity myDbA;
    EditText addressField;
    String address;
    Integer activity_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_choice);
        myDbA = new DataBaseActivity(this);
        activity_number = Objects.requireNonNull(getIntent().getExtras()).getInt("ACTNUMBER", -1);
        Log.d("LocationChoice", "activity_number récupéré" + activity_number);



        Button validate_btn = findViewById(R.id.validateBtn);
        validate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressField = findViewById(R.id.adressField);
                address = addressField.getText().toString();
                if (myDbA.insertAddress(address,activity_number)){
                    Toast.makeText(LocationChoice.this,"Address added",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(LocationChoice.this,"Address not added",Toast.LENGTH_SHORT).show();
                }

                finish();
            }
        });
    }
}
