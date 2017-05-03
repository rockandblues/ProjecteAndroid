package edu.lasalle.pprog2.practicafinal.activities;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.aux.Comment;
import edu.lasalle.pprog2.practicafinal.model.Place;
import edu.lasalle.pprog2.practicafinal.utils.CommentListViewAdapter;

/**
 * Created by MatiasJVH on 04/04/2017.
 */

public class DescriptionActivity extends ParentActivity {

    private Place place;
    private RatingBar restaurantRating;
    private TextView restaurantName;
    private TextView restaurantDescription;
    private ImageView imageView;
    private ListView listView;
    private EditText newComment;
    private FloatingActionButton favButton;
    private CommentListViewAdapter adapter;
    private ArrayList<Comment> comments;
    private boolean blanc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_layout);
        setTitle("");

        place = (Place)getIntent().getSerializableExtra("openedPlace");
        restaurantRating = (RatingBar) findViewById(R.id.rating_description_activity);
        restaurantName = (TextView) findViewById(R.id.restaurant_name_description_activity);
        restaurantDescription = (TextView) findViewById(R.id.restaurant_description);
        imageView = (ImageView) findViewById(R.id.imageDescriptionActivity);
        newComment = (EditText)findViewById(R.id.description_new_comment);
        favButton = (FloatingActionButton) findViewById(R.id.fab_description_activity);

        imageView.setImageResource(R.drawable.restaurante3);
        favButton.setBackgroundTintList(ColorStateList.valueOf((Color.parseColor("#FFEBEE"))));
        blanc = true;
        restaurantName.setText(place.getName());
        restaurantRating.setRating(place.getReview());
        restaurantDescription.setText(getText(R.string.restaurant_description) + " " + place.getType());

        listView = (ListView)findViewById(R.id.comment_list_restaurant_activity);

        //TODO leer comments de la DB

        //Creamos unos comentarios para ver si funciona
        comments = new ArrayList<>();

        Comment c = new Comment();
        c.setUsername("Admin");
        c.setComment("Hello World!");
        comments.add(c);
        Comment c2 = new Comment();
        c2.setUsername("Admin2");
        c2.setComment("CYA Admin");
        comments.add(c2);

        //Creamos el adapter y lo vinculamos a la listview
        adapter = new CommentListViewAdapter(this, comments);
        listView.setAdapter(adapter);

        //Hacemos que la listView ocupe el tamaño que necesite
        setListViewHeightBasedOnChildren(listView);

    }


    public void onMapClicked(View view){

    }


    public void onSendClick(View view){
        //TODO añadir el comentario a la DB
        Comment c = new Comment();
        c.setUsername(MainActivity.emailUser);
        c.setComment(newComment.getText().toString());

        comments.add(c);
        //Creamos el adapter y lo vinculamos a la listview
        adapter = new CommentListViewAdapter(this, comments);
        listView.setAdapter(adapter);

        //Hacemos que la listView ocupe el tamaño que necesite
        setListViewHeightBasedOnChildren(listView);
    }

    public void onFABClick(View view){

        if(blanc) {
            favButton.setBackgroundTintList(ColorStateList.valueOf((Color.parseColor("#C62828"))));
            blanc = false;
        }else {
            favButton.setBackgroundTintList(ColorStateList.valueOf((Color.parseColor("#FFEBEE"))));
            blanc = true;
        }

    }

    /* Este metodo hace que la listview augmente su tamaño en el scrollview */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem instanceof ViewGroup) {
                listItem.setLayoutParams(new ViewGroup.LayoutParams
                        (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }

            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }



}
