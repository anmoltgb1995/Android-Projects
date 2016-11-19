package com.android.learnit;

/**
 * Created by sarthakalang on 03/11/15.
 */
import android.view.View;
import android.view.animation.Animation;

/**
 * Created by ANMOL on 10/5/2015.
 */

public class animListener2 implements Animation.AnimationListener {
    View view;
    Animation anim;

    public animListener2(View view, Animation anim) {
        super();
        this.view = view;
        this.anim=anim;
    }

    @Override
    public void onAnimationStart(Animation animation) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        view.startAnimation(anim);
    }
}

