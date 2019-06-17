package com.d_peres.xiph.opus;

import com.d_peres.xiph.opus.annotation.Application;
import com.d_peres.xiph.opus.annotation.Channels;
import com.d_peres.xiph.opus.annotation.SampleRate;

public class OpusEncoder {
	
	private long enc_address;
	
	static {
		System.loadLibrary("jniopus");
	}
	
	private native int native_init(int sample_rate, int num_channels, int opus_application);
	private native int native_encode_shorts(short[] in, int num_samples, byte[] out);
	private native int native_encode_bytes(byte[] in, int num_samples, byte[] out);
	private native void native_destroy();
	
	//todo: add documentation
	public OpusEncoder(
			@SampleRate int sampleRate,
			@Channels int numChannels,
			@Application int opusApplication) {
		OpusError.throwOnError(native_init(sampleRate, numChannels, opusApplication));
	}
	
	public int encode(short[] in, int numSamples, byte[] out) {
		int ret;
		OpusError.throwOnError(ret = native_encode_shorts(in, numSamples, out));
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
	
	public void destroy() {
		native_destroy();
	}
}
