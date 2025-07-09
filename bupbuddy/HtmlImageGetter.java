package com.example.bupbuddy;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import android.graphics.Bitmap;
import android.graphics.Canvas;

// This class is essential for loading images from URLs inside a TextView
public class HtmlImageGetter implements Html.ImageGetter {

    private final TextView textView;

    public HtmlImageGetter(TextView textView) {
        this.textView = textView;
    }

    @Override
    public Drawable getDrawable(String source) {
        // This is a placeholder drawable that will be updated when the image loads
        final BitmapDrawablePlaceHolder drawable = new BitmapDrawablePlaceHolder();

        // Start loading the image from the URL
        Picasso.get()
                .load(source)
                .placeholder(R.drawable.ic_image_placeholder) // A temporary placeholder icon
                .into(drawable);

        return drawable;
    }

    // A private inner class that acts as a Target for Picasso and a Drawable for the TextView
    private class BitmapDrawablePlaceHolder extends BitmapDrawable implements Target {

        protected Drawable drawable;

        public BitmapDrawablePlaceHolder() {
            // Default constructor
        }

        @Override
        public void draw(Canvas canvas) {
            // If the real drawable is loaded, draw it.
            if (drawable != null) {
                drawable.draw(canvas);
            }
        }

        public void setDrawable(Drawable drawable) {
            this.drawable = drawable;
            // Get the image's dimensions
            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();
            drawable.setBounds(0, 0, width, height);
            setBounds(0, 0, width, height);

            // Force the TextView to redraw itself with the new image
            if (textView != null) {
                textView.setText(textView.getText());
            }
        }

        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            // This is called when Picasso successfully downloads the image
            setDrawable(new BitmapDrawable(textView.getResources(), bitmap));
        }

        @Override
        public void onBitmapFailed(Exception e, Drawable errorDrawable) {
            // You can handle image loading errors here if you want
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
            // This is called before the image starts loading
        }
    }
}