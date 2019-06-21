package com.d_peres.xiph.opus;

import com.d_peres.xiph.opus.annotation.Application;
import com.d_peres.xiph.opus.annotation.Channels;
import com.d_peres.xiph.opus.annotation.SampleRate;

import static com.d_peres.xiph.opus.OpusConstants.OPUS_GET_APPLICATION;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_GET_BANDWIDTH;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_GET_BITRATE;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_GET_COMPLEXITY;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_GET_DTX;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_GET_EXPERT_FRAME_DURATION;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_GET_FINAL_RANGE;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_GET_FORCE_CHANNELS;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_GET_INBAND_FEC;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_GET_IN_DTX;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_GET_LOOKAHEAD;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_GET_LSB_DEPTH;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_GET_MAX_BANDWIDTH;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_GET_PACKET_LOSS_PERC;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_GET_PHASE_INVERSION_DISABLED;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_GET_PREDICTION_DISABLED;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_GET_SAMPLE_RATE;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_GET_SIGNAL;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_GET_VBR;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_GET_VBR_CONSTRAINT;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_RESET_STATE;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_SET_APPLICATION;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_SET_BANDWIDTH;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_SET_COMPLEXITY;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_SET_DTX;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_SET_EXPERT_FRAME_DURATION;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_SET_FORCE_CHANNELS;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_SET_INBAND_FEC;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_SET_LSB_DEPTH;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_SET_MAX_BANDWIDTH;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_SET_PACKET_LOSS_PERC;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_SET_PREDICTION_DISABLED;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_SET_SIGNAL;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_SET_VBR;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_SET_VBR_CONSTRAINT;

@SuppressWarnings("unused")
public class OpusEncoder {
	
	private long enc_address;
	
	static {
		System.loadLibrary("jniopus");
	}

	/* ********** native methods ********** */
	private native int nativeInit(int sample_rate, int num_channels, int opus_application);
	private native int nativeEncodeShorts(short[] in, int num_samples, byte[] out);
	private native int nativeEncodeBytes(byte[] in, int num_samples, byte[] out);
	private native int nativeEncoderCtlSet(int ctl, long value);
	private native int nativeEncoderCtlGet(int ctl);
	private native void nativeDestroy();

	//todo: add documentation
	public OpusEncoder(
			@SampleRate int sampleRate,
			@Channels int numChannels,
			@Application int opusApplication) {
		OpusError.throwOnError(nativeInit(sampleRate, numChannels, opusApplication));
	}
	
	public int encode(short[] in, int numSamples, byte[] out) {
		int ret;
		OpusError.throwOnError(ret = nativeEncodeShorts(in, numSamples, out));
		return ret;
	}
	
	/* temporarely removed to implement an endianess option and perform some tests
	public int encode(byte[] in, int numSamples, byte[] out) {
		// Convert all in c++.. it's just faster
		int ret;
		OpusError.throwOnError(ret = native_encode_bytes(in, numSamples, out));
		return ret;
	}
	*/

	// todo: add javadoc *halp*
	/* ********** ctl general ********** */
	public long ctlGetBandwidth() {
		return nativeEncoderCtlGet(OPUS_GET_BANDWIDTH);
	}

	public long ctlResetState() {
		return nativeEncoderCtlSet(OPUS_RESET_STATE,0);
	}

	public long ctlGetSampleRate() {
		return nativeEncoderCtlGet(OPUS_GET_SAMPLE_RATE);
	}

	// todo: implement as 'disable phase inversion'?
	public long ctlSetPhaseInversionDisabled(boolean disable) {
		return nativeEncoderCtlSet(OPUS_GET_PHASE_INVERSION_DISABLED, disable ? 1 : 0);
	}

	// todo: implement as 'is phase inversion disabled'?
	public long ctlGetPhaseInversionDisabled() {
		return nativeEncoderCtlGet(OPUS_GET_PHASE_INVERSION_DISABLED);
	}

	/* ********** ctl set ********** */
	public long ctlSetApplication(int application) {
		return nativeEncoderCtlSet(OPUS_SET_APPLICATION, application);
	}

	public long ctlSetBitrate(int bitrate) {
		return nativeEncoderCtlSet(OPUS_SET_MAX_BANDWIDTH, bitrate);
	}

	public long ctlSetMaxBandwidth(int maxBandwidth) {
		return nativeEncoderCtlSet(OPUS_SET_MAX_BANDWIDTH, maxBandwidth);
	}

	public long ctlSetVbr(boolean enable) {
		return nativeEncoderCtlSet(OPUS_SET_VBR, enable ? 1 : 0);
	}

	public long ctlSetBandwidth(int bandwidth) {
		return nativeEncoderCtlSet(OPUS_SET_BANDWIDTH, bandwidth);
	}

	public long ctlSetComplexity(int complexty) {
		return nativeEncoderCtlSet(OPUS_SET_COMPLEXITY, complexty);
	}

	public long ctlSetInbandFec(boolean enable) {
		return nativeEncoderCtlSet(OPUS_SET_INBAND_FEC, enable ? 1 : 0);
	}

	public long ctlSetPacketLossPerc(int packetLossPercent) {
		return nativeEncoderCtlSet(OPUS_SET_PACKET_LOSS_PERC, packetLossPercent);
	}

	public long ctlSetDtx(boolean enable) {
		return nativeEncoderCtlSet(OPUS_SET_DTX, enable ? 1 : 0);
	}

	public long ctlSetVbrConstraint(boolean enableConstraint) {
		return nativeEncoderCtlSet(OPUS_SET_VBR_CONSTRAINT, enableConstraint ? 1 : 0);
	}

	// todo: add annotations with valid values (0,1,2)
	public long ctlSetForceChannels(int channels) {
		return nativeEncoderCtlSet(OPUS_SET_FORCE_CHANNELS, channels);
	}

	public long ctlSetSignal(int signalType) {
		return nativeEncoderCtlSet(OPUS_SET_SIGNAL, signalType);
	}

	public long ctlSetLsbDepth(int depth) {
		return nativeEncoderCtlSet(OPUS_SET_LSB_DEPTH, depth);
	}

	public long ctlSetExpertFrameDuration(int frameSize) {
		return nativeEncoderCtlSet(OPUS_SET_EXPERT_FRAME_DURATION, frameSize);
	}

	public long ctlSetPredictionDisabled(boolean disable) {
		return nativeEncoderCtlSet(OPUS_SET_PREDICTION_DISABLED, disable ? 1 : 0);
	}

	/* ********** ctl get ********** */
	public long ctlGetApplication() {
		return nativeEncoderCtlGet(OPUS_GET_APPLICATION);
	}

	public long ctlGetBitrate() {
		return nativeEncoderCtlGet(OPUS_GET_BITRATE);
	}

	public long ctlGetMaxBandwidth() {
		return nativeEncoderCtlGet(OPUS_GET_MAX_BANDWIDTH);
	}

	public long ctlGetVbr() {
		return nativeEncoderCtlGet(OPUS_GET_VBR);
	}

	public long ctlGetComplexity() {
		return nativeEncoderCtlGet(OPUS_GET_COMPLEXITY);
	}

	// todo: add a better name
	public long ctlGetInbandFec() {
		return nativeEncoderCtlGet(OPUS_GET_INBAND_FEC);
	}

	public long ctlGetPacketLossPerc() {
		return nativeEncoderCtlGet(OPUS_GET_PACKET_LOSS_PERC);
	}

	public long ctlGetDtx() {
		return nativeEncoderCtlGet(OPUS_GET_DTX);
	}

	public long ctlGetVbrConstraint() {
		return nativeEncoderCtlGet(OPUS_GET_VBR_CONSTRAINT);
	}

	public long ctlGetForceChannels() {
		return nativeEncoderCtlGet(OPUS_GET_FORCE_CHANNELS);
	}

	public long ctlGetSignal() {
		return nativeEncoderCtlGet(OPUS_GET_SIGNAL);
	}

	public long ctlGetLookahead() {
		return nativeEncoderCtlGet(OPUS_GET_LOOKAHEAD);
	}

	public long ctlGetFinalRange() {
		return nativeEncoderCtlGet(OPUS_GET_FINAL_RANGE);
	}

	public long ctlGetLsbDepth() {
		return nativeEncoderCtlGet(OPUS_GET_LSB_DEPTH);
	}

	public long ctlGetExpertFrameDuration() {
		return nativeEncoderCtlGet(OPUS_GET_EXPERT_FRAME_DURATION);
	}

	public long ctlGetPredictionDisabled() {
		return nativeEncoderCtlGet(OPUS_GET_PREDICTION_DISABLED);
	}

	public long ctlGetInDtx() {
		return nativeEncoderCtlGet(OPUS_GET_IN_DTX);
	}

	public void destroy() {
		nativeDestroy();
	}
}
