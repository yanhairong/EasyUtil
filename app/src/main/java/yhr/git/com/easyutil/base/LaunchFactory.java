package yhr.git.com.easyutil.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import yhr.git.com.easyutil.App;


/**
 * Description: 启动带Toolbar的Activity
 *
 * @author Zhang Fangtao
 * @version 2.0
 * @since 2016/12/1
 */
public class LaunchFactory {

    public static class Type {
        public static final int NEW_TASK = Intent.FLAG_ACTIVITY_NEW_TASK;
        public static final int SINGLE_TOP = Intent.FLAG_ACTIVITY_SINGLE_TOP;
    }

    public interface Creator {
        Intent create();
    }

    public static Intent create(Class<? extends MainFragment> fragment) {
        return create(fragment, null);
    }

    public static Intent create(Class<? extends MainFragment> fragment, Bundle bundle) {
        return create(fragment, bundle, 0);
    }

    public static Intent create(Class<? extends MainFragment> fragment, Bundle bundle, int flag) {
        if (fragment == null) return null;
        Intent intent;
        if (flag == Type.NEW_TASK) {
            intent = new Intent(App.instance().getContext(), NewTaskActivity.class);
        } else if (flag == Type.SINGLE_TOP) {
            intent = new Intent(App.instance().getContext(), SingleTopActivity.class);
        } else {
            intent = new Intent(App.instance().getContext(), StandardActivity.class);
        }
        intent.setFlags(flag);
        intent.putExtra(LaunchFrame.EXTRA_KEY_BUNDLE, bundle);
        intent.putExtra(LaunchFrame.EXTRA_KEY_FRAGMENT, fragment);
        return intent;
    }

    public static void start(Context context, Class<? extends MainFragment> fragment) {
        if (context != null && fragment != null) {
            context.startActivity(create(fragment));
        }
    }

    public static void start(Context context, Creator creator) {
        if (context != null && creator != null) {
            context.startActivity(creator.create());
        }
    }

    public static void start(Fragment fragment, Creator creator) {
        if (fragment != null && creator != null) {
            fragment.startActivity(creator.create());
        }
    }

    public static void startForResult(Activity activity, Creator creator, int requestCode) {
        if (activity != null && creator != null) {
            activity.startActivityForResult(creator.create(), requestCode);
        }
    }

    public static void startForResult(Fragment fragment, Creator creator, int requestCode) {
        if (fragment != null && creator != null) {
            fragment.startActivityForResult(creator.create(), requestCode);
        }
    }

}
