package yhr.git.com.easyutil.splash;

import android.os.Bundle;
import android.view.View;

import com.broaddeep.safe.base.databind.DataBinder;

import yhr.git.com.easyutil.base.MainFragment;
import yhr.git.com.easyutil.splash.view.SplashView;

/**
 * Description:通用版引导页
 *
 * @author Li Jianying
 * @version 2.0
 * @since 2017-5-10
 */

public class SplashFragment extends MainFragment<SplashView, DataBinder> {

    @Override
    protected Class<SplashView> getViewDelegateClass() {
        return SplashView.class;
    }


    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        mViewDelegate.initView();
    }

    @Override
    public String toolbarTitle() {
        return "首页";
    }
}
