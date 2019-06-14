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

JNIEXPORT jint JNICALL Java_com_d_1peres_xiph_opus_OpusDecoder_native_1init(
        JNIEnv *, jobject, jint, jint);

JNIEXPORT jint JNICALL Java_com_d_1peres_xiph_opus_OpusDecoder_native_1decode(
        JNIEnv *, jobject, jbyteArray, jint, jshortArray);

JNIEXPORT void JNICALL Java_com_d_1peres_xiph_opus_OpusDecoder_native_1destroy(
        JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif

#endif //OPUS_ANDROID_OPUS_DECODER_H
