package com.d_peres.xiph.opus.annotation;

import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.d_peres.xiph.opus.OpusConstants.OPUS_APPLICATION_AUDIO;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_APPLICATION_RESTRICTED_LOWDELAY;
import static com.d_peres.xiph.opus.OpusConstants.OPUS_APPLICATION_VOIP;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.SOURCE)
@IntDef({OPUS_APPLICATION_AUDIO, OPUS_APPLICATION_VOIP, OPUS_APPLICATION_RESTRICTED_LOWDELAY})
public @interface Application {}
