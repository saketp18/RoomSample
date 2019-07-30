package com.lite.myapplication;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Saket on 27,July,2019
 */
public class ModelRepo {

    private String DB_NAME = "db_name";
    private Context mContext;
    private ModelDataBase modelDataBase;

    public ModelRepo(Context context){
        this.mContext = context;
        modelDataBase = Room.databaseBuilder(context, ModelDataBase.class, DB_NAME).build();
    }

    public void insert(final String message){
        new InsertTask().execute(message);
    }

    public class InsertTask extends AsyncTask<String, Void, Void>{
        @Override
        protected Void doInBackground(String... objects) {
            Model model = new Model(objects[0]);
            modelDataBase.doDaoModelAccess().insert(model);
            return null;
        }
    }

    public Observable<List<Model>> getModel(){
        return modelDataBase.doDaoModelAccess().getModel();
    }
}
