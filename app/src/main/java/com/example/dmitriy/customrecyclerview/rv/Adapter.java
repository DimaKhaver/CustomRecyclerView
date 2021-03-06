package com.example.dmitriy.customrecyclerview.rv;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.dmitriy.customrecyclerview.R;
import com.example.dmitriy.customrecyclerview.linkedlist.CircularLinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dmitriy on 29.04.17.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ItemsHolder> {

    private CircularLinkedList circularLinkedList;

    public Adapter(CircularLinkedList circularLinkedList) {
        this.circularLinkedList = circularLinkedList;
    }

    @NonNull
    @Override
    public ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = setUpNumberOfElemsOnScreen(parent);
        return new ItemsHolder(view);
    }

    private View setUpNumberOfElemsOnScreen(ViewGroup parent) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item, parent, false);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        if (windowManager != null)
            windowManager.getDefaultDisplay().getMetrics(context.getResources().getDisplayMetrics());

        float deviceWidth = (float) context.getResources().getDisplayMetrics().widthPixels;
        view.getLayoutParams().width = ((int) deviceWidth) / 5;

        return view;
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemsHolder holder, int position) {
        holder.setTime(String.valueOf(circularLinkedList.get(position)));
    }

    @Override
    public int getItemCount() {
        return circularLinkedList.getSize();
    }

    class ItemsHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.time_text) TextView tv;

        private ItemsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void setTime(String name) {
           if (tv != null)
               tv.setText(name);
        }
    }
}
