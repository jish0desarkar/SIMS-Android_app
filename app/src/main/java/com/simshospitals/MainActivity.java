package com.simshospitals;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    ListView listView;
    ArrayList<String> list;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        list=new ArrayList<>();
        list.add("Dummy list Items");
        list.add("Dummy list Items");
        list.add("Dummy list Items");
        list.add("Dummy list Items");
        list.add("Dummy list Items");
        list.add("Dummy list Items");

        final ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.custom_text, list);
        listView.setAdapter(adapter);
listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(MainActivity.this,approval.class));
    }
});
    }
}
