package lisa.duterte.coursework_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChoiceAdapter extends RecyclerView.Adapter<ChoiceHolder> {

    private final List<Choice> choices;
    private Context context;
    private int itemResource;

    public ChoiceAdapter(Context context,int itemResource, List<Choice> choices) {
        this.context = context;
        this.choices = choices;
        this.itemResource = itemResource;
    }


    @NonNull
    @Override
    public ChoiceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(this.itemResource, parent, false);
        return new ChoiceHolder(this.context, view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChoiceHolder holder, int position) {
        Choice c = this.choices.get(position);
        holder.bindChoice(c);

    }

    @Override
    public int getItemCount() {
        return this.choices.size();
    }
}
