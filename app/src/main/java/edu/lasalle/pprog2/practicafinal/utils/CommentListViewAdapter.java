package edu.lasalle.pprog2.practicafinal.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.model.Comment;

/**
 * Created by joanfito on 1/5/17.
 */

public class CommentListViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Comment> comments;

    public CommentListViewAdapter(Context context, ArrayList<Comment> comments) {
        this.context = context;
        this.comments = comments;
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Object getItem(int position) {
        return comments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View commentView = inflater.inflate(R.layout.comment_listview_item, parent, false);

        Comment comment = comments.get(position);

        //Llenamos la vista
        TextView username = (TextView) commentView.findViewById(R.id.comment_user_name);
        username.setText(comment.getUsername());

        TextView message = (TextView) commentView.findViewById(R.id.comment_user_message);
        message.setText(comment.getComment());

        return commentView;
    }
}
