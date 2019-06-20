package com.d_peres.xiph.opus;

import com.d_peres.xiph.opus.annotation.Channels;
import com.d_peres.xiph.opus.annotation.SampleRate;

@SuppressWarnings("unused")
public class OpusDecoder {
	// todo: add decoder ctl operations
	static {
		System.loadLibrary("jniopus");
	}
	
	/* native variables storage */
	private long dec_address;
	private int num_channels;
	
	/* native methods */
	private native int nativeInit(int sample_rate, int num_channels);
	private native int nativeDecode(byte[] in, int num_samples, short[] out);
	private native long nativeDecoderCtl(int ctl, long arg);
	private native void nativeDestroy();
	
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
		OpusError.throwOnError(nativeInit(sampleRate, numChannels));
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
		OpusError.throwOnError(ret = nativeDecode(in, num_samples, out));
		return ret;
	}
	
	/* Decoder CTLs */
	
	/**
	 * Resets the codec state to be equivalent to a freshly initialized state. <br>
	 * This should be called when switching  streams in order to prevent the  back to back  decoding
	 * from giving different results from one at a time decoding.
	 * @return {@link OpusError#OPUS_OK} on success or one of the {@link OpusError} codes
	 */
	public long ctlResetState() {
		return nativeDecoderCtl(OpusConstants.OPUS_RESET_STATE, 0);
	}
	
	/**
	 * Gets the encoder's configured bandpass or the decoder's last bandpass.
	 * @return todo: add the bandwidth constants to 'OpusConstants'
	 */
	public long ctlGetBandwidth() {
		return nativeDecoderCtl(OpusConstants.OPUS_GET_BANDWIDTH, 0);
	}
	
	/**
	 * Gets the sampling rate the encoder or decoder was initialized with.
	 * @return Sampling rate of encoder or decoder.
	 */
	public long ctlGetSampleRate() {
		return nativeDecoderCtl(OpusConstants.OPUS_GET_SAMPLE_RATE, 0);
	}
	
	/**
	 * If set to {@code true}, disables the use of phase inversion for intensity stereo, improving
	 * the quality of mono downmixes, but slightly reducing normal stereo quality. <br>
	 * Disabling phase inversion in the decoder does not comply with RFC 6716, although it does not
	 * cause any interoperability issue and is expected to become part of the Opus standard once
	 * RFC 6716 is updated by draft-ietf-codec-opus-update.
	 * @param disable true to disable phase inversion, false otherwise
	 * @return {@link OpusError#OPUS_OK} on success or one of the {@link OpusError} codes
	 */
	public long ctlSetPhaseInversionDisabled(boolean disable) {
		return nativeDecoderCtl(OpusConstants.OPUS_SET_PHASE_INVERSION_DISABLED, (disable ? 1 : 0));
	}
	
	/**
	 * Gets the encoder's configured phase inversion status.
	 * @return true if stereo phase inversion disabled, false otherwise.
	 */
	public boolean ctlGetPhaseInversionDisabled() {
		return nativeDecoderCtl(OpusConstants.OPUS_GET_PHASE_INVERSION_DISABLED, 0) == 1;
	}
	
	/**
	 * Configures decoder gain adjustment. <br><br>
	 *
	 * Scales the decoded output by a factor specified in Q8 dB units. This has a maximum range of
	 * -32768 to 32767 inclusive, and returns OPUS_BAD_ARG otherwise. The default is zero indicating
	 * no adjustment. This setting survives decoder reset. <br><br>
	 *
	 * gain = pow(10, x/(20.0*256))
	 *
	 * @param gain Amount to scale PCM signal by in Q8 dB units.
	 * @return {@link OpusError#OPUS_OK} on success or {@link OpusError#OPUS_BAD_ARG} otherwise.
	 */
	public long ctlSetGain(long gain) {
		return nativeDecoderCtl(OpusConstants.OPUS_SET_GAIN, gain);
	}
	
	/**
	 * Gets the decoder's configured gain adjustment.
	 *
	 * @return Amount that this decoder signal is scaled by in Q8 dB units.
	 */
	public long ctlGetGain() {
		return nativeDecoderCtl(OpusConstants.OPUS_GET_GAIN, 0);
	}
	
	/**
	 * Gets the duration (in samples) of the last packet successfully decoded or concealed.
	 *
	 * @return Number of samples (at current sampling rate).
	 */
	public long ctlGetLastPacketDuration() {
		return nativeDecoderCtl(OpusConstants.OPUS_GET_LAST_PACKET_DURATION, 0);
	}
	
	/**
	 * Gets the pitch of the last decoded frame, if available.<br><br>
	 *
	 * This can be used for any post-processing algorithm requiring the use of pitch, e.g. time
	 * stretching/shortening. If the last frame was not voiced, or if the pitch was not coded in the
	 * frame, then zero is returned.
	 *
	 * @return {@link OpusError#OPUS_OK} on success or one of the {@link OpusError} codes
	 */
	public long ctlGetPitch() {
		return nativeDecoderCtl(OpusConstants.OPUS_GET_PITCH, 0);
	}
	
	/**
	 * Frees the native memory allocated for this decoder. After this is called
	 * the decoder can no longer be used.
	 */
	public void destroy() {
		nativeDestroy();
	}
}
