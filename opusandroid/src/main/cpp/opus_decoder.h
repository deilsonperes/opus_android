//
// Created by Deilson on 11/06/2019.
//
#ifndef OPUS_ANDROID_OPUS_DECODER_H
#define OPUS_ANDROID_OPUS_DECODER_H

#define OPUS_MONO 1
#define OPUS_STEREO 2

#include <jni.h>
#include "opus.h"
#include "utils.h"

#ifdef __cplusplus
extern "C" {
#endif

OpusDecoder *get_decoder(JNIEnv *, jobject);

jint get_num_channels(JNIEnv*, jobject);

JNIEXPORT jint JNICALL Java_com_d_1peres_xiph_opus_OpusDecoder_nativeInit(
        JNIEnv *, jobject, jint, jint);

JNIEXPORT jint JNICALL Java_com_d_1peres_xiph_opus_OpusDecoder_nativeDecode(
        JNIEnv *, jobject, jbyteArray, jint, jshortArray);

JNIEXPORT jlong JNICALL Java_com_d_1peres_xiph_opus_OpusDecoder_nativeDecoderCtl(
        JNIEnv *, jobject, jint, jlong);

JNIEXPORT void JNICALL Java_com_d_1peres_xiph_opus_OpusDecoder_nativeDestroy(
        JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif

#endif //OPUS_ANDROID_OPUS_DECODER_H
