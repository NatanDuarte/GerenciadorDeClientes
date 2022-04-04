package com.natanduarte.gerenciadordeclientes.ui.activity;

import static com.natanduarte.gerenciadordeclientes.ui.activity.ConstantsActivities.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.natanduarte.gerenciadordeclientes.R;
import com.natanduarte.gerenciadordeclientes.database.CustomerDatabase;
import com.natanduarte.gerenciadordeclientes.database.dao.CustomerDao;
import com.natanduarte.gerenciadordeclientes.model.Customer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomerForm extends AppCompatActivity {

    private EditText nameField;
    private EditText phoneField;
    private EditText emailField;
    private EditText streetField;
    private EditText addressNumberField;
    private EditText cepField;
    private EditText districtField;
    private EditText districtComplementField;
    private EditText cityField;
    private EditText stateField;
    private Customer customer;
    private CustomerDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_form);

        dao = CustomerDatabase
                .getInstance(this)
                .getCustomerDao();

        initFields();
        initCustomer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.customer_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_customer_form_action_done)
            handleSaveButton();

        return super.onOptionsItemSelected(item);
    }

    private void initCustomer() {
        Intent intent = getIntent();

        if (intent.hasExtra(PRODUCT_KEY)) {
            setTitle(APP_TITLE_UPDATE);
            instantiateProductFromExtra(intent);
        } else {
            setTitle(APP_TITLE_NEW_ITEM);
            customer = new Customer();
        }
    }

    private void instantiateProductFromExtra(Intent intent) {
        customer = (Customer) intent.getSerializableExtra(PRODUCT_KEY);
        nameField.setText(customer.getName());
        phoneField.setText(customer.getPhone());
        emailField.setText(customer.getEmail());
        streetField.setText(customer.getStreet());
        addressNumberField.setText(customer.getAddressNumber());
        cepField.setText(customer.getCep());
        districtField.setText(customer.getDistrict());
        districtComplementField.setText(customer.getComplement());
        cityField.setText(customer.getCity());
        stateField.setText(customer.getState());
    }

    private void initFields() {
        nameField = findViewById(R.id.name_field_input);
        phoneField = findViewById(R.id.phone_field);
        emailField = findViewById(R.id.email_field);
        streetField = findViewById(R.id.street_field);
        addressNumberField = findViewById(R.id.address_number_field);
        cepField = findViewById(R.id.cep_field);
        districtField = findViewById(R.id.district_field);
        districtComplementField = findViewById(R.id.district_complement_field);
        cityField = findViewById(R.id.city_field);
        stateField = findViewById(R.id.state_field);
    }

    private void evaluateDaoAction() {
        if (customer.hasValidId())
            dao.update(customer);
        else
            dao.save(customer);
    }

    private void handleSaveButton() {
        if (fieldsAreValid()) {
            populateFields();
            evaluateDaoAction();
            finish();
        }
    }

    private void populateFields() {
        String name = nameField.getText().toString();
        String phone = phoneField.getText().toString();
        String email = emailField.getText().toString();
        String street = streetField.getText().toString();
        String addressNumber = addressNumberField.getText().toString();
        String cep = cepField.getText().toString();
        String district = districtField.getText().toString();
        String districtComplement = districtComplementField.getText().toString();
        String city = cityField.getText().toString();
        String state = stateField.getText().toString();

        customer.setName(name);
        customer.setPhone(phone);
        customer.setEmail(email);
        customer.setStreet(street);
        customer.setAddressNumber(addressNumber);
        customer.setCep(cep);
        customer.setDistrict(district);
        customer.setComplement(districtComplement);
        customer.setCity(city);
        customer.setState(state);
    }

    private boolean fieldsAreValid() {
        List<EditText> requiredFields = new ArrayList(Arrays.asList(
                nameField, cepField, streetField, districtField, cityField, stateField));

        int emptyFields = 0;
        for (EditText field : requiredFields) {
            if (field.getText().toString().equals("") || field.getText() == null) {
                field.setError(getString(R.string.required_field_error_message));
                emptyFields++;
            }
        }
        return emptyFields == 0;
    }
}