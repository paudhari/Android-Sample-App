package com.hari.UI;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Window;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.hari.R;
import com.hari.utils.StringUtils;

import butterknife.BindString;

public class BaseActivity extends FragmentActivity {

    private static final int ACCESS_WIFI_STATE = 1001;
    private static final int INTERNET = 1002;
    private static final int ACCESS_NETWORK_STATE = 1003;

    private Picasso picasso;
    private ProgressDialog mProgressDialog;

    @BindString(R.string.progress_text)
    String loadingText;

    @BindString(R.string.device_out_of_network)
    String noNetwork;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        picasso = Picasso.with(this);
        checkPermission();
    }

    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, INTERNET);
            return;
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_WIFI_STATE}, ACCESS_WIFI_STATE);
            return;
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, ACCESS_NETWORK_STATE);
            return;
        }

    }

    public int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public void loadFileInBackground(String url, final ImageView imgView) {
        if (!StringUtils.isNullOrEmpty(url)) {
            picasso.load(url).into(imgView);
        }
    }

    public void showProgressDialog(String... args) {
        if (isFinishing()) {
            return;
        }
        try {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(this);
                mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                mProgressDialog.setCancelable(false);
            }
            if (args != null && args.length > 0) {
                mProgressDialog.setMessage(args[0]);
            } else {
                mProgressDialog.setMessage("Loading...");
            }
            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes the progress dialog
     */
    public void removeProgressDialog() {
        try {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.hide();
                mProgressDialog.dismiss();
                mProgressDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
