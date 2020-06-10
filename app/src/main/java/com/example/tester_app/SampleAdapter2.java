package com.example.tester_app;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SampleAdapter2 extends RecyclerView.Adapter<SampleAdapter2.ExampleViewHolder> {

    private Context mContext;
    private ArrayList<pokeDetail> mPokeDetail;

    public SampleAdapter2(Context context,ArrayList<pokeDetail> pokeList){
        mContext = context;
        mPokeDetail = pokeList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.layout_cardview2,parent,false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        pokeDetail pokeDet = mPokeDetail.get(position);

        String name = pokeDet.getName();
        String type = pokeDet.getType();
        String height = pokeDet.getHeight();
        String weight = pokeDet.getWeight();
        String exp = pokeDet.getExp();
        String imageUrl = pokeDet.getImageUrl();

        holder.nameT.setText(name);
        holder.typeT.setText(type);
        holder.heightT.setText(height);
        holder.weightT.setText(weight);
        holder.expT.setText(exp);
        Glide.with(mContext).load(imageUrl).placeholder(R.drawable.ic_pokeball).circleCrop().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mPokeDetail.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView nameT,typeT,heightT,weightT,expT;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image2);
            nameT = itemView.findViewById(R.id.name);
            typeT = itemView.findViewById(R.id.type);
            heightT = itemView.findViewById(R.id.height);
            weightT = itemView.findViewById(R.id.weight);
            expT = itemView.findViewById(R.id.exp);
        }
    }
}
