package com.elva.lifesum.shake

import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.hardware.SensorEvent
import android.hardware.Sensor
import java.util.*

class ShakeDetector(private val mSensorManager: SensorManager, private val mListener : OnShakeListener) : SensorEventListener {

    private var mShakeTimestamp: Long = 0

    interface OnShakeListener {
        fun onShake()
        fun onFrequencyControl()
    }

    fun start() {
        Objects.requireNonNull(mSensorManager)
            ?.registerListener(
                this, mSensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL)

        mSensorManager?.registerListener(
            this, mSensorManager?.getDefaultSensor(
                Sensor.TYPE_ACCELEROMETER
            ), SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    fun stop() {
        mSensorManager?.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // ignore
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (mListener != null) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]
            val gX = x / SensorManager.GRAVITY_EARTH
            val gY = y / SensorManager.GRAVITY_EARTH
            val gZ = z / SensorManager.GRAVITY_EARTH

            // gForce will be close to 1 when there is no movement.
            val gForce: Float = kotlin.math.sqrt(gX * gX + gY * gY + gZ * gZ)
            if (gForce > Companion.SHAKE_THRESHOLD_GRAVITY) {
                val now = System.currentTimeMillis()
                // ignore shake events too close to each other (500ms)
                if (mShakeTimestamp + SHAKE_SLOP_TIME_MS > now) {
                    mListener.onFrequencyControl()
                    return
                }

                mShakeTimestamp = now
                mListener.onShake()
            }
        }
    }

    companion object {
        const val SHAKE_THRESHOLD_GRAVITY = 2.7f
        const val SHAKE_SLOP_TIME_MS = 2000
    }
}