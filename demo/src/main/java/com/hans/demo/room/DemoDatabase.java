package com.hans.demo.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;



@Database(entities = {User.class}, version = 1)
public abstract class DemoDatabase extends RoomDatabase {

    private static volatile DemoDatabase INSTANCE;

    public abstract UserDao getSomeHahaUserDao();

    public static DemoDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (DemoDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DemoDatabase.class, "demo.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}