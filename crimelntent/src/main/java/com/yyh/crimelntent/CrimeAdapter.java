package com.yyh.crimelntent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yyh.crimelntent.databinding.ItemCrimeBinding;
import com.yyh.crimelntent.entity.Crime;

import java.util.ArrayList;
import java.util.List;

public class CrimeAdapter extends RecyclerView.Adapter<CrimeAdapter.ViewHolder> {

    private List<Crime> crimes;
    private List<Crime> crimeAll;
    private int currentIndex = 0;
    private View.OnClickListener onClickListener;

    public CrimeAdapter(List<Crime> crimes) {
        this.crimes = crimes;
        this.crimeAll = new ArrayList<>(crimes);
    }

    public View.OnClickListener getOnClickListener(){
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setCurrentIndex(int currentIndex) {
        // 更新视图
        notifyItemChanged(this.currentIndex);
        notifyItemChanged(currentIndex);
        // 设置当前选中项索引
        this.currentIndex = currentIndex;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCrimeBinding inflate = ItemCrimeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Crime crime = crimes.get(position);
        holder.binding.tvTitle.setText(crime.getTitle());
        holder.binding.tvDate.setText(crime.getDate());
        holder.binding.tvSolved.setText(crime.getSolved().toString());
        holder.itemView.setSelected( currentIndex == position);
    }

    @Override
    public int getItemCount() {
        return crimes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemCrimeBinding binding;

        public ViewHolder(@NonNull ItemCrimeBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
            this.itemView.setTag(this);
            this.itemView.setOnClickListener(onClickListener);
        }
    }
}
