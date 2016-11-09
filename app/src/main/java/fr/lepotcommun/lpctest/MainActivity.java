package fr.lepotcommun.lpctest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.Collections;
import java.util.List;

import fr.lepotcommun.lpctest.model.Pot;
import fr.lepotcommun.lpctest.networking.RestClient;
import rx.Subscriber;
import rx.Subscription;

public class MainActivity extends AppCompatActivity {
    private Subscription subscription;
    private MainAdapter adapter;

    private View emptyView;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // TODO set content view and assign views

        Toolbar toolbar = null; //TODO
        setSupportActionBar(toolbar);



        FloatingActionButton fab = null;//TODO

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                RestClient.getSingleton().createPot()
                        //TODO
                        .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        //TODO
                    }

                    @Override
                    public void onNext(Void voidResponse) {
                        //TODO

                    }
                });
            }
        });



        setupRecyclerView();

        fetchPots();
    }

    private void setupRecyclerView() {

        //TODO setup recycler view
    }

    private void fetchPots() {
        subscription = RestClient.getSingleton().pots()
                //TODO
                .subscribe(new Subscriber<List<Pot>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {


                        emptyView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNext(List<Pot> pots) {


                        emptyView.setVisibility(View.GONE);

                        Collections.reverse(pots);
                        //TODO
                    }
                });

    }

    private void fetchNewPots() {
       //TODO
        subscription = RestClient.getSingleton().pots()
                //TODO
                .subscribe(new Subscriber<List<Pot>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Pot> pots) {
                      //TODO
                    }
                });
    }






    @Override
    protected void onDestroy() {
        //TODO
        super.onDestroy();
    }




}
