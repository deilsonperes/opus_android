package com.d_peres.opustestapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ArrayBlockingQueue;

public class MainActivity extends AppCompatActivity {
	
	private String[] permissions = {Manifest.permission.RECORD_AUDIO};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			requestPermissions(permissions, 0);
		} else {
			init();
		}
	}
	
	@RequiresApi(api = Build.VERSION_CODES.M)
	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if(grantResults[0] != PackageManager.PERMISSION_GRANTED)
			requestPermissions(permissions, 0);
		else
			init();
	}
	
	private ArrayBlockingQueue<short []> rec_pcm_queue;
	private ArrayBlockingQueue<byte[]> opus_queue;
	private ArrayBlockingQueue<short []> spk_pcm_queue;
	
	void init() {
		rec_pcm_queue = new ArrayBlockingQueue<>(50);
		spk_pcm_queue = new ArrayBlockingQueue<>(50);
		opus_queue = new ArrayBlockingQueue<>(50);
		
		new RecordThread(rec_pcm_queue);
		new EncodeThread(rec_pcm_queue, opus_queue);
		new DecodeThread(opus_queue, spk_pcm_queue);
		new SpeakerThread(spk_pcm_queue);
	}
}
