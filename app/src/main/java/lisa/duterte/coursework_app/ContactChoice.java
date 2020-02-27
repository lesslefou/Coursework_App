package lisa.duterte.coursework_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class ContactChoice extends AppCompatActivity {

    Integer idUser, activity_number,update=0;
    ArrayList<String> listContactFound = new ArrayList<>();
    DataBaseContact myDbC;
    DataBaseContactActivty myDbCA;
    EditText search;
    ListView contactFoundView;
    Button checkBtn, validateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_choice);
        idUser = Objects.requireNonNull(getIntent().getExtras()).getInt("IDUSER", -1);
        Log.d("ContactChoice", "id récupéré" + idUser);
        activity_number = Objects.requireNonNull(getIntent().getExtras()).getInt("ACTNUMBER", -1);
        Log.d("ContactChoice", "activity_number récupéré" + activity_number);
        update = Objects.requireNonNull(getIntent().getExtras()).getInt("UPDATE", -1);
        Log.d("ContactChoice", "update récupéré" + update);

        myDbC = new DataBaseContact(this);
        myDbCA = new DataBaseContactActivty(this);

        checkBtn = findViewById(R.id.checkLogo);
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkContact(idUser);
            }
        });

        validateBtn = findViewById(R.id.validateBtn);
        validateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (update == 0){
                    finish();
                }
                else {
                    Intent i = new Intent(ContactChoice.this,ViewActivity.class);
                    i.putExtra("ACTNUMBER",activity_number);
                    startActivity(i);

                }
            }
        });


        contactFoundView = findViewById(R.id.contactFoundView);
        listContactFound = new ArrayList<>();
        viewContactAdded();

        contactFoundView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String recup_pseudo = listContactFound.get(position);
                showInformationSavedDialog(recup_pseudo);
            }
        });
    }

    private void checkContact(int user) {
        
        search = findViewById(R.id.searchText);
        String searchText = search.getText().toString();

        //Check si champsText bien rempli
        if (!searchText.equals("")){
            //Check si le user possède le contact
            if (myDbC.checkPseudoContact(searchText,user)) {
                //Check si pseudo dejà ajouté à la listContactFound et l'ajoute ou non en fonction du résultat
                if (myDbCA.insertContactActivity(searchText,activity_number)){
                    Toast.makeText(ContactChoice.this, "Contact added",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ContactChoice.this,"Contact already added",Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(ContactChoice.this,"You don't have this contact on your Contact List",Toast.LENGTH_SHORT).show();
            }
            search.getText().clear();
        }
    }

    //Impossible d'afficher les contacts au fur et à mesure
    private void viewContactAdded() {
        Cursor cursor = myDbCA.viewContactActivity(activity_number);
        Log.d("Contact Choice","number = "+activity_number);

        if (cursor.getCount() == 0) {
            Toast.makeText(ContactChoice.this,"No Data to show", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                listContactFound.add(cursor.getString(1));
                ArrayAdapter listAdapter = new ArrayAdapter<>(ContactChoice.this, android.R.layout.simple_list_item_1, listContactFound);
                contactFoundView.setAdapter(listAdapter);
            }
        }
    }

    protected void showInformationSavedDialog(final String pseudo) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(ContactChoice.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(ContactChoice.this);
        }
        builder.setMessage(R.string.dialogue_message);
        builder.setCancelable(false);
        builder.setNegativeButton(R.string.no_answer, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton(R.string.yes_answer, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDbCA.deleteData(pseudo);
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
