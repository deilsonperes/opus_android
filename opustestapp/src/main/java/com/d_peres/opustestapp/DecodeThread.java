package com.d_peres.opustestapp;

import android.os.SystemClock;

import com.d_peres.easylogger.EasyLogger;
import com.d_peres.xiph.opus.OpusConstants;
import com.d_peres.xiph.opus.OpusDecoder;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;

public class DecodeThread {
	private EasyLogger log = new EasyLogger("OPA", getClass());
	
	private WeakReference<ArrayBlockingQueue<byte[]>> opus_queue_ref;
	private WeakReference<ArrayBlockingQueue<short[]>> pcm_queue_ref;
	
	private Thread decode_thread = new Thread(new Runnable() {
		@Override
		public void run() {
			OpusDecoder dec = new OpusDecoder(24000, OpusConstants.CH_MONO);
			
			byte[] opus_buff;
			short[] pcm_buff = new short[2048];
			
			int log_cnt = 0;
			while (true) {
				if((opus_buff = opus_queue_ref.get().poll()) != null) {
					int pcm_dec = dec.decode(opus_buff, opus_buff.length, pcm_buff);
					pcm_queue_ref.get().offer(Arrays.copyOf(pcm_buff, pcm_dec));
				} else {
					SystemClock.sleep(40);
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
