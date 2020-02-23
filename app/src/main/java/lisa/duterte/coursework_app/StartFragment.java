package lisa.duterte.coursework_app;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StartFragment extends Fragment {

    DatabaseUser myDbU;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_start, container, false);
        Bundle b = getArguments();
        final int idUser = b.getInt("idUser");
        Log.d("StartFragment", "id récupéré" + idUser);

        myDbU = new DatabaseUser(getContext());

        String nameUser = myDbU.nameRecover(idUser,0);
        Log.d("Name User is ",nameUser);
        TextView nameWelcome = v.findViewById(R.id.nameUser);
        nameWelcome.setText(nameUser);
        return v;
    }

}
