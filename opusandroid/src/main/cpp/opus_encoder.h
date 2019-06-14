//
// Created by Deilson on 10/06/2019.
//
#ifndef OPUS_ANDROID_OPUS_ENCODER_H
#define OPUS_ANDROID_OPUS_ENCODER_H

#define JAVA_PREFIX Java_com_d_1peres_xiph_opus_OpusEncoder_

#include <jni.h>
#include "opus.h"

#ifdef __cplusplus
extern "C" {
#endif

OpusEncoder *get_encoder(JNIEnv *, jobject);

JNIEXPORT jint JNICALL Java_com_d_1peres_xiph_opus_OpusEncoder_1native_1init(
        JNIEnv *, jobject, jint, jint, jint);
// encode shorts
JNIEXPORT jint JNICALL Java_com_d_1peres_xiph_opus_OpusEncoder_native_1encode_1shorts(
        JNIEnv *, jobject, jshortArray, jint, jbyteArray);
// encode bytes
JNIEXPORT jint JNICALL Java_com_d_1peres_xiph_opus_OpusEncoder_native_1encode_1bytes(
        JNIEnv *, jobject, jbyteArray, jint, jbyteArray);
// destroy
JNIEXPORT void JNICALL Java_com_d_1peres_xiph_opus_OpusEncoder_native_1destroy(
        JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif

#endif //OPUS_ANDROID_OPUS_ENCODER_H