package com.reprototyperstech.transactionapp.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.reprototyperstech.transactionapp.pojo.PojoFragment;
import com.reprototyperstech.transactionapp.R;
import com.reprototyperstech.transactionapp.activity.TransactionDetails;

import java.util.ArrayList;
import java.util.List;
public class listItemAdapter extends RecyclerView.Adapter<listItemAdapter.ViewHolder> {
    List<PojoFragment> lstTransaction;
    Context context;
    public listItemAdapter(List<PojoFragment> lstTransaction, Context context) {
        this.lstTransaction = lstTransaction;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fragment1_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.txt_status.setText(  lstTransaction.get(position).getTransactionStatus());
        holder.txt_amount.setText(lstTransaction.get(position).getTransactionAmount());

        holder.card_view.setOnClickListener(v -> {
            Intent  intent = new Intent(context, TransactionDetails.class);
            intent.putExtra("status",lstTransaction.get(holder.getAdapterPosition()).getTransactionStatus());
            intent.putExtra("amount",lstTransaction.get(holder.getAdapterPosition()).getTransactionAmount());
            intent.putExtra("datetime",lstTransaction.get(holder.getAdapterPosition()).getTransactionDateTime());
            intent.putExtra("narration",lstTransaction.get(holder.getAdapterPosition()).getNarration());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return lstTransaction.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_details,txt_status,txt_amount;
        CardView card_view ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_details = itemView.findViewById(R.id.txt_details);
            txt_status = itemView.findViewById(R.id.txt_status);
            txt_amount = itemView.findViewById(R.id.txt_amount);
            card_view =  itemView.findViewById(R.id.card_view);

        }
    }
}

