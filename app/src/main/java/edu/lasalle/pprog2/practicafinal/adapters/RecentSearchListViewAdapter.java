package edu.lasalle.pprog2.practicafinal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.R;

/**
 * Created by joanfito on 1/5/17.
 */

public class RecentSearchListViewAdapter
        extends BaseAdapter implements AdapterView.OnItemClickListener {

    private Context context;
    private ArrayList<String> recent;

    public RecentSearchListViewAdapter(Context context, ArrayList<String> recent) {
        this.context = context;
        this.recent = recent;
    }

    @Override
    public int getCount() {
        return recent.size();
    }

    @Override
    public Object getItem(int position) {
        return recent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View recentView = inflater.inflate(R.layout.recent_search_listview_item, parent, false);

        String search = recent.get(position);

        //Llenamos la vista
        TextView name = (TextView) recentView.findViewById(R.id.recent_search_name);
        name.setText(search);

        return recentView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //TODO hacer la busqueda
    }
}
