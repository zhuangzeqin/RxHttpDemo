package com.eeepay.zzq.rxhttpdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.text.method.NumberKeyListener;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * 描述：自定义蓝湖上统一的样式
 * 作者：zhuangzeqin
 * 时间: 2020/3/1-9:43
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class ItemEditText extends ConstraintLayout {
    private TextView labelTv;//左边文本标签
    private String label, hint, edit;
    private EditText editText;//文本输入框
    private Boolean isEdit;//是否要编辑
    private Boolean isNumber;//是否输入数字
    private String isDigits;//是否限制输入字符
    private int editTextColor, hintTextColor, labelTextColor;//颜色
    private int maxLength;//长度设置
    private int lines;//行数设置

    public ItemEditText(Context context) {
        this(context, null);
    }

    public ItemEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ItemEditTextStyle);
        label = typedArray.getString(R.styleable.ItemEditTextStyle_labelText);//左边
        hint = typedArray.getString(R.styleable.ItemEditTextStyle_hintText);//
        edit = typedArray.getString(R.styleable.ItemEditTextStyle_editText);//
        labelTextColor = typedArray.getColor(R.styleable.ItemEditTextStyle_labelTextColor, getResources().getColor(R.color.item_label_color));
        editTextColor = typedArray.getColor(R.styleable.ItemEditTextStyle_editTextColor, getResources().getColor(R.color.item_editxt_color));
        hintTextColor = typedArray.getColor(R.styleable.ItemEditTextStyle_hintTextColor, getResources().getColor(R.color.item_editxt_color));
        //add by zhuangzeqin 2017年8月31日10:48:56 新增自定义属性 isDigits
        isDigits = typedArray.getString(R.styleable.ItemEditTextStyle_digits);
        isNumber = typedArray.getBoolean(R.styleable.ItemEditTextStyle_isNumber, false);
        isEdit = typedArray.getBoolean(R.styleable.ItemEditTextStyle_isEdit, true);
        maxLength = typedArray.getInt(R.styleable.ItemEditTextStyle_maxLength, 25);
        lines = typedArray.getInt(R.styleable.ItemEditTextStyle_lines, 5);
        //获得自定义布局view
        View view = LayoutInflater.from(context).inflate(R.layout.view_item_edit, this, true);
        labelTv = (TextView) view.findViewById(R.id.tv_item_nam);
        editText = (EditText) view.findViewById(R.id.et_item_input);
        setViewContent();
        typedArray.recycle();
    }

    private void setViewContent() {
        if (editTextColor != -1)
            editText.setTextColor(editTextColor);

        if (hintTextColor != -1) {
            editText.setHintTextColor(hintTextColor);
        }
        if (labelTextColor != -1) {
            labelTv.setTextColor(labelTextColor);
        }
        if (label != null) {
            labelTv.setText(label);
        }
        if (hint != null) {
            editText.setHint(hint);
        }
        if (edit != null) {
            editText.setText(edit);
        }
        editText.setEnabled(isEdit ? true : false);
        editText.setFocusableInTouchMode(isEdit ? true : false);
        editText.setFocusable(isEdit ? true : false);
        if (isNumber) {
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
        if (!TextUtils.isEmpty(isDigits)) {
            setDigits(isDigits);
        }
        setFilter(maxLength);
        if (lines != 0) {
            if (lines == 1)
                editText.setSingleLine();
            else
                editText.setLines(lines);
        }
    }

    /**
     * @param num 设置输入长度限制
     */
    public void setFilter(int num) {
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(num)});
    }

    public void setDigits(String digits) {
        editText.setKeyListener(DigitsKeyListener.getInstance(digits));
    }

    /**
     * @return 返回标签的文本
     */
    public String getLabel() {
        return labelTv.getText().toString();
    }

    /**
     * @param label 设置标签内容
     */
    public void setLabel(String label) {
        labelTv.setText(label);
    }

    /**
     * @param color 设置标签字体颜色
     */
    public void setLabelTextColor(int color) {
        labelTv.setTextColor(getResources().getColor(color));
    }

    /**
     * 设置背景颜色
     *
     * @param color
     */
    public void setEditBgColor(@ColorRes int color) {
        editText.setBackgroundColor(getResources().getColor(color));
    }

    /**
     * 设置编辑文本的颜色
     *
     * @param color
     */
    public void setEditTextColor(int color) {
        editText.setTextColor(getResources().getColor(color));
    }

    /**
     * 设置编辑提示文本的颜色
     *
     * @param color
     */
    public void setEditTextHintColor(int color) {
        editText.setHintTextColor(getResources().getColor(color));
    }

    /**
     * @return 设置焦点
     */
    public boolean setSubViewFocus() {
        return editText.requestFocus();
    }

    /**
     * @param isEdit 设置EditText是否可点击
     */
    public void setEnableEdit(boolean isEdit) {
        editText.setEnabled(isEdit);
    }

    /**
     * @param hintText 设置提示右侧文本
     */
    public void setHintText(String hintText) {
        editText.setHint(hintText);
    }


    /**
     * @return 返回编辑框的内容
     */
    public String getEditContent() {
        return editText.getText().toString().trim();
    }

    /**
     * @return 返回编辑框对象
     */
    public EditText getEditText() {
        return editText;
    }

    /**
     * @param content 设置编辑框内容
     */
    public void setEditContent(String content) {
        editText.setText(content);
    }

    /**
     * 设置输入格式
     * InputType.TYPE_CLASS_TEXT ---> 只能为身份证0123456789Xx
     */
    public void setInputType(int inputType) {
        switch (inputType) {
            case InputType.TYPE_CLASS_TEXT:
                editText.setKeyListener(new NumberKeyListener() {
                    @Override
                    public int getInputType() {
                        return InputType.TYPE_CLASS_TEXT;
                    }

                    protected char[] getAcceptedChars() {
                        char numberChars[] = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'X', 'x'};
                        return numberChars;
                    }
                });
                break;
            case InputType.TYPE_NUMBER_FLAG_DECIMAL:
                editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                break;
            default:
                editText.setInputType(inputType);
                break;
        }
    }

    /**
     * @param editListener 设置EditText文本编辑监听
     */
    public void setEditListener(TextWatcher editListener) {
        editText.addTextChangedListener(editListener);
    }

    public void setTransformationMethod(PasswordTransformationMethod method) {
        editText.setTransformationMethod(method);
    }

    /**
     * 设置右边输入框的背景
     *
     * @param drawable
     */
    public void setRightBackDrawable(Drawable drawable) {
        if (editText != null) {
            editText.setBackgroundDrawable(drawable);
        }
    }
}
