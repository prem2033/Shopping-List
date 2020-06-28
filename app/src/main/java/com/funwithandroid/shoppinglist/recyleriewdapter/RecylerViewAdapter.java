package com.funwithandroid.shoppinglist.recyleriewdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.funwithandroid.shoppinglist.ItemObject;
import com.funwithandroid.shoppinglist.R;

import java.util.ArrayList;

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.ViewHolder> {
    ArrayList<ItemObject> arrayList;
    Context context;
    private OnItemClickListener mListener;
    public RecylerViewAdapter(Context context, ArrayList<ItemObject> arrayList) {
        this.context = context;
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public RecylerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        return new ViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecylerViewAdapter.ViewHolder holder, int position) {
        ItemObject itemObject=arrayList.get(position);
        holder.card_list_name.setText(itemObject.getItem_name());
        holder.card_list_quantity.setText(itemObject.getItem_quality());
        if(itemObject.getMarked()==1){
            holder.checkBox.setChecked(true);
        }else{
            holder.checkBox.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView card_list_name,card_list_quantity;
        Button card_delete,card_edit;
        CheckBox checkBox;
        public ViewHolder(@NonNull final View itemView, final OnItemClickListener listener) {
            super(itemView);
            card_list_name = itemView.findViewById(R.id.card_list_name);
            card_list_quantity = itemView.findViewById(R.id.card_list_quantity);
            checkBox=itemView.findViewById(R.id.checkbox);
            card_delete=itemView.findViewById(R.id.card_delete);
            card_edit=itemView.findViewById(R.id.card_edit);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position,itemView);
                        }
                    }
                }
            });
            card_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteBtnClick(position,itemView);
                        }
                    }
                }
            });
            card_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onEditBtnClick(position,itemView);
                        }
                    }
                }
            });
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onCheckBoxClicked(position,itemView);
                        }
                    }
                }
            });
        }

    }
    public interface OnItemClickListener {
        void onItemClick(int position,View itemview);
        void onDeleteBtnClick(int position,View itemview);
        void onEditBtnClick(int position,View itemview);
        void onCheckBoxClicked(int position,View itemview);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
}
