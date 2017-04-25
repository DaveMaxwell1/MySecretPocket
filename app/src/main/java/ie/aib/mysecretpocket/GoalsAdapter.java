package ie.aib.mysecretpocket;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;

import java.text.NumberFormat;
import java.util.List;


public class GoalsAdapter extends RecyclerView.Adapter<GoalsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Goal> goalList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, subtitle;
        public NumberProgressBar numberProgressBar;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.goal_title);
            subtitle = (TextView) view.findViewById(R.id.goal_subtitle);
            numberProgressBar = (NumberProgressBar) view.findViewById(R.id.number_progress_bar);
        }
    }

    public GoalsAdapter(Context mContext, List<Goal> goalList) {
        this.mContext = mContext;
        this.goalList = goalList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goal_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Goal goal = goalList.get(position);
        holder.title.setText(goal.getName());

        double leftToSave = goal.getTotalToSave() - goal.getCurrentlySaved();
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        holder.subtitle.setText("Left to save: " + formatter.format(leftToSave));

        // update progress

        double percentageValue = (goal.getCurrentlySaved() / goal.getTotalToSave()) * 100;
        final int progressBarValue = (int)percentageValue;

        do {
            holder.numberProgressBar.incrementProgressBy(1);
        } while(holder.numberProgressBar.getProgress() < progressBarValue);

    }

    @Override
    public int getItemCount() {
        return goalList.size();
    }
}
