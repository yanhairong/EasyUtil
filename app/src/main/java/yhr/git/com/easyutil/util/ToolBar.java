package yhr.git.com.easyutil.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.broaddeep.safe.base.view.ViewAntiFastClickListener;

import yhr.git.com.easyutil.R;

/**
 * Description:
 *
 * @author Zhang Fangtao
 * @version 1.0
 * @since 16/6/22
 */
public class ToolBar extends RelativeLayout {
    static public abstract class OnToolbarClickListener {
        public void onLeftClicked() {
        }

        public void onRightClicked() {
//            Intent intent = new Intent();
//            intent.setClass(App.instance().getContext(), HomeMainSettingActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            App.instance().getContext().startActivity(intent);
        }
    }

    private OnToolbarClickListener mListener, mListenerSec;

    public void setOnToolbarClickListener(OnToolbarClickListener listener) {
        mListener = listener;
    }

    public void setOnToolbarSecMenuClickListener(OnToolbarClickListener listener) {
        mListenerSec = listener;
    }

    private ImageView mLeftIV;
    private ImageView mRightIV;
    private ImageView mRightIVTwo;
    private ImageView mRightIVThree;
    private TextView mTextView;

    private int mLeftIcon;
    private int mRightIcon;
    private int mTitleText;
    private int mTitleColor;
    private boolean hasElevation;
    private LayoutInflater inflater;
    private boolean hasRightButton;

    public ToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.common_toolbar_layout, this);

        mLeftIV = (ImageView)view.findViewById(R.id.iv_common_toolbar_left);
        mRightIV = (ImageView)view.findViewById(R.id.iv_common_toolbar_right);
        
        mRightIVTwo = (ImageView) view.findViewById(R.id.iv_common_toolbar_right_two);
//        mRightIVThree = (ImageView) view.findViewById(R.id.iv_common_toolbar_right_three);
        mTextView = (TextView) view.findViewById(R.id.tv_common_toolbar_title);

        TypedArray ta =context.getTheme().obtainStyledAttributes(attrs,  R.styleable.ToolBar, 0, 0);

        mLeftIcon = ta.getResourceId(R.styleable.ToolBar_tb_leftDrawable,-1);
        mRightIcon = ta.getResourceId(R.styleable.ToolBar_tb_rightDrawable,-1);
        mTitleText = ta.getResourceId(R.styleable.ToolBar_tb_titleText,-1);
        mTitleColor = ta.getResourceId(R.styleable.ToolBar_tb_titleColor,-1);

        hasElevation = ta.getBoolean(R.styleable.ToolBar_tb_hasElevation,false);
        hasRightButton = ta.getBoolean(R.styleable.ToolBar_tb_hasRightButton, true);

        init();

        ta.recycle();
    }

    private void init() {
        if (mLeftIcon != -1) {
            mLeftIV.setVisibility(VISIBLE);
            mLeftIV.setImageResource(mLeftIcon);
            mLeftIV.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ViewAntiFastClickListener.isFastClick()) return;
                    if (mListener != null) {
                        mListener.onLeftClicked();
                    }
                }
            });
        } else {
            mLeftIV.setVisibility(GONE);
            LayoutParams params = (LayoutParams) mTextView.getLayoutParams();
            params.leftMargin = DisplayUtil.dp2px(15);
            mTextView.requestLayout();
        }
//        if (hasRightButton) {
//            mRightIV.setVisibility(VISIBLE);
//            mRightIV.setImageResource(R.drawable.ic_settings_common);
//            mRightIV.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mListener != null) {
//                        mListener.onRightClicked();
//                    }
//                }
//            });
//            if (mRightIcon != -1) {
//                mRightIV.setImageResource(mRightIcon);
//            }
//        } else {
//            mRightIV.setVisibility(GONE);
//        }

        if (mRightIcon != -1) {
            mRightIV.setVisibility(VISIBLE);
            mRightIV.setImageResource(mRightIcon);
            mRightIV.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ViewAntiFastClickListener.isFastClick()) return;
                    if (mListener != null) {
                        mListener.onRightClicked();
                    }
                }
            });
        } else {
            mRightIV.setVisibility(GONE);
        }

        if (mTitleText != -1) {
            mTextView.setText(mTitleText);
        }

        if (mTitleColor != -1) {
            mTextView.setTextColor(getResources().getColor(mTitleColor));
        }

        if (hasElevation) {
            ViewCompat.setElevation(this, DisplayUtil.dp2px(8));
        }
    }

    public void setLeftIconGone() {
        mLeftIV.setVisibility(GONE);
        mTextView.setText("   安全上网");

    }

    public void setIvRightTwoShow(){
        mRightIVTwo.setVisibility(View.VISIBLE);
    }

//    public void setIvRightThreeShow(){
//        mRightIVThree.setVisibility(View.VISIBLE);
//    }

    public void setRightTwoClicked(OnClickListener onClickListener){
        setIvRightTwoShow();
        mRightIVTwo.setOnClickListener(onClickListener);
    }

//    public void setRightThreeClicked(OnClickListener onClickListener){
//        setIvRightThreeShow();
//        mRightIVThree.setOnClickListener(onClickListener);
//    }


    public void setRightGone() {
        mRightIV.setVisibility(GONE);
    }

    public void setTitle(int id) {
        mTextView.setText(id);
    }

    public void setTitle(CharSequence cs) {
        mTextView.setText(cs);
    }

    public String getTitle() {
        return mTextView.getText().toString();
    }

    public void setTitleColor(int id) {
        mTextView.setTextColor(getResources().getColor(id));
    }

    public void setBgColor(int id) {
        setBackgroundColor(getResources().getColor(id));
    }

    public void setRightIcon(int id) {
        if (id != -1) {
            mRightIV.setVisibility(VISIBLE);
            mRightIV.setImageResource(id);
            mRightIV.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ViewAntiFastClickListener.isFastClick()) return;
                    if (mListener != null) {
                        mListener.onRightClicked();
                    }
                }
            });
        } else {
            mRightIV.setVisibility(GONE);
        }
    }

    public void setRightIcon(Drawable drawable) {
        setRightIcon(mRightIV, drawable, mListener);
    }

    public void setRightIconTwo(Drawable drawable) {
        setRightIcon(mRightIVTwo, drawable, mListenerSec);
    }

    private void setRightIcon(ImageView iv, Drawable drawable, final OnToolbarClickListener listener) {
        if (drawable != null) {
            iv.setVisibility(VISIBLE);
            iv.setImageDrawable(drawable);
            iv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ViewAntiFastClickListener.isFastClick()) return;
                    if (listener != null) {
                        listener.onRightClicked();
                    }
                }
            });
        } else {
            iv.setVisibility(GONE);
        }
    }

    public View getRightView() {
        return mRightIV;
    }
}
