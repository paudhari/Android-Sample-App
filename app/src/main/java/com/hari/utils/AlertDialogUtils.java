package com.hari.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.hari.UI.BaseActivity;


public class AlertDialogUtils {

    public static final int BUTTON_CLICK_FAILURE = 0;
    public static final int BUTTON_CLICK_SUCCESS = 1;

    public static void showAlertDialogWithTwoButtons(Context context, String headerMessage, String messageInfo, String btnPositiveText, String btnNegativeText, final OnButtonClickListener onButtonClick) {
        if (context == null) {
            return;
        }
        if (((BaseActivity) context).isFinishing()) {
            return;
        }
        new AlertDialog.Builder(context).setTitle(headerMessage).setMessage(messageInfo).setPositiveButton(btnPositiveText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                onButtonClick.onButtonClick(BUTTON_CLICK_SUCCESS);
            }
        }).setNegativeButton(btnNegativeText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                onButtonClick.onButtonClick(BUTTON_CLICK_FAILURE);
            }
        }).setCancelable(false).show();
    }

    public static void showAlertDialog(Context context, String headerMessage, String messageInfo, final OnButtonClickListener onButtonClick) {
        if (context == null) {
            return;
        }
        if (((BaseActivity) context).isFinishing()) {
            return;
        }
        if (StringUtils.isNullOrEmpty(headerMessage)) {
            AlertDialog alertDialog = new AlertDialog.Builder(context).setMessage(messageInfo).setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    onButtonClick.onButtonClick(BUTTON_CLICK_SUCCESS);
                }
            }).show();
        } else {
            new AlertDialog.Builder(context).setTitle(headerMessage).setMessage(messageInfo).setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    onButtonClick.onButtonClick(BUTTON_CLICK_SUCCESS);
                }
            }).setCancelable(false).show();
        }
    }

    public interface OnButtonClickListener {
        void onButtonClick(int buttonId);
    }

}
