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

public class LocationChoice extends AppCompatActivity {
    DataBaseActivity myDbA;
    EditText addressField;
    String address;
    Integer activity_number,update=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_choice);
        myDbA = new DataBaseActivity(this);
        activity_number = Objects.requireNonNull(getIntent().getExtras()).getInt("ACTNUMBER", -1);
        Log.d("LocationChoice", "activity_number récupéré" + activity_number);
        update = Objects.requireNonNull(getIntent().getExtras()).getInt("UPDATE", -1);
        Log.d("LocationChoice", "update récupéré" + update);


        if (update == 1){
            addressField = findViewById(R.id.adressField);
            addressField.setText(myDbA.locationActivityRecover(activity_number));
        }

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

                if (update == 0){
                    finish();
                }
                else {
                    Intent i = new Intent(LocationChoice.this,ViewActivity.class);
                    i.putExtra("ACTNUMBER",activity_number);
                    startActivity(i);
                }
            }
        });
    }
}
