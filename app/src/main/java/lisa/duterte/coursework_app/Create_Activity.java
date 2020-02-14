package lisa.duterte.coursework_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;

public class Create_Activity extends AppCompatActivity {

    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<Integer> mImages = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_);

        initCreateImage();

    }


    private void initCreateImage() {

        mImages.add(R.drawable.food_logo);
        mImageNames.add("Choose your Foods");

        mImages.add(R.drawable.drink_logo);
        mImageNames.add("Choose your Drinks");

        initCreateRecycleView();
    }

    private void initCreateRecycleView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_create);
        Create_Activity_Adapter adapterCreate = new Create_Activity_Adapter(mImageNames, mImages, this);
        recyclerView.setAdapter(adapterCreate);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
