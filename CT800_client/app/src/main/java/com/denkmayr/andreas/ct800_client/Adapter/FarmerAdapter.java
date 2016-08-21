package com.denkmayr.andreas.ct800_client.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.denkmayr.andreas.ct800_client.Entity.Cow;
import com.denkmayr.andreas.ct800_client.Entity.Farmer;
import com.denkmayr.andreas.ct800_client.R;

import java.util.List;

public class FarmerAdapter extends ArrayAdapter{
    private final Context mContext;
    private final List<Farmer> mValues;

    public FarmerAdapter(Context context, List<Farmer> values) {
        super(context, -1, values);
        mContext = context;
        mValues = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item_farmer, parent, false);
        TextView header = (TextView) rowView.findViewById(R.id.farmerItemHeader);
        TextView text = (TextView) rowView.findViewById(R.id.farmerItemText);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.farmerItemIcon);

        header.setText(mValues.get(position).getName());
        text.setText(mValues.get(position).getEmail());
        imageView.setImageResource(R.drawable.ic_account_box_black_24dp);
        return rowView;
    }
}
