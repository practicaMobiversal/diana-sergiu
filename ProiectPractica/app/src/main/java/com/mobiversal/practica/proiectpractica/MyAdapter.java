package com.mobiversal.practica.proiectpractica;

/**
 * Created by Lenovo on 10.07.2017.
 */


        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<GroupAdapter> groupsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, nr_util;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            nr_util = (TextView) view.findViewById(R.id.genre);

        }
    }


    public MyAdapter(List<GroupAdapter>groupsList) {
        this.groupsList = groupsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.group_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GroupAdapter group = groupsList.get(position);
        holder.title.setText(group.getTitle());


    }

    @Override
    public int getItemCount() {
        return groupsList.size();
    }
}
