package lisa.duterte.coursework_app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ContactFragment extends Fragment {
    private DataBaseContact myDbC;
    private ArrayList<String> listContact;
    private ListView contactView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_contact, container, false);
        Bundle b = getArguments();
        final int idUser = b.getInt("idUser");
        Log.d("ContactFragment", "id récupére" + idUser);

        Button create_Btn = v.findViewById(R.id.createBtn);
        create_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Add_Contact.class);
                i.putExtra("idUser",idUser);
                startActivity(i);
            }
        });


        contactView = v.findViewById(R.id.listContactView);
        myDbC = new DataBaseContact(getContext());
        listContact = new ArrayList<>();
        viewData();

        contactView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String recup_pseudo = listContact.get(position);
                showInformationSavedDialog(recup_pseudo);

            }
        });


        return v;
    }

    private void viewData() {
        Cursor cursor = myDbC.viewContact();

        if (cursor.getCount() == 0) {
            Toast.makeText(getContext(),"No Data to show", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                listContact.add(cursor.getString(4));
                ArrayAdapter listAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, listContact);
                contactView.setAdapter(listAdapter);
            }
        }
    }

    protected void showInformationSavedDialog(final String pseudo) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getContext());
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
                myDbC.deleteData(pseudo);
                Intent i = new Intent(getActivity(), ContactFragment.class);
                startActivity(i);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
