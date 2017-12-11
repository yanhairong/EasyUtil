package yhr.git.com.easyutil.home;

import android.os.Bundle;

import yhr.git.com.easyutil.R;
import yhr.git.com.easyutil.base.BaseActivity;
import yhr.git.com.easyutil.base.LaunchFactory;
import yhr.git.com.easyutil.splash.SplashFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LaunchFactory.start(MainActivity.this, SplashFragment.class);

    }

}
