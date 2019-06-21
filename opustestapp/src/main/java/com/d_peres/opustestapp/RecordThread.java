package com.d_peres.opustestapp;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;

import com.d_peres.easylogger.EasyLogger;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;

import static com.d_peres.opustestapp.MainActivity.sample_rate;
import static com.d_peres.opustestapp.MainActivity.size_ms;

public class RecordThread {
	private EasyLogger log = new EasyLogger("OPA", getClass());
	
	private WeakReference<ArrayBlockingQueue<short[]>> queue_ref;
	
	private volatile boolean record_end = false;
	
	private Thread record_thread = new Thread(new Runnable() {
		@Override
		public void run() {
			
			int audioSource = MediaRecorder.AudioSource.MIC;
            int sampleRateInHz = sample_rate;
            int channelConfig = AudioFormat.CHANNEL_IN_MONO;
            int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
            int bufferSizeInBytes = (sampleRateInHz / 1000 * size_ms * 2);
            
            short[] aud_buff = new short[bufferSizeInBytes];
            int pcm_samples_read;
			
			AudioRecord record = new AudioRecord(
					audioSource,
					sampleRateInHz,
					channelConfig,
					audioFormat,
					bufferSizeInBytes
			);
			record.startRecording();
			
			while (!record_end) {
				// read audio
				pcm_samples_read = record.read(aud_buff, 0, bufferSizeInBytes / 2);
				// send to queue
				queue_ref.get().offer(Arrays.copyOf(aud_buff, pcm_samples_read));
				// repeat
			}
			
			record.stop();
		}
	});
	
	public RecordThread(ArrayBlockingQueue<short[]> queue) {
		queue_ref = new WeakReference<>(queue);
		record_thread.start();
	}
	
	public void stop() {
		record_end = true;
	}
}
