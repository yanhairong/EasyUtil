package yhr.git.com.easyutil.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Description:
 *
 * @author YanHaiRong
 * @version 2.0
 * @since 2017/11/20
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
            initView();
            
    }

    private void initView() {
        
    }
}
