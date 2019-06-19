package com.d_peres.xiph.opus.annotation;

import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.d_peres.xiph.opus.OpusConstants.OPUS_GET_BANDWIDTH;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_GET_GAIN;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_GET_LAST_PACKET_DURATION;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_GET_PHASE_INVERSION_DISABLED;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_GET_PITCH;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_GET_SAMPLE_RATE;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_RESET_STATE;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_SET_GAIN;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_SET_PHASE_INVERSION_DISABLED;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.SOURCE)
@IntDef({
		OPUS_GET_BANDWIDTH, OPUS_RESET_STATE, OPUS_GET_SAMPLE_RATE,
		OPUS_SET_PHASE_INVERSION_DISABLED, OPUS_GET_PHASE_INVERSION_DISABLED,
		OPUS_SET_GAIN, OPUS_GET_GAIN, OPUS_GET_LAST_PACKET_DURATION, OPUS_GET_PITCH
})
public @interface DecoderCtl {}
