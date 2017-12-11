package yhr.git.com.easyutil.splash.view;


import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.broaddeep.safe.base.view.BaseViewDelegate;

import yhr.git.com.easyutil.R;

/**
 * Description: 通用版view
 *
 * @author Li Jianying
 * @version 2.0
 * @since 2017-5-10
 */

public class SplashView extends BaseViewDelegate {
    private Button button;

    @Override
    public int getLayoutId() {
        return R.layout.guide_view_layout;
    }

    public void initView() {
        button = getContentView().findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getAttachedContext(),"登录",Toast.LENGTH_SHORT).show();
            }
        });

//        get(getProxy().getId("guide_ll_statrt_app")).setVisibility(View.GONE);
//
//        SmartImageView splash_word = get(getProxy().getId("splash_word"));
//        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(splash_word, "alpha", 0.6f, 1.0f);
//        alphaAnimation.setDuration(2000);
//        alphaAnimation.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                animationFinish();
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//                animationFinish();
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//            }
//
//            private void animationFinish() {
//                if (!Settings.isTheFirstLaunching()) {
//                    HomeModel.instance().startHomePage();
//                }
//            }
//        });
//        alphaAnimation.start();
    }
}
