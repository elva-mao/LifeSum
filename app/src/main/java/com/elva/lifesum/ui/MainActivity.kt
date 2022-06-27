package com.elva.lifesum.ui

import android.content.Context
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.elva.lifesum.R
import com.elva.lifesum.databinding.ActivityMainBinding
import com.elva.lifesum.model.FoodItem
import com.elva.lifesum.model.State
import com.elva.lifesum.shake.ShakeDetector
import com.elva.lifesum.util.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ShakeDetector.OnShakeListener {

    private val mViewModel: FoodViewModel by viewModels()
    private lateinit var mDataBinding: ActivityMainBinding
    private var shakeDetector: ShakeDetector? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        initSensor()
        observeFoodItem()
    }

    private fun initViews() {
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(mDataBinding.root)
        mDataBinding.foodItemCard.root.hide()
    }

    private fun initSensor() {
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        shakeDetector = ShakeDetector(sensorManager, this)
    }

    override fun onFrequencyControl() {
        showToast(  "Please wait a moment to shake again ！")
    }

    override fun onShake() {
        loadFoodItem()
        showSnackbar(mDataBinding.root, "Aha ! Food item is loading...")
    }

    override fun onStart() {
        super.onStart()
        handleNetworkChanges()
    }

    override fun onResume() {
        super.onResume()
        shakeDetector?.start()
    }

    override fun onPause() {
        super.onPause()
        shakeDetector?.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        shakeDetector = null
    }

    private fun loadFoodItem() {
        NetworkUtils.getNetworkLiveData(applicationContext).observe(this) { isConnected ->
            if (!isConnected) {
                showToast( "Oops ! Network is not available now")
                return@observe
            } else {
                mViewModel.getFoodItem(FoodDataUtils.generateFoodItemId())
            }
        }
    }

    /**
     * observe the request foodItem data and update ui
     */
    private fun observeFoodItem() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mViewModel.foodItemFlow.collect() { state ->
                    when (state) {
                        is State.Loading -> showLoading(true)
                        is State.Success -> {
                            if (state.data != null) {
                                //update ui
                                showFoodCardView(state.data)
                                showLoading(false)
                            }
                        }
                        is State.Error -> {
                            showSnackbar(mDataBinding.root, state.message)
                            showLoading(false)
                        }
                    }
                }
            }
        }
    }

    private fun showFoodCardView(foodItem: FoodItem) {
        mDataBinding.foodItemCard.foodItem = foodItem
        mDataBinding.foodItemCard.root.show()
    }

    private fun showLoading(isLoading: Boolean) {
        mDataBinding.foodItemCard.isLoading = isLoading
        if (isLoading) mDataBinding.foodItemCard.foodCardView.hide() else mDataBinding.foodItemCard.foodCardView.show()
    }

    /**
     * Observe network changes
     */
    private fun handleNetworkChanges() {
        NetworkUtils.getNetworkLiveData(applicationContext).observe(this) { isConnected ->
            if (!isConnected) {
                showToast( "Oops ! Network is not available now")
            }
        }
    }

}

