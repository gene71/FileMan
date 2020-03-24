package com.example.fileman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "fileman";
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //log
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.filelist);
        FileUtil fu = new FileUtil();

        try {

            //fu.getAllFilePathsExtensionDetails();
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, fu.getAllFilePathsExtensionDetails());
            listView.setAdapter(arrayAdapter);
        } catch (Exception ex){
            Log.d("Files", ex.getMessage());
        }

    }

}
