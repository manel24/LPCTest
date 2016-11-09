package fr.lepotcommun.lpctest;

//import android.databinding.BindingAdapter;
//import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.List;

//import fr.lepotcommun.lpctest.databinding.PotItemBinding;
import fr.lepotcommun.lpctest.model.Pot;


public class MainAdapter extends RecyclerView.Adapter<PotItemViewHolder> {
    private List<Pot> pots;
    MainAdapter(List<Pot> models) {
        pots = models;
    }

    @Override
    public PotItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       //TODO implement

        return null;
    }

    @Override
    public void onBindViewHolder(PotItemViewHolder holder, int position) {

       //TODO implement



    }

    @Override
    public int getItemCount() {

        //TODO implement
        return 0;
    }

    void setPots(List<Pot> pots) {
        this.pots = pots;
        //TODO notifiy data changed
    }

    List<Pot> getPots() {
        return pots;
    }

   // @BindingAdapter("bind:loadImage")
    public static void loadImageAsync(ImageView imageView, String url) {
     //todo loadImage
    }
}

