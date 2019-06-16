package com.d_peres.xiph.opus;

@SuppressWarnings("unused")
public class OpusConstants {
	// Valid sample rates to use on OpusEncoder.init() and OpusDecoder.init()
	public static final int SR_8000  =  8000;
	public static final int SR_12000 = 12000;
	public static final int SR_16000 = 16000;
	public static final int SR_24000 = 24000;
	public static final int SR_48000 = 48000;
	
	// Number of channels
	public static final int CH_MONO   = 1;
	public static final int CH_STEREO = 2;
	
	// Opus bitrate options
	public static final int OPUS_AUTO        = -1;
	public static final int OPUS_BITRATE_MAX = -2;
	
	// Frame sizes (in bytes)
	public static final int FS_120  = 120;
	public static final int FS_240  = 240;
	public static final int FS_480  = 480;
	public static final int FS_960  = 960;
	public static final int FS_1920 = 1920;
	public static final int FS_2880 = 2880;
	
	// Opus applications
	public static final int OPUS_APPLICATION_AUDIO = 2049;
	public static final int OPUS_APPLICATION_VOIP  = 2048;
	public static final int OPUS_APPLICATION_RESTRICTED_LOWDELAY = 2051;
}
