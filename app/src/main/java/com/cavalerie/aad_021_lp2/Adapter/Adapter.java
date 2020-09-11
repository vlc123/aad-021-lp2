package com.cavalerie.aad_021_lp2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cavalerie.aad_021_lp2.Entity.Leader;
import com.cavalerie.aad_021_lp2.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    List<Leader> leaders;

    public Adapter(Context context, List<Leader> leaders) {
        this.context = context;
        this.leaders = leaders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_learning_leader_model, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Leader leader = leaders.get(position);
        holder.bind(leader);
    }

    @Override
    public int getItemCount() {
        return leaders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView title, description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
        }

        public void bind(Leader leader) {
            Glide.with(itemView.getContext()).load(leader.getBadgeUrl()).into(image);
            title.setText(leader.getName());
            if (leader.getHoursOrSkill())
                description.setText(leader.getScore() + " learning hours, " + leader.getCountry());
            else
                description.setText(leader.getScore() + " skill IQ, score " + leader.getCountry());
        }
    }
}
