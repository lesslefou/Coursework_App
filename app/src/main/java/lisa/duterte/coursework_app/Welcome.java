package lisa.duterte.coursework_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {
    private static final String TAG = "Welcome";
    private static Welcome singleInstance;
    Toolbar toolbar;
    Integer idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        idUser = getIntent().getExtras().getInt("idUser",-1);
        Log.d("Welcome", "id récupére" + idUser);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        StartFragment startFragment = new StartFragment();

        transaction.add(R.id.fragment_place,startFragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {
        SharedPreferences sharedPreferences = getSharedPreferences("MY", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        editor.commit();
        Welcome.getSingleInstance().setLoggingOut(true);
        Intent intent = new Intent(Welcome.this,
                MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public static Welcome getSingleInstance() {
        if (singleInstance == null) {
            singleInstance = new Welcome();
        }

        return singleInstance;
    }
    public void setLoggingOut(boolean isLoggingOut) {
    }

    public void onSelectFragment(View view) {
        Fragment newFragment = new Fragment();

        if (view == findViewById(R.id.activityBtn)) {
            newFragment = new ActivityFragment();
        } else if (view == findViewById(R.id.contactBtn)) {
            newFragment = new ContactFragment();
        }

        Bundle b = new Bundle();
        b.putInt("idUser",idUser);
        newFragment.setArguments(b);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_place, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}
