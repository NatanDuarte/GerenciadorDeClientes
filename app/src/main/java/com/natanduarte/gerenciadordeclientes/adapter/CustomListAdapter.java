package com.natanduarte.gerenciadordeclientes.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.natanduarte.gerenciadordeclientes.R;
import com.natanduarte.gerenciadordeclientes.model.Customer;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private final Context context;
    private final List<Customer> customers;

    public CustomListAdapter(Context context, List<Customer> customers) {
        this.context = context;
        this.customers = customers;
    }

    @Override
    public int getCount() {
        return customers.size();
    }

    @Override
    public Object getItem(int position) {
        return customers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return customers.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder") View newView = LayoutInflater
                .from(context)
                .inflate(R.layout.item_customer, viewGroup, false);

        TextView customerNameField = newView.findViewById(R.id.costumer_name_field);
        TextView customerCityField = newView.findViewById(R.id.costumer_city_field);

        customerNameField.setText(customers.get(position).getName());
        customerCityField.setText(
                String.format(
                        "%s - %s",
                        customers.get(position).getCity(),
                        customers.get(position).getState()
                )
        );
        return newView;
    }

    public void clear() {
        customers.clear();
    }

    public void addAll(List<Customer> allCustomers) {
        customers.addAll(allCustomers);
    }

    public void remove(Customer customer) {
        customers.remove(customer);
    }

}
