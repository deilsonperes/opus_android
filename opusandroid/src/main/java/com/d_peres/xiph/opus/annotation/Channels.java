package com.d_peres.xiph.opus.annotation;

import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.d_peres.xiph.opus.OpusConstants.CH_MONO;
import static com.d_peres.xiph.opus.OpusConstants.CH_STEREO;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.SOURCE)
@IntDef({CH_STEREO, CH_MONO})
public @interface Channels {}