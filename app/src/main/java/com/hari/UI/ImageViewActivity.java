package com.hari.UI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.hari.R;
import com.hari.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ImageViewActivity extends BaseActivity {

    @BindView(R.id.imgFullPhoto)
    ImageView imgFullPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null && intent.getStringExtra(Constants.EXTRA_PHOTO_URL) != null) {
            loadFileInBackground(intent.getStringExtra(Constants.EXTRA_PHOTO_URL), imgFullPhoto);
        }
    }
}
