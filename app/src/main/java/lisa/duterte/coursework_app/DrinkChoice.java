package lisa.duterte.coursework_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class DrinkChoice extends AppCompatActivity {

    Button validate_btn;
    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<Integer> mImages = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_choice);

        validate_btn = findViewById(R.id.validateBtn);
        validate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //Permettre la sauvegarde


                /*for (int i=0; i < getItemCount() ; i++){

                 }*/
            }
        });
        initImage();

    }


    private void initImage() {

        mImages.add(R.drawable.coca_logo);
        mImageNames.add("Coca");

        mImages.add(R.drawable.icetea_logo);
        mImageNames.add("Ice Tea");

        mImages.add(R.drawable.beer_logo);
        mImageNames.add("Beer");

        mImages.add(R.drawable.seven_logo);
        mImageNames.add("Seven Up");

        mImages.add(R.drawable.sprite_logo);
        mImageNames.add("Sprite");

        initRecycleView();
    }

    private void initRecycleView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_drinks);
        DrinkAdapter adapterChoice = new DrinkAdapter(mImageNames, mImages, this);
        recyclerView.setAdapter(adapterChoice);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
