package com.d_peres.opustestapp;

import com.d_peres.easylogger.EasyLogger;
import com.d_peres.xiph.opus.OpusConstants;
import com.d_peres.xiph.opus.OpusEncoder;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import static com.d_peres.opustestapp.MainActivity.opus_application;
import static com.d_peres.opustestapp.MainActivity.sample_rate;

public class EncodeThread {
	private EasyLogger log = new EasyLogger("OPA", getClass());
	
	private WeakReference<ArrayBlockingQueue<short[]>> pcm_queue_ref;
	private WeakReference<ArrayBlockingQueue<byte[]>> opus_queue_ref;
	
	private boolean encode_end = false;
	
	private Thread encode_thread = new Thread(new Runnable() {
		@Override
		public void run() {
			OpusEncoder enc = new OpusEncoder(sample_rate, OpusConstants.CH_MONO, opus_application);
			enc.setComplexity(0);
			enc.setBitrate(16000);
			
			short[] buffer;
			byte[] opus_out = new byte[4096];
			
			while (!encode_end) {
				try {
					if ((buffer = pcm_queue_ref.get().poll(400, TimeUnit.MILLISECONDS)) != null){
						// encode
						int opus_frm = enc.encode(buffer, buffer.length, opus_out);
						// enqueue the encoded data
						opus_queue_ref.get().offer(Arrays.copyOf(opus_out, opus_frm));
					}
				} catch (InterruptedException e) {
					// ignore
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
