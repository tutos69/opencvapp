#include <jni.h>
#include <iostream>
#include <cstdlib>
#include <string>
#include <vector>

#include <opencv2/core.hpp>
#include <opencv2/imgproc.hpp>
#include <opencv2/highgui.hpp>
#include <opencv2/imgcodecs.hpp>
#include <opencv2/video.hpp>
#include <opencv2/videoio.hpp>

using  namespace cv;

extern "C"{
    JNIEXPORT void JNICALL Java_com_example_opencvapp_MainActivity_RedNeuronal(JNIEnv* jniEnv, jobject,jlong Gris, jlong Color ){
        Mat* imGris = (Mat*)Gris;
        Mat* imColor = (Mat*)Color;
        std::vector<Point> puntos;
        goodFeaturesToTrack(*imGris,puntos,20,0.01,10,Mat(),3, false);
        for (int i = 0; i < puntos.size(); ++i) {
            circle(*imColor,puntos[i],10,Scalar(0,255,0),2);
        }
    }
}