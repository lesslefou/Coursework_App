package lisa.duterte.coursework_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class Create_Activity extends AppCompatActivity {

    DataBaseActivity myDbA;
    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<Integer> mImages = new ArrayList<>();
    String nameActivity;
    Integer idUser, activity_number = -1;
    Button validateBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_);
        myDbA = new DataBaseActivity(this);

        nameActivity = Objects.requireNonNull(getIntent().getExtras()).getString("NAME","Error");
        Log.d("Create_Activity", "name récupéré " + nameActivity);
        idUser = Objects.requireNonNull(getIntent().getExtras()).getInt("USER",-1);
        Log.d("Create_Activity", "id récupéré " + idUser);


        activity_number = myDbA.numberActivityRecover(nameActivity);
        Log.d("Create_Activity","number = "+ activity_number);

        TextView printNameField = findViewById(R.id.nameActivity);
        printNameField.setText(nameActivity);

        initCreateImage(activity_number);

        validateBtn = findViewById(R.id.validateBtn);
        validateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Create_Activity.this, ViewActivity.class);
                i.putExtra("ACTIVITY_NUMBER",activity_number);
                startActivity(i);
            }
        });

    }



    private void initCreateImage(Integer activity_number) {

        mImages.add(R.drawable.friends_logo);
        mImageNames.add("Choose your Friends");

        mImages.add(R.drawable.food_logo);
        mImageNames.add("Choose your Foods");

        mImages.add(R.drawable.drink_logo);
        mImageNames.add("Choose your Drinks");

        mImages.add(R.drawable.map_logo);
        mImageNames.add("Choose your Destination");

        initCreateRecycleView(activity_number);
    }

    private void initCreateRecycleView( Integer activity_number) {
        if (activity_number != -1) {
            RecyclerView recyclerView = findViewById(R.id.recycler_view_create);
            Create_Activity_Adapter adapterCreate = new Create_Activity_Adapter(mImageNames, mImages, this,idUser,activity_number);
            recyclerView.setAdapter(adapterCreate);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        else {
            Toast.makeText(Create_Activity.this,"No activity found",Toast.LENGTH_SHORT).show();
        }
    }

}
