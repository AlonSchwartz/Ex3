package com.elchananalon.ex3;

import android.content.Intent;
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
        contactsView = findViewById(R.id.dlist_contacts);

        insert.setOnClickListener(this);
        search.setOnClickListener(this);

        contactsDB = openOrCreateDatabase("MyContacts", MODE_PRIVATE, null);

        String sql = "CREATE TABLE IF NOT EXISTS contacts (id integer primary key, name VARCHAR, phone VARINT);";
        contactsDB.execSQL(sql);
        contactsList = new ArrayList<>();

        contactsList.add(new Contact("Jerry", "0224556", R.drawable.phone));
        contactsList.add(new Contact("Michael", "0022154", R.drawable.phone));

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

                break;
            }

            case R.id.btn_search:
            {

                break;
            }

        }


    }


}
