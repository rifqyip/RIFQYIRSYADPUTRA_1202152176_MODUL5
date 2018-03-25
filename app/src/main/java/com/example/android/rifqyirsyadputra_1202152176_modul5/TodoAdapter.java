package com.example.android.rifqyirsyadputra_1202152176_modul5;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rifqy on 25/03/2018.
 */

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.holder> {
    //Deklarasi Variable
    private Context context;
    private List<Todoitem> item;
    int id;

    public TodoAdapter(Context context, List<Todoitem> item, int id) {
        this.context=context; //get context
        this.item=item; //get item
        this.id=id; //get id
    }

    //Menentukan View RecyclerView
    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.activity_list, parent, false);
        holder holder=new holder(view);
        return holder;
    }

    //Menentukan nilai objek sesuai dengan recycler
    @Override
    public void onBindViewHolder(TodoAdapter.holder holder, int position) {
        Todoitem Titem =item.get(position); //mengatur posisi
        //Get Holder
        holder.cvTodo.setText(Titem.getTask());
        holder.cvDesk.setText(Titem.getDesk());
        holder.cvPrior.setText(Titem.getPrior());
        holder.card.setCardBackgroundColor(context.getResources().getColor(this.id));//set background
    }

    public Todoitem getItem(int position) { //mengatur posisi
        return item.get(position);
    }

    //Jika item di hapus
    public void removeitem(int i) {
        item.remove(i);
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, item.size());
    }

    @Override
    public int getItemCount() { //item yang akan ditampilkan
        return item.size();
    }

    class holder extends RecyclerView.ViewHolder {
        //Deklarasi Variable
        TextView cvTodo, cvDesk, cvPrior;
        CardView card;

        public holder(View itemView) {
            super(itemView);
            cvTodo=itemView.findViewById(R.id.cvTodo);
            cvDesk=itemView.findViewById(R.id.cvDesk);
            cvPrior=itemView.findViewById(R.id.cvPrior);
            card=itemView.findViewById(R.id.CardView);
        }
    }
}
