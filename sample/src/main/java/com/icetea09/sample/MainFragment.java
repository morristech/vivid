package com.icetea09.sample;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.icetea09.vivid.imagepicker.ImagePicker;
import com.icetea09.vivid.model.Image;

import java.util.List;

public class MainFragment extends Fragment {

    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView = (ImageView) view.findViewById(R.id.img_fragment);

        view.findViewById(R.id.button_pick_fragment)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ImagePicker.create(MainFragment.this)
                                .returnAfterFirst(true) // set whether pick action or camera action should return immediate result or not. Only works in single mode for image picker
                                .start(0); // image selection title
                    }
                });

        view.findViewById(R.id.button_close)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getFragmentManager().beginTransaction()
                                .remove(MainFragment.this)
                                .commitAllowingStateLoss();
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        List<Image> images = ImagePicker.getImages(data);
        if (images != null && !images.isEmpty()) {
            imageView.setImageBitmap(BitmapFactory.decodeFile(images.get(0).getPath()));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
