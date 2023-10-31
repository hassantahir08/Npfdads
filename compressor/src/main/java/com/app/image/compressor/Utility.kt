package com.app.image.compressor

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Environment
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream


private fun dir_path_13(context: Context) =  File(Environment
    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS+File.separator+"npfd_compre"+File.separator+"NPFE_"+System.currentTimeMillis()), "")

private fun dir_path(context: Context) =  File(Environment
    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS+File.separator+"npfd_compre"+File.separator+"NPFE_"+System.currentTimeMillis()), "")

fun File.compressFormat() = when (extension.toLowerCase()) {
    "png" -> Bitmap.CompressFormat.PNG
    "webp" -> Bitmap.CompressFormat.WEBP
    else -> Bitmap.CompressFormat.JPEG
}

fun Bitmap.CompressFormat.extension() = when (this) {
    Bitmap.CompressFormat.PNG -> "png"
    Bitmap.CompressFormat.WEBP -> "webp"
    else -> "jpg"
}

fun loadBitmap(imageFile: File) = BitmapFactory.decodeFile(imageFile.absolutePath).run {
    determineImageRotation(imageFile, this)
}

fun decodeSampledBitmapFromFile(imageFile: File, reqWidth: Int, reqHeight: Int): Bitmap {
    return try {
        try {
            try
            {
                try
                {
                    try
                    {
                        try
                        {
                            try
                            {
                                try
                                {

                                    try
                                    {
                                        try {
                                            BitmapFactory.Options().run {
                                                inJustDecodeBounds = true
                                                BitmapFactory.decodeFile(imageFile.absolutePath, this)

                                                inSampleSize = calculateInSampleSize(this, reqWidth, reqHeight)

                                                inJustDecodeBounds = false
                                                BitmapFactory.decodeFile(imageFile.absolutePath, this)
                                            }
                                        }
                                        catch (e: NullPointerException)
                                        {

                                        }

                                    }
                                    catch (e: UninitializedPropertyAccessException)
                                    {

                                    }

                                }
                                catch (e: NoSuchFileException)
                                {

                                }
                            }
                            catch (e: FileNotFoundException)
                            {

                            }
                        }
                        catch (e: UninitializedPropertyAccessException)
                        {

                        }
                    }
                    catch (e: NullPointerException)
                    {

                    }
                }
                catch (e: FileNotFoundException)
                {

                }
            }
            catch (e: Exception)
            {

            }

        }
        catch (e: IllegalArgumentException)
        {

        }
    }
    catch (e: IllegalStateException)
    {

    } as Bitmap
}

fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
    // Raw height and width of image
    val (height: Int, width: Int) = options.run { outHeight to outWidth }
    var inSampleSize = 1

    if (height > reqHeight || width > reqWidth) {

        val halfHeight: Int = height / 2
        val halfWidth: Int = width / 2

        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
        // height and width larger than the requested height and width.
        while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
            inSampleSize *= 2
        }
    }

    return inSampleSize
}

fun determineImageRotation(imageFile: File, bitmap: Bitmap): Bitmap {
    val exif = ExifInterface(imageFile.absolutePath)
    val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0)
    val matrix = Matrix()
    when (orientation) {
        6 -> matrix.postRotate(90f)
        3 -> matrix.postRotate(180f)
        8 -> matrix.postRotate(270f)
    }
    return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
}

lateinit var filename: File
internal fun copyToCache(context: Context, imageFile: File): File {

    try
    {
        try
        {
            try
            {
                try
                {
                    try
                    {
                        try
                        {
//                            filename = imageFile.copyTo(File("${dir_path(context)}${imageFile.name}"), true)

                            if (SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                            {
                                filename = imageFile.copyTo(File("${dir_path_13(context)}${imageFile.name}"), true)
                            }
                            else
                            {
                                filename = imageFile.copyTo(File("${dir_path(context)}${imageFile.name}"), true)
                            }

                        }
                        catch (e: NoSuchFileException)
                        {

                        }
                    }
                    catch (e: FileNotFoundException)
                    {

                    }
                }
                catch (e: UninitializedPropertyAccessException)
                {

                }
            }
            catch (e: NullPointerException)
            {

            }
        }
        catch (e: FileNotFoundException)
        {

        }
    }
    catch (e: Exception)
    {

    }


    return filename
}

fun overWrite(imageFile: File, bitmap: Bitmap, format: Bitmap.CompressFormat = imageFile.compressFormat(), quality: Int = 100): File {
    val result = if (format == imageFile.compressFormat()) {
        imageFile
    } else {
        File("${imageFile.absolutePath.substringBeforeLast(".")}.${format.extension()}")
    }
    imageFile.delete()
    saveBitmap(bitmap, result, format, quality)
    return result
}

fun saveBitmap(bitmap: Bitmap, destination: File, format: Bitmap.CompressFormat = destination.compressFormat(), quality: Int = 100) {
    destination.parentFile?.mkdirs()
    var fileOutputStream: FileOutputStream? = null
    try {
        fileOutputStream = FileOutputStream(destination.absolutePath)
        bitmap.compress(format, quality, fileOutputStream)
    } finally {
        fileOutputStream?.run {
            flush()
            close()
        }
    }
}