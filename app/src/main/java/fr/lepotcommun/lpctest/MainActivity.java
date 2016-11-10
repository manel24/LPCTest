package fr.lepotcommun.lpctest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import fr.lepotcommun.lpctest.model.Pot;
import fr.lepotcommun.lpctest.networking.RestClient;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    //private static final String TAG = "MAIN";

    StaggeredGridLayoutManager mStaggeredLayoutManager;
    private Subscription msubscription;
    private MainAdapter madapter;
    private View memptyView;
    private RecyclerView mpots_recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // set content view and assign views
        setContentView(R.layout.activity_main);

        mpots_recyclerView= (RecyclerView) findViewById(R.id.pots_recycler_view);
        memptyView = findViewById(R.id.error_msg_view);
        madapter = new MainAdapter(null);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                RestClient.getSingleton().getApi().createPot()
                        //TODO
                        .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(), "problem adding pot ! ", Toast.LENGTH_LONG).show();
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
        mpots_recyclerView.setHasFixedSize(true);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mpots_recyclerView.setLayoutManager(mStaggeredLayoutManager);
        mpots_recyclerView.setAdapter(madapter);
    }

    private void fetchPots() {
        msubscription = RestClient.getSingleton().getApi().pots()
                //TODO
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Pot>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {


                        memptyView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNext(List<Pot> pots) {


                        memptyView.setVisibility(View.GONE);

                        Collections.reverse(pots);
                        //TODO
                    }
                });

    }

    private void fetchNewPots() {
       //TODO
        msubscription = RestClient.getSingleton().getApi().pots()
                //TODO
                .subscribeOn(Schedulers.newThread())
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
