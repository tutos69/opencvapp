package com.example.opencvapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraActivity;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class Movimiento extends CameraActivity {

    private static String LOGtaf = "OpenCV_Log";
    private CameraBridgeViewBase mOpencv;
    private File cascade;
    static {
        System.loadLibrary("opencvapp");
    }
    public native void MovimientoResta(long gris,long frameAnterior);




    private BaseLoaderCallback mCargarC = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status){
                case LoaderCallbackInterface.SUCCESS:{
                    Log.v(LOGtaf,"OpenCV Iniciado");
                    mOpencv.enableView();
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;

            }
            super.onManagerConnected(status);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);
        RegresarHJog();

        mOpencv = (CameraBridgeViewBase) findViewById(R.id.openCV_surface_view);
        mOpencv.setVisibility(SurfaceView.VISIBLE);
        mOpencv.setCvCameraViewListener(cvCameraViewListener);

    }

    @Override
    protected List<?extends CameraBridgeViewBase> getCameraViewList(){
        return Collections.singletonList(mOpencv);
    }

    private CameraBridgeViewBase.CvCameraViewListener2 cvCameraViewListener = new CameraBridgeViewBase.CvCameraViewListener2() {
        @Override
        public void onCameraViewStarted(int width, int height) {

        }

        @Override
        public void onCameraViewStopped() {

        }

        @Override
        public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
            Mat CamaraColor = inputFrame.rgba();
            Mat CamaraGris = inputFrame.gray();

            MovimientoResta(CamaraGris.getNativeObjAddr(),CamaraColor.getNativeObjAddr());
            return CamaraGris;
        }
    };


    @Override
    public void onPause(){
        super.onPause();
        if(mOpencv!=null){
            mOpencv.disableView();
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if(!OpenCVLoader.initDebug()){
            Log.d(LOGtaf,"Mensaje Opencv");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION,this,mCargarC);

        }else{
            mCargarC.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(mOpencv!=null){
            mOpencv.disableView();
        }
    }
    public void RegresarHJog(){
        Button regresar = (Button) findViewById(R.id.butRegresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(Movimiento.this,Menu.class);
                startActivity(toy);
            }
        });
    }
}