package com.example.final_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CardViewHolder> {

    Context context;

    List<Cars> carsList;

    public CarAdapter(Context context, List<Cars> carsList) {
        this.context = context;
        this.carsList = carsList;
    }


    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_cars, null);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
            //getting the product of the specified position
            Cars car = carsList.get(position);

            //binding the data with the viewholder views
            holder.txtYear.setText(String.valueOf(car.getYear()));
            holder.txtMake.setText(car.getMake());
            holder.txtModel.setText(car.getModel());
            holder.txtPrice.setText(String.valueOf(car.getPrice()));
            if(!car.getDescription().isEmpty())
                holder.txtDescription.setText(car.getDescription());

    }


    @Override
    public int getItemCount() {
        return carsList.size();
    }



    public static class CardViewHolder extends RecyclerView.ViewHolder {

        TextView txtYear, txtMake, txtModel, txtPrice, txtDescription;

        public CardViewHolder(View itemView) {
            super(itemView);

            txtYear = itemView.findViewById(R.id.txtYear);
            txtMake = itemView.findViewById(R.id.txtMake);
            txtModel = itemView.findViewById(R.id.txtModel);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtDescription = itemView.findViewById(R.id.txtDescription);
        }


    }

}
