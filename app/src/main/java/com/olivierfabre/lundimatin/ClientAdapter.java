package com.olivierfabre.lundimatin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.olivierfabre.lundimatin.models.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientViewHolder> {
    private List<Client> clientList;

    public ClientAdapter(List<Client> clientList) {
        // Initialise la liste à une liste vide si elle est nulle
        this.clientList = clientList != null ? clientList : new ArrayList<>();
    }

    @NonNull
    @Override
    public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.client_item, parent, false);
        return new ClientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientViewHolder holder, int position) {
        Client client = clientList.get(position);
        holder.clientName.setText(client.getNom());
        if (client.getNom() != null && client.getNom().length() >= 2) {
            holder.initialsCircle.setText(client.getNom().substring(0, 2).toUpperCase());
        } else {
            holder.initialsCircle.setText("PM");
        }
    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList != null ? clientList : new ArrayList<>();
        notifyDataSetChanged();
    }

    public class ClientViewHolder extends RecyclerView.ViewHolder {
        TextView clientName, initialsCircle;

        public ClientViewHolder(@NonNull View itemView) {
            super(itemView);
            clientName = itemView.findViewById(R.id.client_name);
            initialsCircle = itemView.findViewById(R.id.initials_circle);
        }
    }
}
