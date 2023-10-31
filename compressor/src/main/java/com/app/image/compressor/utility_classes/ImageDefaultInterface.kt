package com.app.image.compressor.constraint

import android.graphics.Bitmap
import com.app.image.compressor.decodeSampledBitmapFromFile
import com.app.image.compressor.determineImageRotation
import com.app.image.compressor.overWrite
import java.io.File

class DefaultImageInterface(
        private val width: Int = 612,
        private val height: Int = 816,
        private val format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
        private val quality: Int = 80
) : ImageInterface {
    private var isResolved = false




    override fun requirement_satisfaction(imageFile: File): Boolean {
        return isResolved
    }

    lateinit var result: File
    override fun satis(imageFile: File): File {
        try {
            try {
                result = decodeSampledBitmapFromFile(imageFile, width, height).run {
                    determineImageRotation(imageFile, this).run {
                        overWrite(imageFile, this, format, quality)
                    }
                }
            }
            catch (e: NullPointerException)
            {

            }
        }
        catch (e: IllegalStateException)
        {

        }

        isResolved = true
        return result
    }
}

fun ClassImageCompression.default(
        width: Int = 612,
        height: Int = 816,
        format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
        quality: Int = 80
) {
    cons_function(DefaultImageInterface(width, height, format, quality))
}