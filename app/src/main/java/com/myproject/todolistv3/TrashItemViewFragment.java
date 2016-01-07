package com.myproject.todolistv3;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrashItemViewFragment extends Fragment {

    public static final int RESULT_OK = -1;
    private static final int EDIT_ITEM_REQUEST_CODE = 102;
    TODoItemDatabase database;
    TextView titleViewTextView;
    TextView descriptionViewTextView;
    TextView priorityViewTextView;
    ToDoItem item;
    //    UpdateMainView updateMainView;
//    Context context;
    public TrashItemViewFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragView = inflater.inflate(R.layout.fragment_trash_item_view, container, false);
        titleViewTextView = (TextView) fragView.findViewById(R.id.trashTitleViewTextView);
        descriptionViewTextView = (TextView) fragView.findViewById(R.id.trashDescriptionViewTextView);
        priorityViewTextView  = (TextView) fragView.findViewById(R.id.trashPriorityViewTextView);
        database = new TODoItemDatabase(getActivity());
//        context = getActivity();
//        updateMainView = (MainActivity) context;
        Bundle bundle = getArguments();
        item = bundle.getParcelable("ITEM");
        titleViewTextView.setText(item.getTitle());
        descriptionViewTextView.setText(item.getDiscription());
        priorityViewTextView.setText(item.getPriority());
        FloatingActionButton fab = (FloatingActionButton) fragView.findViewById(R.id.undoFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                undoItem();
            }
        });
        FloatingActionButton fabDelete = (FloatingActionButton) fragView.findViewById(R.id.trashDeleteFab);
        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItem();
            }
        });
        return fragView;
    }

    public void deleteItem() {
        item.setId( (int) database.getIdForItem(item));
//        item.setIs_deleted(1);
        database.hardDeleteTodoItem(item);
        getActivity().finish();
    }

    public void undoItem (){
        item.setIs_deleted(0);
        database.updateTodoItem(item);
        getActivity().finish();
    }

}
