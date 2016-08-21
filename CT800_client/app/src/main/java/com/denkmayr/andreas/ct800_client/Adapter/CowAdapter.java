package com.denkmayr.andreas.ct800_client.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.denkmayr.andreas.ct800_client.Entity.Cow;
import com.denkmayr.andreas.ct800_client.R;

import java.util.List;

public class CowAdapter extends ArrayAdapter {
    private final Context mContext;
    private final List<Cow> mValues;

    public CowAdapter(Context context, List<Cow> values) {
        super(context, -1, values);
        mContext = context;
        mValues = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item_cow, parent, false);
        TextView header = (TextView) rowView.findViewById(R.id.cowItemHeader);
        TextView text = (TextView) rowView.findViewById(R.id.cowItemText);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.cowItemIcon);

        header.setText(mValues.get(position).getEartag());
        text.setText(mValues.get(position).getName());
        imageView.setImageResource(R.drawable.ic_photo_size_select_actual_black_24dp);

        return rowView;
    }
}
