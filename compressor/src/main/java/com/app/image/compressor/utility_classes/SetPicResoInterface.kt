package com.app.image.compressor.constraint

import android.graphics.BitmapFactory
import com.app.image.compressor.calculateInSampleSize
import com.app.image.compressor.decodeSampledBitmapFromFile
import com.app.image.compressor.determineImageRotation
import com.app.image.compressor.overWrite
import java.io.File

class ResolutionImageInterface(private val width: Int, private val height: Int) : ImageInterface {

    override fun requirement_satisfaction(imageFile: File): Boolean {
        return BitmapFactory.Options().run {
            inJustDecodeBounds = true
            BitmapFactory.decodeFile(imageFile.absolutePath, this)
            calculateInSampleSize(this, width, height) <= 1
        }
    }

    override fun satis(imageFile: File): File {
        return try {
            try {
                decodeSampledBitmapFromFile(imageFile, width, height).run {
                    determineImageRotation(imageFile, this).run {
                        overWrite(imageFile, this)
                    }
                }
            }
            catch (e: NullPointerException)
            {

            } as File
        }
        catch (e: IllegalStateException)
        {

        } as File
    }
}

fun ClassImageCompression.resolution(width: Int, height: Int) {
    cons_function(ResolutionImageInterface(width, height))
}