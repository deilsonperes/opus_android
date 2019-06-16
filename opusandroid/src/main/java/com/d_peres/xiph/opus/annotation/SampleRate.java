package com.d_peres.xiph.opus.annotation;

import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.d_peres.xiph.opus.OpusConstants.SR_12000;
import static com.d_peres.xiph.opus.OpusConstants.SR_16000;
import static com.d_peres.xiph.opus.OpusConstants.SR_24000;
import static com.d_peres.xiph.opus.OpusConstants.SR_48000;
import static com.d_peres.xiph.opus.OpusConstants.SR_8000;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.SOURCE)
@IntDef({SR_8000, SR_12000, SR_16000, SR_24000, SR_48000})
public @interface SampleRate {}
