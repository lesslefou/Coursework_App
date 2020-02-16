package lisa.duterte.coursework_app;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ContactFragment extends Fragment {
    private DatabaseContact myDbC;
    private ArrayList<String> listContact;
    private ArrayAdapter adapter;
    private ListView contactView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_contact, container, false);

        Button create_Btn = v.findViewById(R.id.createBtn);
        create_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Create_Contact.class);
                startActivity(i);
            }
        });

        myDbC = new DatabaseContact(getContext());
        listContact = new ArrayList<>();
        contactView = v.findViewById(R.id.listContactView);
        viewData();
        contactView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //PERMETTRE DE SUPPRIMER LE CONTACT LORSQU'IL CLICK DESSUS
                String text = contactView.getItemAtPosition(position).toString();
                Toast.makeText(getContext(),""+text,Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    private void viewData() {
        Cursor cursor = myDbC.viewData();

        if (cursor.getCount() == 0) {
            Toast.makeText(getContext(),"No Data to show", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                listContact.add(cursor.getString(1));
            }
            adapter = new ArrayAdapter<>(getContext(), R.layout.fragment_contact, listContact);
            contactView.setAdapter(adapter);
        }
    }
}
