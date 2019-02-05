/*
 * Copyright (C) 2017 MUKTADIR
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://muktadir.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.muktadir.android.util.helper;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

import id.zelory.compressor.Compressor;

import static android.graphics.BitmapFactory.decodeByteArray;

/**
 * ****************************************************************************
 * * Copyright © 2017 MUKTADIR, All rights reserved.
 * *
 * * Created by:
 * * Name : ZOARDER AL MUKTADIR
 * * Date : 10/25/2018
 * * Email : muktadir@muktadir.com
 * *
 * * Purpose :
 * *
 * * Last Edited by : ZOARDER AL MUKTADIR on 10/25/2018.
 * * History:
 * * 1: Create the Class
 * * 2:
 * *
 * * Last Reviewed by : ZOARDER AL MUKTADIR on 10/25/2018.
 * ****************************************************************************
 */

public class ImageProcessingUtil {

    private static final int CAMERA_CAPTURE = 125;
    private static final int CROP_PIC = 150;

    private static HashMap<String, Bitmap> userCardMap = new HashMap<>();

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampmerge_intermidiate_and_BCS_30_BCS_12 leSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static String savePicture(final Context context, final Bitmap finalBitmap, String imageName) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/saved_images");
        boolean result = myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fileName;
        if (TextUtils.isEmpty(imageName)) {
            fileName = "Image-" + n + ".jpg";
        } else {
            fileName = imageName;
        }

        final File file = new File(myDir, fileName);
        if (file.exists()) {
            result = file.delete();
        }
        try {
            new AsyncTask<Void, Void, Void>() {
                /**
                 * Override this method to perform a computation on a background thread. The
                 * specified parameters are the parameters passed to {@link #execute}
                 * by the caller of this task.
                 * <p>
                 * This method can call {@link #publishProgress} to publish updates
                 * on the UI thread.
                 *
                 * @param params The parameters of the task.
                 * @return A result, defined by the subclass of this task.
                 * @see #onPreExecute()
                 * @see #onPostExecute
                 * @see #publishProgress
                 */
                @Override
                protected Void doInBackground(Void... params) {
                    FileOutputStream out;
                    try {
                        out = new FileOutputStream(file);
                        finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                        out.flush();
                        out.close();
                        new Compressor(context).compressToFile(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    return null;
                }
            }.execute();


        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return file.getAbsolutePath();
    }

    public static String savePicture(final Context context, byte[] imagebyte, String imageName) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/saved_images");
        boolean result = myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fileName;
        if (TextUtils.isEmpty(imageName)) {
            fileName = "Image-" + n + ".jpg";
        } else {
            fileName = imageName;
        }

        final Bitmap finalBitmap = decodeByteArray(imagebyte, 0, imagebyte.length);
        final File file = new File(myDir, fileName);
        if (file.exists()) {
            result = file.delete();
        }
        try {

            new AsyncTask<Void, Void, Void>() {
                /**
                 * Override this method to perform a computation on a background thread. The
                 * specified parameters are the parameters passed to {@link #execute}
                 * by the caller of this task.
                 * <p>
                 * This method can call {@link #publishProgress} to publish updates
                 * on the UI thread.
                 *
                 * @param params The parameters of the task.
                 * @return A result, defined by the subclass of this task.
                 * @see #onPreExecute()
                 * @see #onPostExecute
                 * @see #publishProgress
                 */
                @Override
                protected Void doInBackground(Void... params) {
                    FileOutputStream out;
                    try {
                        out = new FileOutputStream(file);
                        finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                        out.flush();
                        out.close();
                        new Compressor(context).compressToFile(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    return null;
                }
            }.execute();

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return file.getAbsolutePath();
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static void captureImage(Context context) {
        try {
            // use standard intent to capture an image
            Intent captureIntent = new Intent(
                    MediaStore.ACTION_IMAGE_CAPTURE);
            // we will handle the returned data in onActivityResult
            ((Activity) context).startActivityForResult(captureIntent, CAMERA_CAPTURE);
        } catch (ActivityNotFoundException anfe) {
            anfe.printStackTrace();
            Toast toast = Toast.makeText(context, "This device doesn't support the crop action!",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * this function does the crop operation.
     */
    public static void performCrop(Context context, Uri picUri) {
        // take care of exceptions
        try {
            // call the standard crop action intent (the user device may not
            // support it)
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            // indicate image type and Uri
            cropIntent.setDataAndType(picUri, "image/*");
            // set crop properties
            cropIntent.putExtra("crop", "true");
            // indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 2);
            cropIntent.putExtra("aspectY", 1);
            // indicate output X and Y
            cropIntent.putExtra("outputX", 512);
            cropIntent.putExtra("outputY", 512);
            // retrieve data on return
            cropIntent.putExtra("return-data", true);
            // start the activity - we handle returning in onActivityResult
            ((Activity) context).startActivityForResult(cropIntent, CROP_PIC);
        }
        // respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
            anfe.printStackTrace();
            Toast toast = Toast
                    .makeText(context, "This device doesn't support the crop action!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    //  Bitmap bitmap =
    public static Bitmap getImageBitmapfromUri(Context inContext, Uri uri) {
        try {
            return MediaStore.Images.Media.getBitmap(inContext.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    private static byte[] getBytesFromBitmap(Bitmap picture) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        picture.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    public static byte[] getByteFromImagePath(String imagePath) {
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();

    }

    public static byte[] getBytefromDrawable(Context context, Integer Id) {

        Drawable drawable = ContextCompat.getDrawable(context, Id);
        Bitmap bitmap = ((BitmapDrawable) Objects.requireNonNull(drawable)).getBitmap();
        return getBytesFromBitmap(bitmap);
    }


    private static Bitmap getCardBitmap(String userId) {
        if (TextUtils.isEmpty(userId)) return null;

        return userCardMap.get(userId);
    }

    private static void storeCard(String userId, Bitmap bitmap) {
        if (TextUtils.isEmpty(userId)) return;

        userCardMap.put(userId, bitmap);
    }


    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, (float) pixels, (float) pixels, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    public static Bitmap getBitmapFromBytes(byte[] profileImageByte) {
        return BitmapFactory.decodeByteArray(profileImageByte, 0, profileImageByte.length);
    }

    /**
     * @param input   bitmap
     * @param context context
     * @return blurry bitmap
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static Bitmap getBlutrryBitMap(Bitmap input, Context context) {
        try {
            RenderScript rsScript = RenderScript.create(context);
            Allocation alloc = Allocation.createFromBitmap(rsScript, input);

            ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(rsScript, Element.U8_4(rsScript));
            blur.setRadius(99);
            blur.setInput(alloc);

            Bitmap result = Bitmap.createBitmap(input.getWidth(), input.getHeight(), Bitmap.Config.ARGB_8888);
            Allocation outAlloc = Allocation.createFromBitmap(rsScript, result);

            blur.forEach(outAlloc);
            outAlloc.copyTo(result);

            rsScript.destroy();
            return result;
        } catch (Exception e) {
            // TODO: handle exception
            return input;
        }

    }

    /**
     * @param filePath filePath
     */
    public static Bitmap getLogoBitmap(String filePath) {
        File image = new File(filePath);

        if (image.exists()) {
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(), bmOptions);
            if (bitmap == null) return null;
            bitmap = Bitmap.createScaledBitmap(bitmap, 24, 24, true);
            return bitmap;
        }

        return null;

    }

    public void sssdsd(Context context, Bitmap bitmap) {
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), bitmap);
        final float roundPx = (float) bitmap.getWidth() * 0.06f;
        roundedBitmapDrawable.setCornerRadius(roundPx);
    }
}
