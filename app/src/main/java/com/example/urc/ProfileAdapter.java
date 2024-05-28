package com.example.urc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class ProfileAdapter extends BaseAdapter {
    private List<String> titles;
    private List<Integer> titleImages;
    private Context context;

    public ProfileAdapter(List<String> titles, List<Integer> titleImages, Context context) {
        this.titles = titles;
        this.context = context;
        this.titleImages = titleImages;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public Object getItem(int i) {
        return titles.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder")
        View view = LayoutInflater.from(context).inflate(R.layout.profile_variant_style, viewGroup, false);
        TextView title = view.findViewById(R.id.title);
        ImageView titleImage = view.findViewById(R.id.title_image);

        titleImage.setImageResource(titleImages.get(i));
        title.setText(titles.get(i));

        return view;
    }
}
