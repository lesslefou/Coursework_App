package lisa.duterte.coursework_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Setting extends AppCompatActivity {

    DatabaseUser myDbU;
    TextView name,surname,email,pseudo;
    Integer idUser;
    Button back,unsubscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        myDbU = new DatabaseUser(this);

        idUser = getIntent().getExtras().getInt("IDUSER",-1);
        Log.d("Setting", "id récupéré" + idUser);

        name = findViewById(R.id.edit_name);
        surname = findViewById(R.id.edit_surname);
        email = findViewById(R.id.edit_email);
        pseudo = findViewById(R.id.edit_pseudo);


        String nameUser = myDbU.nameRecover(idUser,0);
        name.setText(nameUser);
        String surnameUser = myDbU.nameRecover(idUser,1);
        surname.setText(surnameUser);
        String emailUser = myDbU.nameRecover(idUser,2);
        email.setText(emailUser);
        final String pseudoUser = myDbU.nameRecover(idUser,3);
        pseudo.setText(pseudoUser);

        back = findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        unsubscribe = findViewById(R.id.btn_unsubscribe);
        unsubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInformationSavedDialog(pseudoUser);
            }
        });
    }

    protected void showInformationSavedDialog(final String pseudo) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setMessage(R.string.dialogue_message_unsubscribe);
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
                myDbU.deleteUser(pseudo);
                Intent i = new Intent(Setting.this, MainActivity.class);
                startActivity(i);
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
