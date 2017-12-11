package yhr.git.com.easyutil.base;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.KeyEvent;
import android.view.View;

import yhr.git.com.easyutil.R;
import yhr.git.com.easyutil.util.ToolBar;


/**
 * Description: 带ToolBar的Activity
 *
 * @author Zhang Fangtao
 * @version 2.0
 * @since 2016/12/1
 */
public class LaunchFrame extends BaseActivity {

    protected static final String EXTRA_KEY_FRAGMENT = "main_fragment";
    protected static final String EXTRA_KEY_BUNDLE = "main_bundle";

//    protected SkinProxy getProxy() {
//        return ThemeFacade.getProxy(getApplicationContext());
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewLayoutId());
        init(getIntent());
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(LaunchFrame.class.getSimpleName());
        if (fragment != null && fragment instanceof MainFragment) {
            MainFragment mf = (MainFragment) fragment;
            mf.onUserInteraction();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(LaunchFrame.class.getSimpleName());
        if (fragment != null && fragment instanceof MainFragment) {
            MainFragment mf = (MainFragment) fragment;
            if (mf.onKeyDown(keyCode, event)) {
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(LaunchFrame.class.getSimpleName());
        if (fragment != null && fragment instanceof MainFragment) {
            MainFragment mf = (MainFragment) fragment;
            if (!event.isCanceled() && mf.onKeyUp(keyCode, event)) {
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        init(intent);
    }

    protected int getContentViewLayoutId() {
//        return getProxy().getLayoutId("common_main_toolbar_layout");
        return R.layout.common_main_toolbar_layout;
    }

    protected int getContentViewId() {
        return R.id.common_contentView;
    }

    protected ToolBar getToolBar() {
        return (ToolBar) findViewById(R.id.common_toolbar);
    }

    protected int getFrameId() {
        return R.id.common_frame;
    }

    protected void init(Intent intent) {
        Class<?> cls = (Class<?>) intent.getSerializableExtra(EXTRA_KEY_FRAGMENT);
        if (cls == null) return;

        Bundle bundle = intent.getBundleExtra(EXTRA_KEY_BUNDLE);
        Fragment fragment = Fragment.instantiate(this, cls.getName(), bundle);
        if (!(fragment instanceof MainFragment)) return;

        getSupportFragmentManager()
                .beginTransaction()
                .replace(getContentViewId(), fragment)
                .commit();

        initToolBar((MainFragment) fragment);
        initContentViewBackground((MainFragment) fragment);
    }

    protected void initToolBar(final MainFragment fragment) {
        final ToolBar bar = getToolBar();
        if (bar == null) return;

        bar.setBackgroundColor(fragment.toolbarBackgroundColor());

        if (!fragment.toolbarVisible()) {
            bar.setVisibility(View.GONE);
            return;
        }

        if (!fragment.toolbarBackVisible()) {
            bar.setLeftIconGone();
        }

        String title = fragment.toolbarTitle();

        if (title != null && title.length() != 0) {
            bar.setTitle(title);
        }

        bar.setOnToolbarClickListener(new ToolBar.OnToolbarClickListener() {
            @Override
            public void onLeftClicked() {
                fragment.toolbarBackClick();
            }

            @Override
            public void onRightClicked() {
                fragment.toolbarMenuClick(bar.getRightView());
            }
        });
        bar.setOnToolbarSecMenuClickListener(new ToolBar.OnToolbarClickListener() {
            @Override
            public void onRightClicked() {
                fragment.toolbarSecMenuClick();
            }
        });

        if (fragment.toolbarMenuVisible()) {
            Drawable drawable = fragment.toolbarMenuDrawable();
            if (drawable != null) {
                bar.setRightIcon(fragment.toolbarMenuDrawable());
            }
            Drawable secMenuDrawable = fragment.toolbarSecMenuDrawable();
            if (secMenuDrawable != null) {
                bar.setRightIconTwo(secMenuDrawable);
            }
        }
    }

    private void initContentViewBackground(MainFragment fragment) {
        try {
            Drawable drawable = fragment.getContentViewBackground();
            if (drawable != null) {
                int frameId = getFrameId();
                View contentView = findViewById(frameId);
                if (contentView != null) {
                    ViewCompat.setBackground(contentView, drawable);
                }
            }
        } catch (Exception ignored) {
        }
    }


}
