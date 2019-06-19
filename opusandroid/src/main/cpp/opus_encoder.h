//
// Created by Deilson on 10/06/2019.
//
#ifndef OPUS_ANDROID_OPUS_ENCODER_H
#define OPUS_ANDROID_OPUS_ENCODER_H

#include <jni.h>
#include "opus.h"

#ifdef __cplusplus
extern "C" {
#endif

OpusEncoder *get_encoder(JNIEnv *, jobject);

JNIEXPORT jint JNICALL Java_com_d_1peres_xiph_opus_OpusEncoder_nativeInit(
        JNIEnv *, jobject, jint, jint, jint);
// encode shorts
JNIEXPORT jint JNICALL Java_com_d_1peres_xiph_opus_OpusEncoder_nativeEncodeShorts(
        JNIEnv *, jobject, jshortArray, jint, jbyteArray);
// encode bytes
JNIEXPORT jint JNICALL Java_com_d_1peres_xiph_opus_OpusEncoder_nativeEncodeBytes(
        JNIEnv *, jobject, jbyteArray, jint, jbyteArray);
// ctl -> bitrate
JNIEXPORT jint JNICALL Java_com_d_1peres_xiph_opus_OpusEncoder_ctlSetBitrate(
        JNIEnv *, jobject, jint);
// ctl -> complexity
JNIEXPORT jint JNICALL Java_com_d_1peres_xiph_opus_OpusEncoder_ctlSetComplexity(
        JNIEnv *, jobject, jint);
// ctl -> vbr
JNIEXPORT jint JNICALL Java_com_d_1peres_xiph_opus_OpusEncoder_ctlEnableVbr(
        JNIEnv *, jobject, jboolean);
// destroy
JNIEXPORT void JNICALL Java_com_d_1peres_xiph_opus_OpusEncoder_nativeDestroy(
        JNIEnv *, jobject);
// generic ctl test
JNIEXPORT jint JNICALL Java_com_d_1peres_xiph_opus_OpusEncoder_nativeEncoderCtl(
        JNIEnv *, jobject, jint, jlong);

#ifdef __cplusplus
}
#endif

#endif //OPUS_ANDROID_OPUS_ENCODER_H