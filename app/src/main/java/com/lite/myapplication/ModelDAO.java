package com.lite.myapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Saket on 27,July,2019
 */

@Dao
public interface ModelDAO {

    /**
     * use Long return type when id is needed of created row.
     * @param model
     */
    @Insert
    void insert(Model model);

    @Query("SELECT * FROM Model")
    public Observable<List<Model>> getModel();

    @Query("SELECT * FROM MessageDB WHERE messageId = :id")
    public LiveData<List<MessageDB>> getMessage(int id);
}
