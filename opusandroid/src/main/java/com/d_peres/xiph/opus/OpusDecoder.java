package com.d_peres.xiph.opus;

import com.d_peres.xiph.opus.annotation.Channels;
import com.d_peres.xiph.opus.annotation.SampleRate;

@SuppressWarnings("unused")
public class OpusDecoder {
	
	static {
		System.loadLibrary("jniopus");
	}
	
	/* native variables storage */
	private long dec_address;
	private int num_channels;
	
	/* native methods */
	private native int native_init(int sample_rate, int num_channels);
	private native int native_decode(byte[] in, int num_samples, short[] out);
	private native void native_destroy();
	
	public OpusDecoder(
			@SampleRate int sampleRate,
			@Channels int numChannels) {
		OpusError.throwOnError(native_init(sampleRate, numChannels));
	}
	
	public int decode(byte[] in, int num_samples, short[] out) {
		int ret;
		OpusError.throwOnError(ret = native_decode(in, num_samples, out));
		return ret;
	}
	
	public void destroy() {
		native_destroy();
	}
}
