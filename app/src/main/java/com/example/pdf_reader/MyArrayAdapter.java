package com.example.pdf_reader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<File> {
    private Context context;
    private ViewHolder viewHolder;
    private ArrayList<File> pdf_array_list;

    MyArrayAdapter(Context context, ArrayList<File> pdf_array_list) {
        super(context, R.layout.pdf_adapter, pdf_array_list);

        this.context = context;
        this.pdf_array_list = pdf_array_list;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        if (pdf_array_list.size() > 0){
            return pdf_array_list.size();
        }
        else return 1;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.pdf_adapter, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.tv_filename = convertView.findViewById(R.id.pdf_file_name_tv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_filename.setText(pdf_array_list.get(position).getName());

        return convertView;
    }

    public class ViewHolder{
        TextView tv_filename;
    }
}
