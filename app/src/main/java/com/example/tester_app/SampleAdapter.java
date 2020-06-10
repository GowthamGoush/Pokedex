package com.example.tester_app;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SampleAdapter extends RecyclerView.Adapter<SampleAdapter.SampleViewHolder> {

    private Context context;
    private ArrayList<SampleInput> output;
    public OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public class SampleViewHolder extends RecyclerView.ViewHolder{


        public TextView textView1;
        public ImageView imageView;

        public SampleViewHolder(@NonNull View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.text1);
            imageView = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public SampleAdapter(Context mContext, ArrayList<SampleInput> input){
        context = mContext;
        output = input;
    }

    @NonNull
    @Override
    public SampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_cardview,parent,false);
        return new SampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SampleViewHolder holder, int position) {
        SampleInput currentItem = output.get(position);

        String name = currentItem.getText1();
        String image = currentItem.getPokeImg();

        holder.textView1.setText(name.toUpperCase());
        Glide.with(context).load(image).placeholder(R.drawable.ic_pokeball).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return output.size();
    }


}
