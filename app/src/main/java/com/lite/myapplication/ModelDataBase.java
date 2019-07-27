package com.lite.myapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Created by Saket on 27,July,2019
 */

/**
 *  Multiple tables can be added inside entities.
 */
@Database(entities = {Model.class, MessageDB.class}, exportSchema = false, version = 1)
public abstract class ModelDataBase extends RoomDatabase {

    public abstract ModelDAO doDaoModelAccess();

}
