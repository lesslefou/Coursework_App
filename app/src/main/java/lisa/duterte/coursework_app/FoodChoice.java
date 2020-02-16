package lisa.duterte.coursework_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class FoodChoice extends AppCompatActivity {

    ///ATTENTION PERMETTRE LA DESELECTION !!!

    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<Integer> mImages = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_choice);


        initImage();

    }


    private void initImage() {

        mImages.add(R.drawable.socca_chips_logo);
        mImageNames.add("Chips Socca");

        mImages.add(R.drawable.paysanne_chips_logo);
        mImageNames.add("Chips Paysanne");

        mImages.add(R.drawable.barbec_chips_logo);
        mImageNames.add("Chips Barbecue");

        mImages.add(R.drawable.saucisson_logo);
        mImageNames.add("Saucisson");

        mImages.add(R.drawable.brownie_logo);
        mImageNames.add("Brownie");

        initRecycleView();
    }

    private void initRecycleView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_drinks);
        DrinkAdapter adapterChoice = new DrinkAdapter(mImageNames, mImages, this);
        recyclerView.setAdapter(adapterChoice);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
