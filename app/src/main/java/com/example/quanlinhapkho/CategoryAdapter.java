package com.example.quanlinhapkho;

import android.content.Context;
import android.icu.util.ULocale;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Locale;

public class CategoryAdapter extends ArrayAdapter<String> {
    public CategoryAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected, parent, false);
        TextView txtSelected = convertView.findViewById(R.id.textviewSelected);

        String category = this.getItem(position);
        if(category !=null) {
            txtSelected.setText(category);
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        TextView txtCategory = convertView.findViewById(R.id.textviewCategory);

        String category = this.getItem(position);
        if(category !=null) {
            txtCategory.setText(category);
        }

        return convertView;
    }
}
