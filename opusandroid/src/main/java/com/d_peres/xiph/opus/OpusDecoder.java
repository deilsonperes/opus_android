package com.d_peres.xiph.opus;

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
	
	public OpusDecoder(int sampleRate, int numChannels, int opusApplication) {
		OpusError.throwOnError(native_init(sampleRate, numChannels));
	}
	
	public int decode(byte[] in, short[] out) {
		int ret = 0;
		OpusError.throwOnError(ret = native_decode(in, in.length, out));
		return ret;
	}
	
	public void destroy() {
		native_destroy();
	}
}
