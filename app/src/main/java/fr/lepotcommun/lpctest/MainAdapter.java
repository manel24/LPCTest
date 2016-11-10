package fr.lepotcommun.lpctest;

//import android.databinding.BindingAdapter;
//import android.databinding.DataBindingUtil;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.List;

import fr.lepotcommun.lpctest.model.Pot;


public class MainAdapter extends RecyclerView.Adapter<PotItemViewHolder> {
    private List<Pot> mpots;
    MainAdapter(List<Pot> models) {
        mpots = models;
    }


    //load pot Image using Picasso
    //this method will be called automatically once the binding starts
    @BindingAdapter("bind:imageUrl")
    public static void loadImageAsync(ImageView imageView, String url) {
        Picasso.with(imageView.getContext()).load(url).into(imageView);
    }

    @Override
    public PotItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pot_item, parent, false);
        PotItemViewHolder holder = new PotItemViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(PotItemViewHolder holder, int position) {

        final Pot pot = mpots.get(position);
        holder.getBinding().setVariable(fr.lepotcommun.lpctest.BR.pot, pot);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {

        return mpots.size();
    }

    List<Pot> getPots() {
        return mpots;
    }

    void setPots(List<Pot> pots) {
        this.mpots = pots;
        notifyDataSetChanged();
    }
}
