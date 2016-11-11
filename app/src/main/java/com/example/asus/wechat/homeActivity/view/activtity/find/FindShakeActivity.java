package com.example.asus.wechat.homeActivity.view.activtity.find;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.asus.wechat.R;

public class FindShakeActivity extends AppCompatActivity {

    private ImageView iv_shake_up;
    private ImageView iv_shake_down;
    private TextView tv_shake_mode;
    private ImageView iv_back;
    private ImageView iv_setting;
    private RadioGroup rg;
    private RadioButton rb_shake_TV;
    private RadioButton rb_shake_song;
    private RadioButton rb_shake_person;
    private SensorManager sensorManager;
    private long lastTime = 0;
    private Vibrator vibrator;
    private int playId;
    private SoundPool soundPool;
    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - lastTime < 1000) {
                return;
            }
            lastTime = currentTimeMillis;
            float[] values = event.values;
            //分别获取手机在X、Y、Z轴上的加速度大小
            float valueX = Math.abs(values[0]);
            float valueY = Math.abs(values[1]);
            float valueZ = Math.abs(values[2]);
            if (valueX > 13 || valueY > 13 || valueZ > 13) {
                startAnim();
                startSound();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_shake);
        init();
        vibrator = ((Vibrator) getSystemService(VIBRATOR_SERVICE));
    }

    //初始化传感器
    private void initSensor() {
        sensorManager = ((SensorManager) getSystemService(Context.SENSOR_SERVICE));
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    //设置摇动动画
    private void startAnim() {
        //向上的动画
        AnimationSet upSet = new AnimationSet(false);
        TranslateAnimation upUp = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 0,
                TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, -1);
        upUp.setDuration(1000);
        TranslateAnimation upDown = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 0,
                TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 1);
        upDown.setDuration(1000);
        upDown.setStartOffset(1000);
        upSet.addAnimation(upUp);
        upSet.addAnimation(upDown);
        iv_shake_up.startAnimation(upSet);

    //向下的动画
        AnimationSet downSet = new AnimationSet(false);
        TranslateAnimation downUp = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 0,
                TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, -1);
        downUp.setDuration(1000);
        downUp.setStartOffset(1000);
        TranslateAnimation downDown = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 0,
                TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 1);
        downDown.setDuration(1000);
        downSet.addAnimation(downDown);
        downSet.addAnimation(downUp);
        iv_shake_down.startAnimation(downSet);
    }

    //初始化声音池
    private void initSoundPool() {
        soundPool = null;
        if (Build.VERSION.SDK_INT > 20) {
            SoundPool.Builder builder = new SoundPool.Builder();
            //设置最大并发流数量
            builder.setMaxStreams(3);
            AudioAttributes.Builder builder1 = new AudioAttributes.Builder();
            builder1.setLegacyStreamType(AudioManager.STREAM_MUSIC);
            builder.setAudioAttributes(builder1.build());
            soundPool = builder.build();
        } else {
            soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        }
        playId = soundPool.load(this, R.raw.awe, 1);
    }

    //开启声音
    private void startSound() {
        soundPool.play(playId, 1, 1, 0, 0, 1);
        vibrator.vibrate(new long[]{300, 200, 300, 200}, -1);
    }

    //初始化数据
    private void init() {
        iv_shake_up = ((ImageView) findViewById(R.id.iv_shake_up));
        iv_shake_down = ((ImageView) findViewById(R.id.iv_shake_down));
        tv_shake_mode = ((TextView) findViewById(R.id.tv_shake_mode));
        iv_back = ((ImageView) findViewById(R.id.iv_back));
        iv_setting = ((ImageView) findViewById(R.id.iv_setting));
        rg = ((RadioGroup) findViewById(R.id.rg));
        rb_shake_TV = ((RadioButton) findViewById(R.id.rb_shake_TV));
        rb_shake_song = ((RadioButton) findViewById(R.id.rb_shake_song));
        rb_shake_person = ((RadioButton) findViewById(R.id.rb_shake_person));

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            private Drawable shake_tv1 = getResources().getDrawable(R.drawable.shake_tv1);
            private Drawable shake_song1 = getResources().getDrawable(R.drawable.shake_song1);
            private Drawable shake_person = getResources().getDrawable(R.drawable.shake_person);
            private Drawable shake_tv = getResources().getDrawable(R.drawable.shake_tv);
            private Drawable shake_song = getResources().getDrawable(R.drawable.shake_song);
            private Drawable shake_person1 = getResources().getDrawable(R.drawable.shake_person1);

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_shake_person:
                        tv_shake_mode.setText("摇一摇");
                        shake_tv.setBounds(0, 0, shake_tv.getMinimumWidth(), shake_tv.getMinimumHeight());
                        shake_person1.setBounds(0, 0, shake_person1.getMinimumWidth(), shake_person.getMinimumHeight());
                        shake_song.setBounds(0, 0, shake_song.getMinimumWidth(), shake_song.getMinimumHeight());

                        rb_shake_person.setCompoundDrawables(null, shake_person1, null, null);
                        rb_shake_song.setCompoundDrawables(null, shake_song, null, null);
                        rb_shake_TV.setCompoundDrawables(null, shake_tv, null, null);
                        break;
                    case R.id.rb_shake_song:
                        tv_shake_mode.setText("摇歌曲");

                        shake_tv.setBounds(0, 0, shake_tv.getMinimumWidth(), shake_tv.getMinimumHeight());
                        shake_person.setBounds(0, 0, shake_person.getMinimumWidth(), shake_person.getMinimumHeight());
                        shake_song1.setBounds(0, 0, shake_song1.getMinimumWidth(), shake_song.getMinimumHeight());

                        rb_shake_person.setCompoundDrawables(null, shake_person, null, null);
                        rb_shake_song.setCompoundDrawables(null, shake_song1, null, null);
                        rb_shake_TV.setCompoundDrawables(null, shake_tv, null, null);
                        break;
                    case R.id.rb_shake_TV:
                        tv_shake_mode.setText("摇电视");

                        shake_tv1.setBounds(0, 0, shake_tv1.getMinimumWidth(), shake_tv.getMinimumHeight());
                        shake_person.setBounds(0, 0, shake_person.getMinimumWidth(), shake_person.getMinimumHeight());
                        shake_song.setBounds(0, 0, shake_song.getMinimumWidth(), shake_song.getMinimumHeight());
                        rb_shake_person.setCompoundDrawables(null, shake_person, null, null);
                        rb_shake_song.setCompoundDrawables(null, shake_song, null, null);
                        rb_shake_TV.setCompoundDrawables(null, shake_tv1, null, null);
                        break;
                }
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FindShakeActivity.this.finish();
            }
        });
        iv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindShakeActivity.this,FindShakeSetActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initSensor();
        initSoundPool();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(sensorEventListener);
    }
}
