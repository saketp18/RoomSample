package com.lite.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleObserver;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements LifecycleObserver {

    private ListView listView;
    private ModelRepo modelRepo;
    private ArrayAdapter<String> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.list);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(mAdapter);
        modelRepo = new ModelRepo(getApplicationContext());

        Handler mHandler = new Handler();
        //insertMessages(); Use to add items to DB.
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getMessages();
            }
        }, 5000);

    }

    public void insertMessages(){

        modelRepo.insert("String12");
        modelRepo.insert("String22");
        modelRepo.insert("String32");
        modelRepo.insert("String42");
        modelRepo.insert("String52");
    }

    public void getMessages(){

        Observer<List<Model>> mObserver = new Observer<List<Model>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Model> models) {

                Log.d("Saket", models.toString());
                for(Model model : models) {
                    mAdapter.add(model.toString());
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                mAdapter.notifyDataSetChanged();
            }
        };
        modelRepo.getModel().observeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(mObserver);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
