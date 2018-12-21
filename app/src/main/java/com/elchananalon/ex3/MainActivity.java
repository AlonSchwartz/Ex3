package com.elchananalon.ex3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private Button insert, search;
    private TextView name, phone;
    private ListView contactsView;
    private ArrayList<Contact> contactsList;

    private SQLiteDatabase contactsDB = null;
    private ContactsListAdapter myAdapter; // custom adapter for the list




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insert = findViewById(R.id.btn_insert);
        search = findViewById(R.id.btn_search);
        name = findViewById(R.id.editTxt_Name);
        phone = findViewById(R.id.editTxt_phone);
        contactsView = findViewById(R.id.dlist_contacts);

        insert.setOnClickListener(this);
        search.setOnClickListener(this);

        contactsDB = openOrCreateDatabase("MyContacts", MODE_PRIVATE, null);

        String sql = "CREATE TABLE IF NOT EXISTS contacts (id integer primary key, name VARCHAR, phone VARINT);";
        contactsDB.execSQL(sql);
        contactsList = new ArrayList<>();

        //contactsList.add(new Contact("Jerry", "0224556", R.drawable.phone));
        //contactsList.add(new Contact("Michael", "0022154", R.drawable.phone));
        //contactsList.add(new Contact("James", "", R.drawable.phone));
        //contactsList.add(new Contact("Kim", "", R.drawable.phone));

        loadContacts();

        myAdapter = new ContactsListAdapter(this, R.layout.custom_list_view, contactsList);

        contactsView.setAdapter(myAdapter);

        contactsView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int index, long id)
            {
                System.out.println("====Tests====");
                System.out.println(index);
                System.out.println("Phone Number: "+contactsList.get(index).getPhoneNumber());


            }
        });


    }

    public void onClick(View v)
    {

        switch (v.getId())
        {
            case R.id.btn_insert:
            {
                //insert contact only if there is contact name
                if (!(name.getText().toString().equals("")))
                {
                    insertContact();
                }
                else{
                    // not sure if needed. we need to ask Ilan if its ok to have a contact without name
                    Toast.makeText(this, "Please enter contact name", Toast.LENGTH_SHORT).show();
                }

                break;
            }

            case R.id.btn_search:
            {

                break;
            }

        }
    }

    /*****************************************************************/

    public void loadContacts(){

        // A Cursor provides read and write access to database results
        String sql = "SELECT * FROM contacts";
        Cursor cursor = contactsDB.rawQuery(sql, null);

        // Get the index for the column name provided
        int nameColumn = cursor.getColumnIndex("name");
        int phoneColumn = cursor.getColumnIndex("phone");

        // Move to the first row of results
        cursor.moveToFirst();

        // Verify that we have data
        if(cursor != null && (cursor.getCount() > 0)){
            // As long we have data - get it and add it to the contacts list
            do{
                String contactName = cursor.getString(nameColumn);
                String contactPhone = cursor.getString(phoneColumn);

                contactsList.add(new Contact(contactName, contactPhone, R.drawable.phone));

            }while(cursor.moveToNext());
        }
    }

    /*****************************************************************/

    public void insertContact() {

        // Get the contact name and phone entered
        String contactName = name.getText().toString();
        String contactPhone = phone.getText().toString();

        // Execute SQL statement to insert new data
        String sql = "INSERT INTO contacts (name, phone) VALUES ('" + contactName + "', '" + contactPhone + "');";
        contactsDB.execSQL(sql);

    }


}
