package com.natanduarte.gerenciadordeclientes.ui.activity;

import static com.natanduarte.gerenciadordeclientes.ui.activity.ConstantsActivities.PRODUCT_KEY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.natanduarte.gerenciadordeclientes.R;
import com.natanduarte.gerenciadordeclientes.adapter.CustomListAdapter;
import com.natanduarte.gerenciadordeclientes.database.CustomerDatabase;
import com.natanduarte.gerenciadordeclientes.database.dao.CustomerDao;
import com.natanduarte.gerenciadordeclientes.model.Customer;

public class CustomerList extends AppCompatActivity {

    private CustomerDao dao;
    private CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        setTitle(getString(R.string.customer_list_title));

        dao = CustomerDatabase
                .getInstance(this)
                .getCustomerDao();

        initFAB();
    }

    @Override
    protected void onResume() {
        super.onResume();
        handleListView();
    }

    @Override
    public void onCreateContextMenu(
            ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.customers_list_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_customer_list_action_remove) {
            AdapterView.AdapterContextMenuInfo menuInfo =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Customer customer = (Customer) adapter.getItem(menuInfo.position);
            dao.delete(customer);
            adapter.remove(customer);
            adapter.notifyDataSetChanged();
        }
        return super.onContextItemSelected(item);
    }

    private void handleListView() {
        ListView customersList = findViewById(R.id.customersList);
        adapter = new CustomListAdapter(this, dao.selectAll());
        customersList.setAdapter(adapter);

        customersList.setOnItemClickListener(
                (adapterView, view, position, id) ->
                        openUpdateForm(adapterView, position));
        registerForContextMenu(customersList);
    }

    private void openUpdateForm(@NonNull AdapterView<?> adapterView, int position) {
        Customer customer = (Customer) adapterView.getItemAtPosition(position);
        Intent intent = new Intent(this, CustomerForm.class);
        intent.putExtra(PRODUCT_KEY, customer);
        startActivity(intent);
    }

    private void initFAB() {
        FloatingActionButton fabNewCustomer =
                findViewById(R.id.costumers_list_fab_add_new);

        fabNewCustomer.setOnClickListener(view -> {
            Intent intent = new Intent(this, CustomerForm.class);
            startActivity(intent);
        });
    }
}