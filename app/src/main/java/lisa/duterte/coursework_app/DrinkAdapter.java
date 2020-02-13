package lisa.duterte.coursework_app;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;



public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.ViewHolder> implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "DrinkAdapter";

    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<Integer> mImages = new ArrayList<>();
    private Context mContext;

    public DrinkAdapter(ArrayList<String> mImageNames, ArrayList<Integer> mImages, Context mContext) {
        this.mImageNames = mImageNames;
        this.mImages = mImages;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_drink_adapter, parent,false);

        Spinner spinner = view.findViewById(R.id.quantity);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mContext,
                R.array.quantityNumbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG,"onBindViewHolder: called");

        holder.image.setImageDrawable(ResourcesCompat.getDrawable(mContext.getResources(), mImages.get(position), null));
        holder.imageName.setText(mImageNames.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick:clicked on: " + mImageNames.get(position));
                Toast.makeText(mContext,mImageNames.get(position),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView imageName;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.cocaText);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
