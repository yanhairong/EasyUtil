package yhr.git.com.easyutil.base;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.os.Bundle;

import com.broaddeep.safe.base.databind.DataBindFragment;
import com.broaddeep.safe.base.databind.DataBinder;
import com.broaddeep.safe.base.view.ViewDelegate;

public class BaseFragment<T extends ViewDelegate, D extends DataBinder> extends DataBindFragment<T, D> {

    public BaseFragment() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onPause() {
        super.onPause();
    }

    public void onResume() {
        super.onResume();
    }

    public void onDetach() {
        super.onDetach();
    }

    protected void onThemeChanged(Theme theme) {
    }

    protected void onSkinChanged() {
    }

    public D getDataBinder() {
        return null;
    }

    protected Class<T> getViewDelegateClass() {
        return null;
    }
}
