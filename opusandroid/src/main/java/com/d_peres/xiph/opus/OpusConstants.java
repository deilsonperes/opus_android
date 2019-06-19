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
	
	/* CTL operations */
	// Generic
	public static final int OPUS_GET_BANDWIDTH                = 4009;
	public static final int OPUS_RESET_STATE                  = 4028;
	public static final int OPUS_GET_SAMPLE_RATE              = 4029;
	public static final int OPUS_SET_PHASE_INVERSION_DISABLED = 4046;
	public static final int OPUS_GET_PHASE_INVERSION_DISABLED = 4047;
	
	// Encoder ctl
	public static final int OPUS_SET_APPLICATION              = 4000;
	public static final int OPUS_GET_APPLICATION              = 4001;
	public static final int OPUS_SET_BITRATE                  = 4002;
	public static final int OPUS_GET_BITRATE                  = 4003;
	public static final int OPUS_SET_MAX_BANDWIDTH            = 4004;
	public static final int OPUS_GET_MAX_BANDWIDTH            = 4005;
	public static final int OPUS_SET_VBR                      = 4006;
	public static final int OPUS_GET_VBR                      = 4007;
	public static final int OPUS_SET_BANDWIDTH                = 4008;
	public static final int OPUS_SET_COMPLEXITY               = 4010;
	public static final int OPUS_GET_COMPLEXITY               = 4011;
	public static final int OPUS_SET_INBAND_FEC               = 4012;
	public static final int OPUS_GET_INBAND_FEC               = 4013;
	public static final int OPUS_SET_PACKET_LOSS_PERC         = 4014;
	public static final int OPUS_GET_PACKET_LOSS_PERC         = 4015;
	public static final int OPUS_SET_DTX                      = 4016;
	public static final int OPUS_GET_DTX                      = 4017;
	public static final int OPUS_SET_VBR_CONSTRAINT           = 4020;
	public static final int OPUS_GET_VBR_CONSTRAINT           = 4021;
	public static final int OPUS_SET_FORCE_CHANNELS           = 4022;
	public static final int OPUS_GET_FORCE_CHANNELS           = 4023;
	public static final int OPUS_SET_SIGNAL                   = 4024;
	public static final int OPUS_GET_SIGNAL                   = 4025;
	public static final int OPUS_GET_LOOKAHEAD                = 4027;
	public static final int OPUS_GET_FINAL_RANGE              = 4031;
	public static final int OPUS_SET_LSB_DEPTH                = 4036;
	public static final int OPUS_GET_LSB_DEPTH                = 4037;
	public static final int OPUS_SET_EXPERT_FRAME_DURATION    = 4040;
	public static final int OPUS_GET_EXPERT_FRAME_DURATION    = 4041;
	public static final int OPUS_SET_PREDICTION_DISABLED      = 4042;
	public static final int OPUS_GET_PREDICTION_DISABLED      = 4043;
	public static final int OPUS_GET_IN_DTX                   = 4049;
	
	// Decoder ctl
	public static final int OPUS_SET_GAIN                     = 4034;
	public static final int OPUS_GET_GAIN                     = 4045;
	public static final int OPUS_GET_LAST_PACKET_DURATION     = 4039;
	public static final int OPUS_GET_PITCH                    = 4033;
}
