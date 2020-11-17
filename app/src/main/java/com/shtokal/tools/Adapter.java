package com.shtokal.tools;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shtokal.tools.lights.LightActivity;
import com.shtokal.tools.metalDetector.MetalActivity;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<String> data;

    Adapter(Context context, List<String> data) {
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
        String title = data.get(i);
        viewHolder.textTitle.setText(title);
        // similarly you can set new image for each card and descriptions

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textTitle, textDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            final int id =itemView.getId();
            System.out.println();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i;
                    switch (textTitle.getText().toString()) {
                        case "Flashlight":
                            i = new Intent(v.getContext(), LightActivity.class); break;
                        case "Metal Detector":
                            i = new Intent(v.getContext(), MetalActivity.class); break;
                        default:
                            i = new Intent(v.getContext(), LightActivity.class); break;

                    }

                    v.getContext().startActivity(i);
                }
            });
            textTitle = itemView.findViewById(R.id.textTitle);
            textDescription = itemView.findViewById(R.id.textDesc);
        }
    }
}
