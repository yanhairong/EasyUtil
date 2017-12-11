package yhr.git.com.easyutil.base;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.View;

import com.broaddeep.safe.base.databind.DataBinder;
import com.broaddeep.safe.base.view.ViewDelegate;

import yhr.git.com.easyutil.R;

/**
 * Description: 功能页面入口Fragment
 *
 * @author Zhang Fangtao
 * @version 2.0
 * @since 2016/12/2
 */
public abstract class MainFragment<T extends ViewDelegate, D extends DataBinder> extends BaseFragment<T, D> {

    public void onUserInteraction() {
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return false;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    public boolean toolbarVisible() {
        return true;
    }

    /**
     * Toolbar显示文字
     */
    public String toolbarTitle() {
        return null;
    }

    /**
     * Toolbar返回键按钮是否可见
     * @return true 可见；false 不可见
     */
    public boolean toolbarBackVisible() {
        return true;
    }

    /**
     * Toolbar设置按钮是否可见
     * @return true 可见；false 不可见
     */
    public boolean toolbarMenuVisible() {
        return false;
    }

    /**
     * Toolbar设置按钮点击响应
     */
    public void toolbarMenuClick() {
    }

    /**
     * Toolbar设置按钮点击响应
     *
     * @param menuAnchor 如果需要弹出PopupMenu，可使用该view作为anchor
     */
    public void toolbarMenuClick(View menuAnchor) {
        toolbarMenuClick();
    }

    /**
     * Toolbar第二菜单按钮点击响应
     */
    public void toolbarSecMenuClick() {
    }

    /**
     * Toolbar后退按钮点击响应
     */
    public void toolbarBackClick() {
        Activity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    public int toolbarBackgroundColor() {
        return R.color.common_toolbar_color;
    }

//    public void setToolbarBackgroundColor(int color) {
//        Activity activity = getActivity();
//        if (activity == null || !(activity instanceof LaunchFrame)) {
//            return;
//        }
//        LaunchFrame frame = (LaunchFrame) activity;
//        ToolBar toolBar = frame.getToolBar();
//        if (toolBar != null) {
//            toolBar.setBackgroundColor(color);
//        }
//    }

    /**
     * Toolbar菜单按钮资源
     * @return Drawable
     */
    public Drawable toolbarMenuDrawable() {
        return getActivity().getResources().getDrawable(R.mipmap.common_ic_more);
    }

    /**
     * Toolbar第二菜单按钮资源
     *
     * @return Drawable
     */
    public Drawable toolbarSecMenuDrawable() {
        return null;
    }

    public Drawable getContentViewBackground() {
        return null;
    }
}
