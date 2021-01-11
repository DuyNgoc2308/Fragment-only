package com.example.exercise1.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exercise1.Note;
import com.example.exercise1.R;
import com.example.exercise1.data.db;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter <AccountAdapter.ViewHolder> {
    Context context;
    RecyclerView rvAccounts;
    List<Note> listAccounts;
    int selectedPos;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_account, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AccountAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Note account = listAccounts.get(position);
        holder.txtUsername.setText(account.getUsername());
        holder.txtId.setText(String.valueOf(account.getId()));
        holder.txtPassword.setText(account.getPassword());
        db dbb = new db(context);
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(),
                        listAccounts.get(position).getUsername() +" | "
                                + "has been deleted", Toast.LENGTH_SHORT)
                        .show();
                dbb.deleteNoteById(listAccounts.get(position).getId());
                listAccounts.remove(selectedPos);
                notifyItemRemoved(selectedPos);

                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listAccounts.size();
    }
    public AccountAdapter (Context context, List<Note> listAccounts, RecyclerView rvAccounts){
        this.listAccounts = listAccounts;
        this.rvAccounts = rvAccounts;
        this.context = context;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtId;
        TextView txtUsername;
        TextView txtPassword;
        Button btn_delete;

        db dbb;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.account_id);
            txtPassword = itemView.findViewById(R.id.account_password);
            txtUsername = itemView.findViewById(R.id.account_username);
            btn_delete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
