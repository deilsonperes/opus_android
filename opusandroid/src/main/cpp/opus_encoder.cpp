

// Created by Deilson on 10/06/2019.

#include "utils.h"
#include "opus_encoder.h"

#ifdef __cplusplus
extern "C" {
#endif

OpusEncoder* get_encoder(JNIEnv *env, jobject obj)
{
    jclass cls = env->GetObjectClass(obj);
    jfieldID fid = env->GetFieldID(cls, "enc_address", "J");
    return reinterpret_cast<OpusEncoder *>(env->GetLongField(obj, fid));
}

JNIEXPORT jint JNICALL Java_com_d_1peres_xiph_opus_OpusEncoder_native_1init(
        JNIEnv *env, jobject obj, jint sample_rate,jint num_channels,jint application)
{
    // Create the encoder state struct
    int result;
    OpusEncoder* enc;
    enc = opus_encoder_create(sample_rate, num_channels, application, &result);
    if(result != OPUS_OK)
    {
        // opus_create error
        return result;
    }

    // Initialize the encoder
    result = opus_encoder_init(enc, sample_rate, num_channels, application);
    if(result != OPUS_OK)
    {
        // opus_init error
        opus_encoder_destroy(enc);
        return result;
    }

    // Store encoder state on java class
    jclass cls = env->GetObjectClass(obj);
    jfieldID fid = env->GetFieldID(cls, "enc_address", "J");
    env->SetLongField(obj, fid, reinterpret_cast<jlong>(enc));

    return OPUS_OK;
}
// enc. shorts
JNIEXPORT jint JNICALL Java_com_d_1peres_xiph_opus_OpusEncoder_nativeEncodeShorts(
        JNIEnv *env, jobject obj, jshortArray pcm_in, jint num_samples, jbyteArray opus_out)
{
    int encoded_bytes;
    OpusEncoder* enc = get_encoder(env, obj);

    int max_out_len = env->GetArrayLength(opus_out);
    short* input  = env->GetShortArrayElements(pcm_in, JNI_FALSE);
    auto * output = reinterpret_cast<unsigned char *>(env->GetByteArrayElements(opus_out, JNI_FALSE));

    encoded_bytes = opus_encode(enc, input, num_samples, output, max_out_len);

    env->ReleaseShortArrayElements(pcm_in, input, JNI_ABORT);
    env->ReleaseByteArrayElements(opus_out, reinterpret_cast<jbyte *>(output), 0);

    return encoded_bytes;
}
// enc. bytes
#pragma clang diagnostic push
#pragma ide diagnostic ignored "hicpp-signed-bitwise"
JNIEXPORT jint JNICALL Java_com_d_1peres_xiph_opus_OpusEncoder_nativeEncodeBytes(
        JNIEnv *env, jobject obj, jbyteArray in_buff, jint num_samples, jbyteArray out_buff)
{
    OpusEncoder* enc = get_encoder(env, obj);

    int out_buff_size = env->GetArrayLength(out_buff);
    int short_samples = num_samples / 2;

    if(num_samples % 2 != 0)
    {
        // if not multiple of 2
        loge("Array size must be multiple of 2 (size: %d bytes)", num_samples);
        return OPUS_BAD_ARG;
    }

    // get the data to c++ world
    /*       input buffer in shorts     */
    short in_buff_shorts[short_samples];

    /*  java buffers  */
    auto* in_buff_ptr = reinterpret_cast<char*>(env->GetByteArrayElements(in_buff, JNI_FALSE));
    auto* out_buff_ptr = reinterpret_cast<unsigned char*>(env->GetByteArrayElements(out_buff, JNI_FALSE));

    // convert from char* to short*
    for(int i = 0; i < short_samples; i++)
    {
        // Conversion process: iterate through the short buffer, retrieving the bytes in pairs, with
        // the first byte being at position i * 2 and the second at i * 2 + 1, so the iteration in
        // the byte array occurs 2 by 2
        //                    |    (first byte) << 8     |  |       second byte        |
        in_buff_shorts[i] =  (in_buff_ptr[i * 2] << 8) | (in_buff_ptr[i * 2 + 1] & 0x00ff);
    }

    // encode the data
    int encoded_bytes = opus_encode(enc, in_buff_shorts, short_samples, out_buff_ptr, out_buff_size);

    // release the java buffers
    env->ReleaseByteArrayElements(in_buff, reinterpret_cast<jbyte*>(in_buff_ptr), 0);
    env->ReleaseByteArrayElements(out_buff, reinterpret_cast<jbyte*>(out_buff_ptr), 0);

    // return the result
    return encoded_bytes;
}
#pragma clang diagnostic pop
//ctl -> bitrate
JNIEXPORT jint JNICALL Java_com_d_1peres_xiph_opus_OpusEncoder_ctlSetBitrate(
        JNIEnv *env, jobject obj, jint bitrate)
{
    OpusEncoder* enc = get_encoder(env, obj);
    return opus_encoder_ctl(enc, OPUS_SET_BITRATE(bitrate));
}
// ctl -> complexity
JNIEXPORT jint JNICALL Java_com_d_1peres_xiph_opus_OpusEncoder_ctlSetComplexity(
        JNIEnv *env, jobject obj, jint complexity)
{
    OpusEncoder* enc = get_encoder(env, obj);
    return opus_encoder_ctl(enc, OPUS_SET_COMPLEXITY(complexity));
}
// ctl -> vbr
JNIEXPORT jint JNICALL Java_com_d_1peres_xiph_opus_OpusEncoder_ctlEnableVbr(
        JNIEnv *env, jobject obj, jboolean enable){
    OpusEncoder* enc = get_encoder(env, obj);
    return opus_encoder_ctl(enc, OPUS_SET_VBR(enable));
}
// destroy
JNIEXPORT void JNICALL Java_com_d_1peres_xiph_opus_OpusEncoder_nativeDestroy(JNIEnv *env, jobject obj)
{
    OpusEncoder* enc = get_encoder(env, obj);
    opus_encoder_destroy(enc);

    jclass cls = env->GetObjectClass(obj);
    jfieldID fid = env->GetFieldID(cls, "enc_address", "J");
    env->SetLongField(obj, fid, (jlong) NULL);
}

#ifdef __cplusplus
};
#endif