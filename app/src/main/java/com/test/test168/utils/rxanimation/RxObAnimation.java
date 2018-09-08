package com.test.test168.utils.rxanimation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import io.reactivex.Observable;


public class RxObAnimation {

    public static Observable<Animator> afterObAnimationCompleteListener(ObjectAnimator objectAnimator, View view) {
        if (objectAnimator == null || view == null) {
            return Observable.empty();
        }
        return Observable.create(new ObAnimationAfterCompleteOnSubscribe(objectAnimator, view));
    }
}