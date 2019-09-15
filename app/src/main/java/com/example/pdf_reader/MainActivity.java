package com.example.pdf_reader;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<File> pdf_file_list = new ArrayList<>();
    public static int REQUEST_PERMISSION = 1;
    boolean boolean_permission;
    ListView pdf_file_list_view;
    MyArrayAdapter pdf_adapter;
    File dir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pdf_file_list_view = findViewById(R.id.pdf_file_list_lv);
        Intent intent = new Intent(MainActivity.this, PDFViewerActivity.class);
        startActivity(intent);

//        permission_fn();
//
//        pdf_file_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getApplicationContext(), PDFViewerActivity.class);
//                intent.putExtra("position", position);
//
//                startActivity(intent);
//            }
//        });
    }

    private void permission_fn() {
        if ((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)){
            if (!ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
            }
        } else {
            boolean_permission = true;
            getFile(dir);
            pdf_adapter = new MyArrayAdapter(getApplicationContext(), pdf_file_list);
            pdf_file_list_view.setAdapter(pdf_adapter);
        }
    }

    public ArrayList<File> getFile(File dir) {
        File[] listFile = dir.listFiles();

        if (listFile != null && listFile.length > 0) {
            for (File file : listFile) {

                if (file.isDirectory()) {
                    getFile(file);
                } else {
                    boolean boolean_pdf = false;

                    if (file.getName().endsWith(".pdf")) {
                        for (int j = 0; j < pdf_file_list.size(); j++) {
                            if (pdf_file_list.get(j).getName().equals(file.getName())) {
                                boolean_pdf = true;
                            }
                        }
                        if (!boolean_pdf) {
                            pdf_file_list.add(file);
                        }
                    }
                }
            }
        }
        return pdf_file_list;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSION){

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                boolean_permission = true;
                getFile(dir);
                pdf_adapter = new MyArrayAdapter(getApplicationContext(), pdf_file_list);
                pdf_file_list_view.setAdapter(pdf_adapter);
            } else {
                Toast.makeText(this, "Please Allow Permission.", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        finish();
        return true; // super.onKeyDown(keyCode, event);
    }

}
