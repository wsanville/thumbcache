package co.touchlab.thumbcache.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

/**
 * User: William Sanville
 * Date: 7/17/12
 * Time: 4:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImageLoadedHandler extends Handler
{
    private boolean mFadeInBitmap = true;
    private Bitmap mLoadingBitmap;
    protected Context mContext;
    private static final int FADE_IN_TIME = 200;

    public ImageLoadedHandler(Context context, boolean fadeInBitmap, Bitmap loadingBitmap)
    {
        this.mContext = context;
        this.mFadeInBitmap = fadeInBitmap;
        this.mLoadingBitmap = loadingBitmap;
    }

    @Override
    public void handleMessage(Message msg)
    {
        if (msg.obj instanceof Tuple)
        {
            Tuple t = (Tuple)msg.obj;

            if (t.imageView != null && t.bitmap != null && t.data != null)
            {
                //Make sure the Message given has data that matches what the ImageView expects
                if (ImageWorker.isValidData(t.data, t.imageView))
                    setImageBitmap(t.imageView, t.bitmap);
            }
            t.bitmap = null;
        }
    }

    /**
     * Called when the processing is complete and the final bitmap should be set on the ImageView.
     *
     * @param imageView
     * @param bitmap
     */
    private void setImageBitmap(ImageView imageView, Bitmap bitmap)
    {
        if (mFadeInBitmap)
        {
            // Transition drawable with a transparent drwabale and the final bitmap
            final TransitionDrawable td =
                    new TransitionDrawable(new Drawable[] {
                            new ColorDrawable(android.R.color.transparent),
                            new BitmapDrawable(mContext.getResources(), bitmap)
                    });
            // Set background to loading bitmap
            imageView.setBackgroundDrawable(
                    new BitmapDrawable(mContext.getResources(), mLoadingBitmap));

            imageView.setImageDrawable(td);
            td.startTransition(FADE_IN_TIME);
        }
        else
        {
            imageView.setImageBitmap(bitmap);
        }
    }

    public static class Tuple
    {
        private Object data;
        private Bitmap bitmap;
        private ImageView imageView;

        public Tuple(Object data, Bitmap bitmap, ImageView imageView)
        {
            this.data = data;
            this.bitmap = bitmap;
            this.imageView = imageView;
        }
    }

    public static ImageLoadedHandler makeDefault(Context context, ImageWorker imageWorker)
    {
        return new ImageLoadedHandler(context, imageWorker.isFadeInBitmap(), imageWorker.getLoadingBitmap());
    }
}
