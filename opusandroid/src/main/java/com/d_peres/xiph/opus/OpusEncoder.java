package com.d_peres.xiph.opus;

import com.d_peres.xiph.opus.annotation.Application;
import com.d_peres.xiph.opus.annotation.Channels;
import com.d_peres.xiph.opus.annotation.SampleRate;

public class OpusEncoder {
	
	private long enc_address;
	
	static {
		System.loadLibrary("jniopus");
	}
	
	private native int nativeInit(int sample_rate, int num_channels, int opus_application);
	private native int nativeEncodeShorts(short[] in, int num_samples, byte[] out);
	private native int nativeEncodeBytes(byte[] in, int num_samples, byte[] out);
	private native void nativeDestroy();
	
	/* native ctl calls */
	private native int ctlSetBitrate(int bitrate);
	private native int ctlSetComplexity(int complexity);
	private native int ctlEnableVbr(boolean enable);
	
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
	
	/* temporarely removed to implement an endianess option and do tests
	public int encode(byte[] in, int numSamples, byte[] out) {
		// Convert all in c++.. it's just faster
		int ret;
		OpusError.throwOnError(ret = native_encode_bytes(in, numSamples, out));
		return ret;
	}
	*/
	
	/* ctl operations */
	public int setBitrate(int bitrate) {
		return ctlSetBitrate(bitrate);
	}
	
	public int setComplexity(int complexity) {
		return ctlSetComplexity(complexity);
	}
	
	public int enableVbr(boolean enable) {
		return ctlEnableVbr(enable);
	}
	
	public void destroy() {
		nativeDestroy();
	}
}
