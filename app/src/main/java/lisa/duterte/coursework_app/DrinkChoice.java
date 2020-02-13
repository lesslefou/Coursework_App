package lisa.duterte.coursework_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class DrinkChoice extends AppCompatActivity {

    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<Object> mImages = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_choice);

        Spinner spinner = findViewById(R.id.quantity);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.quantityNumbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //spinner.setOnItemSelectedListener(this);

        initImage();

    }

    private void initImage() {

        mImages.add(this.getResources().getDrawable(R.drawable.coca_logo));
        mImageNames.add("Coca");

        mImages.add(this.getResources().getDrawable(R.drawable.icetea_logo));
        mImageNames.add("Ice Tea");

        mImages.add(this.getResources().getDrawable(R.drawable.beer_logo));
        mImageNames.add("Beer");

        initRecycleView();
    }

    private void initRecycleView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        DrinkAdapter adapterChoise = new DrinkAdapter(mImageNames, mImages, this);
        recyclerView.setAdapter(adapterChoise);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
