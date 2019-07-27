package com.lite.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LifecycleObserver {

    private ListView listView;
    private ModelRepo modelRepo;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> mList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.list);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(mAdapter);
        modelRepo = new ModelRepo(getApplicationContext());
        insertMessages();
        getMessages();
    }

    public void insertMessages(){

        modelRepo.insert("String12");
        modelRepo.insert("String22");
        modelRepo.insert("String32");
        modelRepo.insert("String42");
        modelRepo.insert("String52");
    }

    public void getMessages(){

        modelRepo.getModel().observe(this, new Observer<List<Model>>() {
            @Override
            public void onChanged(List<Model> models) {
                for(Model model: models){
                    String message = model.toString();
                    mList.add(message);
                    mAdapter.add(message);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        modelRepo.getModel().removeObservers(this);
    }
}
