package com.jr_dev.loco_do;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private EditText addTxt;
    private Button addBtn;
    private ListView itemLst;

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addTxt = (EditText)findViewById(R.id.addTxt);
        addBtn = (Button)findViewById(R.id.addBtn);
        itemLst = (ListView)findViewById(R.id.itemLst);
        items = File_Helper.readData(this);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);

        itemLst.setAdapter(adapter);

        itemLst.setOnItemClickListener(this);
    }



    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addBtn:

                        String item = addTxt.getText().toString();
                    if (item.length() >0) {
                        adapter.add(item);
                        addTxt.setText("");
                        try {
                            File_Helper.writeData(items, this);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(this, "Item Added", Toast.LENGTH_LONG).show();
                        break;
                    }
                    else {
                        Toast.makeText(this,"Please Add Item", Toast.LENGTH_LONG).show();
                        break;
                    }

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        items.remove(position);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Item Deleted", Toast.LENGTH_LONG).show();
    }
}
