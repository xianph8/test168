package com.test.test168.utils.rxanimation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.View;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.MainThreadDisposable;


public class ObAnimationAfterCompleteOnSubscribe implements ObservableOnSubscribe<Animator> {

    final ObjectAnimator objectAnimator;

    final View view;

    public ObAnimationAfterCompleteOnSubscribe(ObjectAnimator objectAnimator, View view) {
        this.objectAnimator = objectAnimator;
        this.view = view;
    }

    /**
     * Called for each Observer that subscribes.
     *
     * @param subscriber the safe emitter instance, never null
     * @throws Exception on error
     */
    @Override
    public void subscribe(ObservableEmitter<Animator> subscriber) throws Exception {
        Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (!subscriber.isDisposed()) {
                    subscriber.onNext(animation);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };

        objectAnimator.addListener(animatorListener);
        Log.d("SHF", "ObAnimationAfterCompleteOnSubscribe--" + System.currentTimeMillis());
        objectAnimator.start();
        subscriber.setDisposable(new MainThreadDisposable() {
            @Override
            protected void onDispose() {

            }
        });
    }
}