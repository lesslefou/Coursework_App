package lisa.duterte.coursework_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class ViewActivity extends AppCompatActivity {

    DataBaseActivity myDbA;
    DataBaseDrinkFood myDbDF;
    DataBaseContactActivty myDbCA;
    Integer activity_number,idUser;
    TextView name,address;
    ListView food,drink,friend;
    ArrayList<String> listContact;
    Button foodUpdate,drinkUpdate,friendUpdate,locationUpdate,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        activity_number = Objects.requireNonNull(getIntent().getExtras()).getInt("ACTNUMBER",-1);
        Log.d("ViewActivity", "activity_number récupéré " + activity_number);
        idUser = Objects.requireNonNull(getIntent().getExtras()).getInt("IDUSER",-1);
        Log.d("ViewActivity", "idUser récupéré " + idUser);

        myDbA = new DataBaseActivity(this);
        myDbDF = new DataBaseDrinkFood(this);
        myDbCA = new DataBaseContactActivty(this);

        name = findViewById(R.id.activityName);
        name.setText(myDbA.informationRecover(activity_number,0));

        address = findViewById(R.id.locationAddress);
        address.setText(myDbA.informationRecover(activity_number,1));

        friend = findViewById(R.id.ListViewFriend);
        listContact = new ArrayList<>();
        viewFriendList();


        foodUpdate = findViewById(R.id.foodUpdate);
        drinkUpdate = findViewById(R.id.drinkUpdate);
        friendUpdate = findViewById(R.id.friendUpdate);
        locationUpdate = findViewById(R.id.locationUpdate);
        back = findViewById(R.id.btn_back);

        foodUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        drinkUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        friendUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewActivity.this,ContactChoice.class);
                i.putExtra("ACTNUMBER",activity_number);
                i.putExtra("UPDATE",1);
                startActivity(i);


            }
        });
        locationUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewActivity.this,LocationChoice.class);
                i.putExtra("ACTNUMBER",activity_number);
                i.putExtra("UPDATE",1);
                startActivity(i);

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewActivity.this,Welcome.class);
                i.putExtra("idUser",idUser);
                startActivity(i);
            }
        });
    }

    private void viewFriendList() {
        Cursor cursor = myDbCA.viewContactActivity(activity_number);

        if (cursor.getCount() == 0) {
            Toast.makeText(ViewActivity.this,"No Contact Added Yet", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                listContact.add(cursor.getString(1));
                ArrayAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listContact);
                friend.setAdapter(listAdapter);
            }
        }
    }
}
