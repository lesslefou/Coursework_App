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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

//https://www.youtube.com/watch?v=QY-O49a_Ags&t=6s pour la recherche de contact

public class ActivityFragment extends Fragment {
    private DataBaseActivity myDbA;
    private ArrayList<String> listActivity;
    private ListView activityView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v = inflater.inflate(R.layout.fragment_activity, container, false);
        Bundle b = getArguments();
        final int idUser = Objects.requireNonNull(b).getInt("idUser");
        Log.d("ActivityFragment", "id récupéré" + idUser);

        Button create_Btn = v.findViewById(R.id.createBtn);
        create_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), NameChoiceActivity.class);
                i.putExtra("IDUSER",idUser);
                startActivity(i);
            }
        });

        activityView = v.findViewById(R.id.listActivities);
        myDbA = new DataBaseActivity(getContext());
        listActivity = new ArrayList<>();
        viewData(idUser);

        activityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String recup_name = listActivity.get(position);
                showInformationSavedDialog(recup_name);

            }
        });

        return v;
    }

    private void viewData(int user) {
        Cursor cursor = myDbA.viewActivity(user);

        if (cursor.getCount() == 0) {
            Toast.makeText(getContext(),"No Data to show", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                listActivity.add(cursor.getString(1));
                ArrayAdapter listAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, listActivity);
                activityView.setAdapter(listAdapter);
            }
        }
    }

    protected void showInformationSavedDialog(final String name) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getContext());
        }
        builder.setMessage(R.string.dialogue_message_activity);
        builder.setCancelable(false);
        builder.setNegativeButton(R.string.delete_answer, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDbA.deleteActivity(name);
                dialog.cancel();
            }
        });
        builder.setPositiveButton(R.string.update_answer, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Faire la fonction
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
