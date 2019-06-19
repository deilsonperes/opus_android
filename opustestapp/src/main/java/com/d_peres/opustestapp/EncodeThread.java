package com.d_peres.opustestapp;

import android.os.SystemClock;

import com.d_peres.easylogger.EasyLogger;
import com.d_peres.xiph.opus.OpusConstants;
import com.d_peres.xiph.opus.OpusEncoder;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;

public class EncodeThread {
	private EasyLogger log = new EasyLogger("OPA", getClass());
	
	private WeakReference<ArrayBlockingQueue<short[]>> pcm_queue_ref;
	private WeakReference<ArrayBlockingQueue<byte[]>> opus_queue_ref;
	
	private boolean encode_end = false;
	
	private Thread encode_thread = new Thread(new Runnable() {
		@Override
		public void run() {
			OpusEncoder enc = new OpusEncoder(24000, OpusConstants.CH_MONO, OpusConstants.OPUS_APPLICATION_AUDIO);
			enc.enableVbr(true);
			enc.setBitrate(500);
			short[] buffer;
			byte[] opus_out = new byte[4096];
			
			int log_cnt = 0;
			while (!encode_end) {
				if ((buffer = pcm_queue_ref.get().poll()) != null){
					// encode
					int opus_frm = enc.encode(buffer, buffer.length, opus_out);
					// enqueue the encoded data
					opus_queue_ref.get().offer(Arrays.copyOf(opus_out, opus_frm));
				} else {
					SystemClock.sleep(40);
				}
			}
		}
	});
	
	public EncodeThread(ArrayBlockingQueue<short[]> queue_pcm, ArrayBlockingQueue<byte[]> queue_opus) {
		pcm_queue_ref = new WeakReference<>(queue_pcm);
		opus_queue_ref = new WeakReference<>(queue_opus);
		encode_thread.start();
	}
	
	public void stop() {
		encode_end = true;
	}
}
