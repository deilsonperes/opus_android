package com.d_peres.xiph.opus;

@SuppressWarnings("unused")
public interface OpusConstants {
	// Valid sample rates to use on OpusEncoder.init() and OpusDecoder.init()
	int SR_8000  =  8000;
	int SR_12000 = 12000;
	int SR_16000 = 16000;
	int SR_24000 = 24000;
	int SR_48000 = 48000;
	
	// Number of channels
	int CH_MONO   = 1;
	int CH_STEREO = 2;
	
	// Opus bitrate options
	int OPUS_AUTO        = -1;
	int OPUS_BITRATE_MAX = -2;
	
	// Frame sizes (in bytes)
	int FS_120  = 120;
	int FS_240  = 240;
	int FS_480  = 480;
	int FS_960  = 960;
	int FS_1920 = 1920;
	int FS_2880 = 2880;
	
	// Opus applications
	int OPUS_APPLICATION_AUDIO = 2049;
	int OPUS_APPLICATION_VOIP  = 2048;
	int OPUS_APPLICATION_RESTRICTED_LOWDELAY = 2051;
}
