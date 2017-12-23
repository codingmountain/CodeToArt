package com.example.chintugandhwani.codetoart.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.chintugandhwani.codetoart.Activity.MainActivity;
import com.example.chintugandhwani.codetoart.Constant.StringConstant;
import com.example.chintugandhwani.codetoart.Pojoclasses.MoviesList.Results;
import com.example.chintugandhwani.codetoart.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by chintu gandhwani on 12/22/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    List<Results> results;
    Context context;


    public RecyclerViewAdapter(List<Results> results, Context context) {
        this.results = results;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.movielist_row_layout, parent, false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mvtitile.setText(results.get(position).getTitle());
        holder.mvdate.setText(results.get(position).getRelease_date());
        Picasso.with(context).load(StringConstant.BASE_URL_IMG + results.get(position).getPoster_path()).placeholder(R.drawable.placeholder).into(holder.moviethumnailimg);
        if (Boolean.parseBoolean(results.get(position).getAdult())) {
            holder.mvtype.setText("A");
            holder.mvtype.setBackgroundColor(context.getResources().getColor(android.R.color.holo_red_light));
        } else {
            holder.mvtype.setText("U/A");
            holder.mvtype.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_light));
        }
        holder.detailbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity obj = (MainActivity) context;
                obj.showmoviedetail(results.get(position).getId(), results.get(position).getTitle());
            }
        });

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView moviethumnailimg;
        CardView detailbt;
        TextView mvdate, mvtype, mvtitile;

        public MyViewHolder(View itemView) {
            super(itemView);
            moviethumnailimg = (ImageView) itemView.findViewById(R.id.moviethumnail);
            detailbt = (CardView) itemView.findViewById(R.id.moviedetailbt);
            mvdate = (TextView) itemView.findViewById(R.id.moviedate);
            mvtype = (TextView) itemView.findViewById(R.id.movietype);
            mvtitile = (TextView) itemView.findViewById(R.id.movietitle);
        }
    }
}
