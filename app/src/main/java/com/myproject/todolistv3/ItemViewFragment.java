package com.myproject.todolistv3;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemViewFragment extends Fragment {

    public static final int RESULT_OK = -1;
    private static final int EDIT_ITEM_REQUEST_CODE = 102;
    TODoItemDatabase database;
    TextView titleViewTextView;
    TextView descriptionViewTextView;
    ToDoItem item;

    public ItemViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragView = inflater.inflate(R.layout.fragment_item_view, container, false);
        titleViewTextView = (TextView) fragView.findViewById(R.id.titleViewTextView);
        descriptionViewTextView = (TextView) fragView.findViewById(R.id.descriptionViewTextView);
        database = new TODoItemDatabase(getActivity());
        Bundle bundle = getArguments();
        item = bundle.getParcelable("ITEM");
        titleViewTextView.setText(item.getTitle());
        descriptionViewTextView.setText(item.getDiscription());

        FloatingActionButton fab = (FloatingActionButton) fragView.findViewById(R.id.editFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editItem();
            }
        });
        return fragView;
    }

    public void editItem() {
        Intent intent = new Intent(getActivity(), EditItemActivity.class);
        Log.i("YOYOYO", String.valueOf(database.getIdForItem(item)));
        item.setId(database.getIdForItem(item));
        Log.i("YOYOYO", String.valueOf(item.getId()));
        Log.i("YOYOYO", String.valueOf(item.getTitle()));
        intent.putExtra("EDIT_ITEM", item);
        startActivityForResult(intent, EDIT_ITEM_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_ITEM_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                ToDoItem item = data.getParcelableExtra("EDIT_ITEM");
                database.updateTodoItem(item);
                titleViewTextView.setText(item.getTitle());
                descriptionViewTextView.setText(item.getDiscription());
            }
        }
    }
}
