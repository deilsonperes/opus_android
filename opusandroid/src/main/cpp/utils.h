//
// Created by Deilson on 11/06/2019.
//

#ifndef OPUS_ANDROID_UTILS_H
#define OPUS_ANDROID_UTILS_H

#include <android/log.h>

#define TAG "D_OPUS_STATUS"
#define loge(...) __android_log_print(ANDROID_LOG_ERROR,    TAG, __VA_ARGS__)
#define logw(...) __android_log_print(ANDROID_LOG_WARN,     TAG, __VA_ARGS__)
#define logi(...) __android_log_print(ANDROID_LOG_INFO,     TAG, __VA_ARGS__)
#define logd(...) __android_log_print(ANDROID_LOG_DEBUG,    TAG, __VA_ARGS__)

#endif //OPUS_ANDROID_UTILS_H
