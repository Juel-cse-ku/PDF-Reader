 package com.example.pdf_reader;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.krishna.fileloader.FileLoader;
import com.krishna.fileloader.listener.FileRequestListener;
import com.krishna.fileloader.pojo.FileResponse;
import com.krishna.fileloader.request.FileLoadRequest;

import java.io.File;

 public class PDFViewerActivity extends AppCompatActivity {
public static final String API_URL = "https://gahp.net/wp-content/uploads/2017/09/sample.pdf";
    PDFView pdfView;
    ProgressBar fileDownloadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview);

        pdfView = findViewById(R.id.pdfView);
        fileDownloadingPB = findViewById(R.id.file_downloading_pb);

        fileDownloadingPB.setVisibility(View.VISIBLE);

        FileLoader.with(this)
                .load(API_URL)
                .asFile(new FileRequestListener<File>() {
                    @Override
                    public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                        fileDownloadingPB.setVisibility(View.GONE);
                        File fileToRead = response.getBody();

                        pdfView.fromFile(fileToRead)
                                .enableSwipe(true)
                                .enableAnnotationRendering(true)
                                .enableDoubletap(true)
                                .scrollHandle(new DefaultScrollHandle(PDFViewerActivity.this))
                                .onRender(new OnRenderListener() {
                                    @Override
                                    public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight) {
                                        pdfView.fitToWidth();
                                    }
                                })
                                .invalidPageColor(Color.WHITE)
                                .load();

                    }

                    @Override
                    public void onError(FileLoadRequest request, Throwable t) {
                        fileDownloadingPB.setVisibility(View.GONE);
                        Toast.makeText(PDFViewerActivity.this, ""+ t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
 }
