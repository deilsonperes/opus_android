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
	
	/**
	 * Creates an Opus Decoder.<br>
	 * The sampling rate and must be 8000, 12000, 16000, 24000, or 48000
	 * The numner of channels can be either 1 or 2 (mono/stereo)
	 *
	 * @param sampleRate the sample rate
	 * @param numChannels the number of channels
	 */
	public OpusDecoder(
			@SampleRate int sampleRate,
			@Channels int numChannels) {
		OpusError.throwOnError(native_init(sampleRate, numChannels));
	}
	
	/**
	 * Decodes an opus frame.
	 *
	 * @param in the input signal (compressed opus bytes)
	 * @param num_samples the number of bytes in the input signal
	 * @param out the pcm output signal
	 * @return the number of samples decoded (per channel)
	 */
	public int decode(byte[] in, int num_samples, short[] out) {
		int ret;
		OpusError.throwOnError(ret = native_decode(in, num_samples, out));
		return ret;
	}
	
	/**
	 * Frees the native memory allocated for this decoder. After this is called
	 * the decoder can no longer be used.
	 */
	public void destroy() {
		native_destroy();
	}
}
