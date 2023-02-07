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
#include <opencv2/objdetect.hpp>
//#include <opencv2/video/tracking.hpp>
#include <opencv2/objdetect/objdetect.hpp>
#include <opencv2/video/detail/tracking.detail.hpp>
#include <opencv2/features2d.hpp>

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

    CascadeClassifier cascadeClassifier;
//    FaceRecognizerSF ;

    JNIEXPORT void JNICALL Java_com_example_opencvapp_MainActivity_DetectorDeCaras(JNIEnv* jniEnv, jobject, jstring archisssvo){
        const char * jnamestr = jniEnv->GetStringUTFChars(archisssvo,NULL);
        std::string filePath(jnamestr);
        cascadeClassifier.load(filePath);

    }

    JNIEXPORT void JNICALL Java_com_example_opencvapp_MainActivity_RedNeuronal2(JNIEnv* jniEnv, jobject,jlong Gris, jlong Color ){
    Mat* imGris = (Mat*)Gris;
//    Mat* imGris2 = (Mat*)Color;
    Mat* imColor = (Mat*)Color;
    std::vector<Rect> caras;
        cascadeClassifier.detectMultiScale(*imGris,caras);
        for (int i = 0; i < caras.size(); ++i) {
            rectangle(*imColor,Point(caras[i].x,caras[i].y),Point(caras[i].x+caras[i].width,caras[i].y+caras[i].height),Scalar(0,0,255),2);
        }
    }



}
extern "C"
JNIEXPORT void JNICALL
    Java_com_example_opencvapp_HOG_HOG(JNIEnv *env, jobject thiz, jlong gris, jlong color) {
        Mat* imGris = (Mat*)gris;
        Mat* imColor = (Mat*)color;
        cv::HOGDescriptor hog;
        hog.setSVMDetector(cv::HOGDescriptor::getDefaultPeopleDetector());
        std::vector<Rect> detecciones;
        hog.detectMultiScale(* imGris, detecciones, 0, Size(8, 8), Size(32, 32), 1.05, 2);
        for (auto&detecciones:detecciones){
            rectangle (* imColor,detecciones.tl(),detecciones.br(),Scalar(255,0,0),2);
        }
    }

Mat frameAnterior2;
Mat restaBlanco;

extern "C"
JNIEXPORT void JNICALL


Java_com_example_opencvapp_Movimiento_MovimientoResta(JNIEnv *env, jobject thiz, jlong gris,jlong frameAnterior) {
//    Mat* imGris = (Mat*)gris;
    Mat* imGris2 = (Mat*)gris;
    Mat imgis = imGris2->clone();
    Mat* resta;
   if(frameAnterior2.empty()){
       frameAnterior2 = imgis.clone();
   }



    absdiff(* imGris2, frameAnterior2,*imGris2);
//    equalizeHist(*imGris2, *imGris2);
    frameAnterior2 = imgis.clone();
//    Ptr<CLAHE> clahe = createCLAHE();
//    clahe->setClipLimit(2);
//    clahe->apply(*imGris2, *imGris2);
    Mat kernel;
    kernel = cv::getStructuringElement(MORPH_ELLIPSE, Size(3, 3));
    cv::erode(*imGris2, *imGris2,kernel);



}

