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
// ctl set
JNIEXPORT jint JNICALL Java_com_d_1peres_xiph_opus_OpusEncoder_nativeEncoderCtlSet(
        JNIEnv *, jobject, jint, jlong);
// ctl get
JNIEXPORT jint JNICALL Java_com_d_1peres_xiph_opus_OpusEncoder_nativeEncoderCtlGet(
        JNIEnv *, jobject, jint);
// destroy
JNIEXPORT void JNICALL Java_com_d_1peres_xiph_opus_OpusEncoder_nativeDestroy(
        JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif

#endif //OPUS_ANDROID_OPUS_ENCODER_H