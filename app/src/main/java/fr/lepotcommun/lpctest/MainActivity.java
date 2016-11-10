package fr.lepotcommun.lpctest;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.lepotcommun.lpctest.model.Pot;
import fr.lepotcommun.lpctest.networking.RestClient;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String POST_TAG = "ADD POT";
    private static final String GET_TAG = "GET ALL POTS";
    private static final String MAIN_TAG = "MAIN";


    StaggeredGridLayoutManager mStaggeredLayoutManager;
    private Subscription msubscription;
    private MainAdapter madapter;
    private LinearLayout memptyView;
    private RecyclerView mpots_recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // set content view and assign views
        setContentView(R.layout.activity_main);
        Log.i(MAIN_TAG, "=========Creating views============");
        mpots_recyclerView= (RecyclerView) findViewById(R.id.pots_recycler_view);
        memptyView = (LinearLayout) findViewById(R.id.error_msg_view);

        madapter = new MainAdapter(null);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.hideOverflowMenu();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                RestClient.getSingleton().getApi().createPot()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                        Log.i(POST_TAG, "============oncompleted============");
                        mpots_recyclerView.getAdapter().notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(POST_TAG, "============no connection found============ Cause:" + e.toString());
                        Toast.makeText(getApplicationContext(), "no internet connection! ", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(Void voidResponse) {
                    }
                });
            }
        });

        setupRecyclerView();
        fetchPots();
    }

    private void setupRecyclerView() {

        //setup recycler view
        mpots_recyclerView.setHasFixedSize(true);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mpots_recyclerView.setLayoutManager(mStaggeredLayoutManager);
        madapter = new MainAdapter(new ArrayList<Pot>());
        mpots_recyclerView.setAdapter(madapter);
    }

    private void fetchPots() {
        msubscription = RestClient.getSingleton().getApi().pots()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Pot>>() {
                    @Override
                    public void onCompleted() {
                        Log.i(GET_TAG, "============oncompleted============");
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.i(GET_TAG, "============no connection found============ Cause:" + e.toString());
                        memptyView.setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void onNext(List<Pot> pots) {

                        Log.i(GET_TAG, "============Retrieving pots============");
                        memptyView.setVisibility(View.GONE);
                        Collections.reverse(pots);
                        System.out.print(pots);
                        madapter.setPots(pots);
                    }
                });

    }

    private void fetchNewPots() {
        madapter.notifyDataSetChanged();
        msubscription = RestClient.getSingleton().getApi().pots()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<List<Pot>>() {
                    @Override
                    public void onCompleted() {
                        Log.i(POST_TAG, "============oncompleted============");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Pot> pots) {
                        madapter.getPots().addAll(pots);
                        madapter.notifyDataSetChanged();
                        System.out.print(pots);

                    }
                });
    }

    @Override
    protected void onDestroy() {
        //unsubscribe
        msubscription.unsubscribe();
        super.onDestroy();
    }




}
