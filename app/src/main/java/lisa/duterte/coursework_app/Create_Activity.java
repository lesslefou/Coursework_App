package lisa.duterte.coursework_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;

public class Create_Activity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private RecyclerView choiceListView;
    private ArrayList<Choice> choices;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate: started.");

        choiceListView = findViewById(R.id.recycler_view);

        loadChoice();
    }

    private void loadChoice(){
        Log.d(TAG,"initImageBitmaps: preparing bitmaps");

        choices = new ArrayList<>();

        Choice food_item = new Choice(
                R.string.food,
                R.drawable.food_logo);


        Choice drink_item = new Choice(
                R.string.drink,
                R.drawable.drink_logo);

        choices.add(food_item);
        choices.add(drink_item);
        initRecycleView();
    }

    private  void initRecycleView(){
        Log.d(TAG,"initRecycleView: init recyclerview");

        ChoiceAdapter adapter = new ChoiceAdapter(this, R.layout.location_entry, choices);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        choiceListView.setLayoutManager(layoutManager);
        choiceListView.setAdapter(adapter);

    }
}
