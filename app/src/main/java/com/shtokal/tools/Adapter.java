package com.shtokal.tools;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.shtokal.tools.cardiograph.HeartRateMonitor;
import com.shtokal.tools.compass.solar.CompassActivity;
import com.shtokal.tools.level.LevelActivity;
import com.shtokal.tools.levell.view.Levell;
import com.shtokal.tools.lights.LightActivity;
import com.shtokal.tools.metalDetector.MetalActivity;
import com.shtokal.tools.ruler.RulerActivity;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<DetailsData> data;

    Adapter(Context context, List<DetailsData> data) {
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.custom_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        // bind the textview with data received
        DetailsData item = data.get(i);
        viewHolder.textTitle.setText(item.getTextTitle());
        viewHolder.textDescription.setText(item.getTextDescription());
        viewHolder.imageView.setImageResource(item.getImage());

        // similarly you can set new image for each card and descriptions

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle, textDescription;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i;
                    switch (textTitle.getText().toString()) {
                        case "Metal Detector": i = new Intent(v.getContext(), MetalActivity.class); break;
                        case "Level": i = new Intent(v.getContext(), Levell.class); break;
                        case "Compass": i = new Intent(v.getContext(), CompassActivity.class); break;
                        case "Ruler": i = new Intent(v.getContext(), RulerActivity.class); break;
                        case "Cardiograph": i = new Intent(v.getContext(), HeartRateMonitor.class); break;
                        default: i = new Intent(v.getContext(), LightActivity.class); break;
                    }
                    v.getContext().startActivity(i);
                }
            });
            textTitle = itemView.findViewById(R.id.textTitle);
            textDescription = itemView.findViewById(R.id.textDesc);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
