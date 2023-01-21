package com.example.opencvapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.opencvapp.databinding.ActivityMainBinding;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraActivity;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;

import java.util.Collections;
import java.util.List;

public class MainActivity extends CameraActivity {

    private static String LOGtaf = "OpenCV_Log";
    private CameraBridgeViewBase mOpencv;
    static {
        System.loadLibrary("opencvapp");
    }
    public native void RedNeuronal(long gris, long color);

//
//    private ActivityMainBinding binding;


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
            RedNeuronal(CamaraGris.getNativeObjAddr(),CamaraColor.getNativeObjAddr());
            return CamaraColor;
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
    /**
     * A native method that is implemented by the 'opencvapp' native library,
     * which is packaged with this application.
     */
//    public native String stringFromJNI();
}