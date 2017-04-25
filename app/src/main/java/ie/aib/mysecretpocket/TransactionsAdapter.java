package ie.aib.mysecretpocket;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;


public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Transaction> transactionsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView narrative, date, amount;

        public MyViewHolder(View view) {
            super(view);
            narrative = (TextView) view.findViewById(R.id.narrative);
            date = (TextView) view.findViewById(R.id.date);
            amount = (TextView) view.findViewById(R.id.amount);
        }
    }

    public TransactionsAdapter(Context mContext, List<Transaction> transactionsList) {
        this.mContext = mContext;
        this.transactionsList = transactionsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transaction_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Transaction transaction = transactionsList.get(position);

        holder.narrative.setText(transaction.getNarrative());

        SimpleDateFormat df = new SimpleDateFormat("dd-mm-YYYY");
        holder.date.setText(df.format(transaction.getDate()));

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        holder.amount.setText(formatter.format(transaction.getAmount()));

    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }
}
