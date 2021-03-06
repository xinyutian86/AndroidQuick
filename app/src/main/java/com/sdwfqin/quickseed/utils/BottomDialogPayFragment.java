package com.sdwfqin.quickseed.utils;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sdwfqin.quickseed.R;

/**
 * 描述：付款弹窗
 *
 * @author 张钦
 * @date 2018/1/17
 */
public class BottomDialogPayFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    private OnDialogClickListener mOnDialogClickListener;
    private RadioGroup mRg;
    private RadioButton mWechat;
    private RadioButton mAlipay;
    private TextView mPayb;

    private int payType = 0;

    /**
     * 按钮监听接口
     */
    public interface OnDialogClickListener {

        /**
         * 付款
         *
         * @param type 0微信，1支付宝
         */
        void pay(int type);
    }

    /**
     * 设置监听
     *
     * @param onDialogClickListener
     */
    public void setOnClickListener(OnDialogClickListener onDialogClickListener) {
        mOnDialogClickListener = onDialogClickListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.bottom_pay, null);
        mRg = view.findViewById(R.id.rg);
        mWechat = view.findViewById(R.id.wechat);
        mAlipay = view.findViewById(R.id.alipay);
        mPayb = view.findViewById(R.id.payb);

        mPayb.setOnClickListener(this);

        dialog.setContentView(view);

        dialog.getWindow().findViewById(R.id.design_bottom_sheet)
                // #00ffffff
                .setBackgroundResource(R.color.tm);

        mRg.setOnCheckedChangeListener((group, checkedId) -> {
            Drawable wechat = getResources().getDrawable(R.mipmap.wechat);
            Drawable alipay = getResources().getDrawable(R.mipmap.alipay);
            Drawable choose1 = getResources().getDrawable(R.mipmap.choose_y);
            Drawable no_choose1 = getResources().getDrawable(R.mipmap.choose_n);
            switch (checkedId) {
                case R.id.wechat:
                    mWechat.setCompoundDrawablesWithIntrinsicBounds(wechat, null, choose1, null);
                    mAlipay.setCompoundDrawablesWithIntrinsicBounds(alipay, null, no_choose1, null);
                    payType = 0;
                    break;
                case R.id.alipay:
                    mWechat.setCompoundDrawablesWithIntrinsicBounds(wechat, null, no_choose1, null);
                    mAlipay.setCompoundDrawablesWithIntrinsicBounds(alipay, null, choose1, null);
                    payType = 1;
                    break;
            }
        });

        return dialog;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.payb:
                mOnDialogClickListener.pay(payType);
                dismiss();
                break;
        }
    }

    public static class Builder {

        private BottomDialogPayFragment mDialogFragment;

        public Builder() {
            mDialogFragment = new BottomDialogPayFragment();
        }

        public Builder setOnClickListener(OnDialogClickListener onClickListener) {
            mDialogFragment.mOnDialogClickListener = onClickListener;
            return this;
        }

        public BottomSheetDialogFragment builder() {
            return mDialogFragment;
        }
    }
}
