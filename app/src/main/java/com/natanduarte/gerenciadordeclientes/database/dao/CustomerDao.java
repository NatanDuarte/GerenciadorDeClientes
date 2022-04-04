package com.natanduarte.gerenciadordeclientes.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.natanduarte.gerenciadordeclientes.model.Customer;

import java.util.List;

@Dao
public interface CustomerDao {
    @Insert
    void save(Customer customer);

    @Update
    void update(Customer customer);

    @Delete
    void delete(Customer customer);

    @Query("SELECT * FROM customers;")
    List<Customer> selectAll();
}
