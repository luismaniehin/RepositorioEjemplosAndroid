package com.ricopollo.lnieto.ejemplorecyclerview2.Ejemplo2;

import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.ricopollo.lnieto.ejemplorecyclerview2.R;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    RecyclerView recyclerView2;
    RecyclerViewAdapter adapter2;
    ArrayList<String> stringArrayList = new ArrayList<>();
    CoordinatorLayout coordinatorLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView2 = findViewById(R.id.recycler_view2);
        coordinatorLayout2 = findViewById(R.id.coordinator_layout2);

        this.mxLoadRecyclerView();
        this.mxEnableSwipeToDeleteAndUndo();
    }

    private void mxLoadRecyclerView(){
        stringArrayList.add("Item 1");
        stringArrayList.add("Item 2");
        stringArrayList.add("Item 3");
        stringArrayList.add("Item 4");
        stringArrayList.add("Item 5");
        stringArrayList.add("Item 6");
        stringArrayList.add("Item 7");
        stringArrayList.add("Item 8");
        stringArrayList.add("Item 9");
        stringArrayList.add("Item 10");
        stringArrayList.add("Item 11");
        stringArrayList.add("Item 12");
        stringArrayList.add("Item 13");
        stringArrayList.add("Item 14");
        stringArrayList.add("Item 15");

        adapter2 = new RecyclerViewAdapter(stringArrayList);
        recyclerView2.setAdapter(adapter2);
    }

    private void mxEnableSwipeToDeleteAndUndo(){
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                final String item = adapter2.getDatos().get(position);

                adapter2.removeItem(position);

                Snackbar snackbar = Snackbar.make(coordinatorLayout2, "Item removed", Snackbar.LENGTH_SHORT);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter2.restoreItem(item, position);
                        recyclerView2.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView2);
    }
}
