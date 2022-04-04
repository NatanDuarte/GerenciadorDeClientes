package com.natanduarte.gerenciadordeclientes.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.natanduarte.gerenciadordeclientes.database.dao.CustomerDao;
import com.natanduarte.gerenciadordeclientes.model.Customer;

@Database(entities = {Customer.class}, version = 1)
public abstract class CustomerDatabase extends RoomDatabase {

    public abstract CustomerDao getCustomerDao();

    public static CustomerDatabase getInstance(Context context) {
        String DATABASE_NAME = "customers.db";
        return Room
                .databaseBuilder(
                        context,
                        CustomerDatabase.class,
                        DATABASE_NAME
                )
                .allowMainThreadQueries()
                .build();
    }
}
