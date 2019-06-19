package com.d_peres.xiph.opus.annotation;

import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
import static com.d_peres.xiph.opus.OpusConstants.OPUS_SET_BITRATE;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_SET_COMPLEXITY;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_SET_DTX;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_SET_EXPERT_FRAME_DURATION;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_SET_FORCE_CHANNELS;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_SET_INBAND_FEC;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_SET_LSB_DEPTH;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_SET_MAX_BANDWIDTH;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_SET_PACKET_LOSS_PERC;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_SET_PHASE_INVERSION_DISABLED;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_SET_PREDICTION_DISABLED;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_SET_SIGNAL;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_SET_VBR;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_SET_VBR_CONSTRAINT;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.SOURCE)
@IntDef({
		OPUS_SET_APPLICATION, OPUS_GET_APPLICATION, OPUS_SET_BITRATE, OPUS_GET_BITRATE,
		OPUS_SET_MAX_BANDWIDTH, OPUS_GET_MAX_BANDWIDTH, OPUS_SET_VBR, OPUS_GET_VBR,
		OPUS_SET_BANDWIDTH, OPUS_GET_BANDWIDTH, OPUS_SET_COMPLEXITY, OPUS_GET_COMPLEXITY,
		OPUS_SET_INBAND_FEC, OPUS_GET_INBAND_FEC, OPUS_SET_PACKET_LOSS_PERC,
		OPUS_GET_PACKET_LOSS_PERC, OPUS_SET_DTX, OPUS_GET_DTX, OPUS_SET_VBR_CONSTRAINT,
		OPUS_GET_VBR_CONSTRAINT, OPUS_SET_FORCE_CHANNELS, OPUS_GET_FORCE_CHANNELS,
		OPUS_SET_SIGNAL, OPUS_GET_SIGNAL, OPUS_GET_LOOKAHEAD, OPUS_RESET_STATE,
		OPUS_GET_SAMPLE_RATE, OPUS_GET_FINAL_RANGE, OPUS_SET_LSB_DEPTH,
		OPUS_GET_LSB_DEPTH, OPUS_SET_EXPERT_FRAME_DURATION, OPUS_GET_EXPERT_FRAME_DURATION,
		OPUS_SET_PREDICTION_DISABLED, OPUS_GET_PREDICTION_DISABLED,
		OPUS_SET_PHASE_INVERSION_DISABLED, OPUS_GET_PHASE_INVERSION_DISABLED, OPUS_GET_IN_DTX
})
public @interface EncoderCtl {}
