package fr.lepotcommun.lpctest;

import android.support.v7.widget.RecyclerView;
import fr.lepotcommun.lpctest.databinding.PotItemBinding;


class PotItemViewHolder extends RecyclerView.ViewHolder {
    private PotItemBinding binding;
    PotItemViewHolder(PotItemBinding viewDataBinding) {
        super(viewDataBinding.getRoot());
        binding = viewDataBinding;
    }

    PotItemBinding getBinding() {
        return binding;
    }
}
