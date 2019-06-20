//
// Created by Deilson on 11/06/2019.
//

#include "opus_decoder.h"

#ifdef __cplusplus
extern "C" {
#endif

OpusDecoder *get_decoder(JNIEnv *env, jobject obj)
{
    jclass cls = env->GetObjectClass(obj);
    jfieldID fid = env->GetFieldID(cls, "dec_address", "J");
    return reinterpret_cast<OpusDecoder *>(env->GetLongField(obj, fid));
}

jint get_num_channels(JNIEnv* env, jobject obj) {
    jclass cls = env->GetObjectClass(obj);
    jfieldID fid = env->GetFieldID(cls, "num_channels", "I");
    return env->GetIntField(obj, fid);
}

JNIEXPORT jint JNICALL Java_com_d_1peres_xiph_opus_OpusDecoder_nativeInit(
        JNIEnv *env, jobject obj, jint sample_rate, jint num_channels)
{
    int result;
    OpusDecoder *dec = opus_decoder_create(sample_rate, num_channels, &result);
    if (result != OPUS_OK) {
        return result;
    }

    result = opus_decoder_init(dec, sample_rate, num_channels);
    if (result != OPUS_OK) {
        opus_decoder_destroy(dec);
        return result;
    }

    jclass cls = env->GetObjectClass(obj);
    jfieldID fid = env->GetFieldID(cls, "dec_address", "J");
    env->SetLongField(obj, fid, reinterpret_cast<jlong>(dec));

    fid = env->GetFieldID(cls, "num_channels", "I");
    env->SetIntField(obj, fid, num_channels);

    return result;
}

JNIEXPORT jint JNICALL Java_com_d_1peres_xiph_opus_OpusDecoder_nativeDecode(
        JNIEnv *env, jobject obj, jbyteArray opus_in, jint opus_num_samples, jshortArray pcm_out)
{
    OpusDecoder* dec = get_decoder(env, obj);
    int num_channels = get_num_channels(env, obj);
    int out_size = env->GetArrayLength(pcm_out);

    auto* opus_data = reinterpret_cast<const unsigned char*>(env->GetByteArrayElements(opus_in, JNI_FALSE));
    auto* native_pcm = env->GetShortArrayElements(pcm_out, JNI_FALSE);

    /* input
     * const char* opus_in
     * int opus_num_samples
     *
     * max_size is the max duration of the frame in samples (per channel)
     * that can fit into the decoded_frame array
     */
    int max_size = out_size / num_channels;
    int result = opus_decode(dec, opus_data, opus_num_samples, native_pcm, max_size, 0);

    // Release arrays
    env->ReleaseByteArrayElements(opus_in, (jbyte *) opus_data, 0);
    env->ReleaseShortArrayElements(pcm_out, native_pcm, 0);

    return result;
}

JNIEXPORT jlong JNICALL Java_com_d_1peres_xiph_opus_OpusDecoder_nativeDecoderCtl(
        JNIEnv *env, jobject obj, jint ctl, jlong arg)
{
    OpusDecoder* dec = get_decoder(env, obj);
    auto oarg = static_cast<opus_int32>(arg);

    switch (ctl) {
        // generic ctls (GET_FINAL_RANGE is not useful)
        case OPUS_RESET_STATE:
            oarg = opus_decoder_ctl(dec, OPUS_RESET_STATE);
            break;

        case OPUS_GET_BANDWIDTH_REQUEST:
            opus_decoder_ctl(dec, OPUS_GET_BANDWIDTH(&oarg));
            break;

        case OPUS_GET_SAMPLE_RATE_REQUEST:
            opus_decoder_ctl(dec, OPUS_GET_SAMPLE_RATE(&oarg));
            break;

        case OPUS_SET_PHASE_INVERSION_DISABLED_REQUEST:
            opus_decoder_ctl(dec, OPUS_SET_PHASE_INVERSION_DISABLED(oarg));
            break;

        case OPUS_GET_PHASE_INVERSION_DISABLED_REQUEST:
            opus_decoder_ctl(dec, OPUS_GET_PHASE_INVERSION_DISABLED(&oarg));
            break;

            // decoder ctls
        case OPUS_SET_GAIN_REQUEST:
            oarg = opus_decoder_ctl(dec, OPUS_SET_GAIN(oarg));
            break;

        case OPUS_GET_GAIN_REQUEST:
            opus_decoder_ctl(dec, OPUS_GET_GAIN(&oarg));
            break;

        case OPUS_GET_LAST_PACKET_DURATION_REQUEST:
            opus_decoder_ctl(dec, OPUS_GET_LAST_PACKET_DURATION(&oarg));
            break;

        case OPUS_GET_PITCH_REQUEST:
            opus_decoder_ctl(dec, OPUS_GET_PITCH(&oarg));
            break;

        default:
            // if ctl is not on the list, return bad argumend
            return OPUS_BAD_ARG;
    }

    return oarg;
}

JNIEXPORT void JNICALL Java_com_d_1peres_xiph_opus_OpusDecoder_nativeDestroy(
        JNIEnv *env, jobject obj)
{
    OpusDecoder *dec = get_decoder(env, obj);
    opus_decoder_destroy(dec);

    jclass cls = env->GetObjectClass(obj);
    jfieldID fid = env->GetFieldID(cls, "dec_address", "J");
    env->SetLongField(obj, fid, 0);
}

#ifdef __cplusplus
}
#endif
