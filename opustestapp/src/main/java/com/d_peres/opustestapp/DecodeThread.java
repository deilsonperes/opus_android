package com.d_peres.opustestapp;

import com.d_peres.easylogger.EasyLogger;
import com.d_peres.xiph.opus.OpusConstants;
import com.d_peres.xiph.opus.OpusDecoder;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class DecodeThread {
	private EasyLogger log = new EasyLogger("OPA", getClass());
	
	private WeakReference<ArrayBlockingQueue<byte[]>> opus_queue_ref;
	private WeakReference<ArrayBlockingQueue<short[]>> pcm_queue_ref;
	
	private Thread decode_thread = new Thread(new Runnable() {
		@Override
		public void run() {
			OpusDecoder dec = new OpusDecoder(24000, OpusConstants.CH_MONO);
			log.i("Phase inversion disabled: %b", dec.ctlGetPhaseInversionDisabled());
			dec.ctlSetPhaseInversionDisabled(true);
			log.i("Phase inversion disabled: %b", dec.ctlGetPhaseInversionDisabled());
			dec.ctlSetPhaseInversionDisabled(false);
			log.i("Phase inversion disabled: %b", dec.ctlGetPhaseInversionDisabled());
			
			byte[] opus_buff;
			short[] pcm_buff = new short[2048];
			
			while (true) {
				try {
					if((opus_buff = opus_queue_ref.get().poll(100, TimeUnit.MILLISECONDS)) != null) {
						int pcm_dec = dec.decode(opus_buff, opus_buff.length, pcm_buff);
						pcm_queue_ref.get().offer(Arrays.copyOf(pcm_buff, pcm_dec));
					}
				} catch (InterruptedException e) {
					// ignore
				}
			}
		}
	});
	
	public DecodeThread(ArrayBlockingQueue<byte []> opus_queue, ArrayBlockingQueue<short []> pcm_queue) {
		opus_queue_ref = new WeakReference<>(opus_queue);
		pcm_queue_ref = new WeakReference<>(pcm_queue);
		decode_thread.start();
	}
}
