package com.akondi.cleanarchitecure.merchants.presentation.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.akondi.cleanarchitecure.R
import com.akondi.cleanarchitecure.core.exception.Failure
import com.akondi.cleanarchitecure.core.extensions.*
import com.akondi.cleanarchitecure.core.platform.BaseFragment
import com.akondi.cleanarchitecure.core.util.IdleTimeListener
import com.akondi.cleanarchitecure.core.util.IdleTimer
import com.akondi.cleanarchitecure.merchants.domain.entities.merchantdetails.MerchantDetailsView
import com.akondi.cleanarchitecure.merchants.domain.entities.failures.MerchantFailure
import com.akondi.cleanarchitecure.merchants.domain.entities.merchants.MerchantView
import com.akondi.cleanarchitecure.merchants.presentation.adapters.OnPageClickListener
import com.akondi.cleanarchitecure.merchants.domain.entities.merchants.Image
import com.akondi.cleanarchitecure.merchants.presentation.animator.MerchantDetailsAnimator
import com.akondi.cleanarchitecure.merchants.presentation.viewmodels.MerchantDetailsViewModel
import com.akondi.cleanarchitecure.merchants.presentation.adapters.MerchantDetailsAdapter
import kotlinx.android.synthetic.main.fragment_merchant_details.*
import kotlinx.android.synthetic.main.slider.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class MerchantDetailsFragment : BaseFragment() {

    companion object {
        private const val PARAM_MERCHANT = "param_merchant"

        fun forMerchant(merchant: MerchantView?): MerchantDetailsFragment {
            val merchantDetailsFragment =
                MerchantDetailsFragment()
            val arguments = Bundle()
            arguments.putParcelable(PARAM_MERCHANT, merchant)
            merchantDetailsFragment.arguments = arguments

            return merchantDetailsFragment
        }
    }

    @Inject
    lateinit var merchantDetailsAnimator: MerchantDetailsAnimator

    private lateinit var merchantDetailsViewModel: MerchantDetailsViewModel

    private lateinit var merchantDetailsAdapter: MerchantDetailsAdapter

    private var merchantImages: MutableList<Image> = ArrayList()

    private var idleTimer: IdleTimer? = null

    private lateinit var dots: Array<ImageView?>


    override fun layoutId() = R.layout.fragment_merchant_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        activity?.let { merchantDetailsAnimator.postponeEnterTransition(it) }

        merchantDetailsViewModel = viewModel(viewModelFactory) {
            observe(merchantDetails, ::renderMerchantDetails)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSlider()
        merchantDetailsViewModel.loadMerchantDetails((arguments?.get(PARAM_MERCHANT) as MerchantView).id)
    }

    override fun onResume() {
        super.onResume()
        restartIdleTimer()
    }

    override fun onPause() {
        super.onPause()
        stopIdleTimer()
    }

    override fun onBackPressed() {
        merchantDetailsAnimator.fadeInvisible(scrollView, merchantDetails)
        if (merchantPlay.isVisible())
            merchantDetailsAnimator.scaleDownView(merchantPlay)
        else
            merchantDetailsAnimator.cancelTransition(merchantPoster)
    }

    private fun renderMerchantDetails(merchant: MerchantDetailsView?) {
        merchant?.let {
            with(merchant) {
                activity?.let {
                    merchantPoster.loadUrlAndPostponeEnterTransition(images[0].url, it)
                    merchantDetailsAdapter.setImages(images)
                    merchantImages.clear()
                    merchantImages.addAll(images)
                    setupBeginViewPager()
                    it.toolbar.title = name
                    restartIdleTimer()
                }
                merchantAbout.text = if (bookable) "bookable online" else " not bookable online"
                merchantRating.text = String.format("%s,/6", reviewScore)
                merchantAddress.text = String.format(
                    "%s, %s, %s, %s",
                    location.address.street,
                    location.address.number,
                    location.address.zipcode,
                    location.address.city
                )
                merchantPhone.text = phoneNumber
                //merchantPlay.setOnClickListener { merchantDetailsViewModel.playMerchant(images[0].url) }
            }
        }
        merchantDetailsAnimator.fadeVisible(scrollView, merchantDetails)
        merchantDetailsAnimator.cancelTransition(merchantPoster)
        merchantDetailsAnimator.scaleUpView(merchantPlay)

    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> {
                notify(R.string.failure_network_connection); close()
            }
            is Failure.ServerError -> {
                notify(R.string.failure_server_error); close()
            }
            is MerchantFailure.NonExistentMerchant -> {
                notify(R.string.failure_merchant_non_existent); close()
            }
        }
    }

    private fun setupSlider() {
        merchantDetailsAdapter =
            MerchantDetailsAdapter(
                merchantImages,
                object :
                    OnPageClickListener {
                    override fun onClick(position: Int) {
                        restartIdleTimer()
                    }
                })

        merchantViewPager.adapter = merchantDetailsAdapter

        merchantViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(i: Int, v: Float, i1: Int) {}

            override fun onPageSelected(i: Int) {
                createDots(i)
            }

            override fun onPageScrollStateChanged(i: Int) {}
        })
    }

    private fun setupBeginViewPager() {
        merchantDetailsAdapter.notifyDataSetChanged()
        createDots(merchantViewPager.currentItem)
    }

    private fun createDots(current_position: Int) {
        if (dotsLayout != null)
            dotsLayout.removeAllViews()

        dots = arrayOfNulls(merchantImages.size)
        var i = 0
        while (i < merchantImages.size) {

            dots[i] = ImageView(appContext)

            if (i == current_position)
                dots[i]?.setImageDrawable(
                    ContextCompat.getDrawable(
                        appContext,
                        R.drawable.active_dot
                    )
                )
            else
                dots[i]?.setImageDrawable(
                    ContextCompat.getDrawable(
                        appContext,
                        R.drawable.default_dot
                    )
                )

            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(4, 0, 4, 0)

            dotsLayout.addView(dots[i], params)
            i++
        }
    }

    private fun stopIdleTimer() {
        idleTimer?.interrupt()
    }

    private fun restartIdleTimer() {
        stopIdleTimer()
        startIdleTimer()
    }

    private fun startIdleTimer() {
        idleTimer = IdleTimer(4, object : IdleTimeListener {
            override fun onTimePassed() {
                goToNextSlide()
                restartIdleTimer()
            }
        })
        idleTimer?.start()
    }

    private fun goToNextSlide() {
        val uiHandler = Handler(Looper.getMainLooper())
        uiHandler.post {
            val fadeIn = AnimationUtils.loadAnimation(
                appContext,
                R.anim.fade_in
            )

            val fadeOut = AnimationUtils.loadAnimation(
                appContext,
                R.anim.fade_out
            )
            merchantViewPager.animation = fadeOut
            merchantDetailsAdapter.notifyDataSetChanged()

            val position = merchantViewPager.currentItem
            if (position < merchantDetailsAdapter.count - 1) {
                merchantViewPager.currentItem = position + 1
                merchantViewPager.animation = fadeIn
                merchantDetailsAdapter.notifyDataSetChanged()
            } else {
                merchantViewPager.currentItem = 0
                merchantViewPager.animation = fadeIn
                merchantDetailsAdapter.notifyDataSetChanged()
            }
        }
    }
}