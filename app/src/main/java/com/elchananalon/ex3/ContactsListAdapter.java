package com.elchananalon.ex3;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactsListAdapter extends ArrayAdapter<Contact> {

    ArrayList<Contact> contacts;
    Context context;
    int resource;
    String pNumber;

    public ContactsListAdapter(Context context, int resource, ArrayList<Contact> contacts) {
        super(context, resource, contacts);
        this.contacts = contacts;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // For getting the view of the xml
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        // Actually getting the view
        View view = layoutInflater.inflate(resource, null, false);

        // binding elements to view elements
        ImageView imgPhone = view.findViewById(R.id.imgPhone);
        TextView name = view.findViewById(R.id.txtName);
        TextView number = view.findViewById(R.id.txtNumber);

        //getting the contact of the specified position
        Contact contact = contacts.get(position);

        //adding values to the list
        imgPhone.setImageDrawable(context.getResources().getDrawable(contact.getPhoto()));
        name.setText(contact.getName());
        number.setText(contact.getPhoneNumber());

        pNumber = contact.getPhoneNumber();
        imgPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // if there is a number - open dialer and call
                // Not sure if this is the right way to do it, maybe it's better to do this at MainActivity
                String phoneNumber = "tel:+" + pNumber;

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber));
                //context.startActivity(intent);

            }
        });

        return view;
    }
}
