package lisa.duterte.coursework_app;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class ChoiceHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final ImageView locationIcon;
    private final TextView locationName;

    private  Choice choice;
    private Context context;

    public ChoiceHolder(Context context, View itemView) {
        super(itemView);

        this.context = context;
        this.locationIcon = itemView.findViewById(R.id.entry_location_icon);
        this.locationName = itemView.findViewById(R.id.entry_location_name);

        itemView.setOnClickListener(this);
    }

    public void bindChoice(Choice choice) {
        this.choice = choice;
        this.locationName.setText(choice.getName());
        this.locationIcon.setImageDrawable(this.context.getResources().getDrawable(choice.getIconId()));
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context,"You clicked",Toast.LENGTH_SHORT).show();


    }
}
