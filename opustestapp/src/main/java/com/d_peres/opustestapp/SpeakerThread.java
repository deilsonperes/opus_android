package com.d_peres.opustestapp;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

import com.d_peres.easylogger.EasyLogger;

import java.lang.ref.WeakReference;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class SpeakerThread {
	private EasyLogger log = new EasyLogger("OPA", getClass());
	
	private WeakReference<ArrayBlockingQueue<short[]>> queue_ref;
	
	private Thread speaker_thread = new Thread(new Runnable() {
		@Override
		public void run() {
			int streamType = AudioManager.STREAM_MUSIC;
			int sampleRateInHz = 24000;
			int channelConfig = AudioFormat.CHANNEL_OUT_MONO;
			int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
			int bufferSizeInBytes = (sampleRateInHz / 1000 * 40 * 2);
			int mode = AudioTrack.MODE_STREAM;
			AudioTrack track = new AudioTrack(
					streamType,
					sampleRateInHz,
					channelConfig,
					audioFormat,
					bufferSizeInBytes,
					mode
			);
			track.play();
			short[] buff;
			
			while (true) {
				try {
					if ((buff = queue_ref.get().poll(100, TimeUnit.MILLISECONDS)) != null) {
						int ret = track.write(buff, 0, buff.length);
						if (ret < 0) {
							log.i("Error: %d", ret);
						}
					}
				} catch (InterruptedException e) {
					// ignore and continue
				}
			}
		}
	});
	
	public SpeakerThread(ArrayBlockingQueue<short[]> queue) {
		queue_ref = new WeakReference<>(queue);
		speaker_thread.start();
	}
}
