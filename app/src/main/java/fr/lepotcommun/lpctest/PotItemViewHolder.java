package fr.lepotcommun.lpctest;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;


class PotItemViewHolder extends RecyclerView.ViewHolder
{

    private ViewDataBinding binding;

    PotItemViewHolder(View rowView) {
        super(rowView);
        binding = DataBindingUtil.bind(rowView);
    }

    ViewDataBinding getBinding() {
        return binding;
    }

}




