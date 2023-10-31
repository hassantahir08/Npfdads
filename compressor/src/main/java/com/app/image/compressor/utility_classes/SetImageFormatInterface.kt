package com.app.image.compressor.constraint

import android.graphics.Bitmap
import com.app.image.compressor.compressFormat
import com.app.image.compressor.loadBitmap
import com.app.image.compressor.overWrite
import java.io.File


class FormatImageInterface(private val format: Bitmap.CompressFormat) : ImageInterface {

    override fun requirement_satisfaction(imageFile: File): Boolean {
        return format == imageFile.compressFormat()
    }

    override fun satis(imageFile: File): File {
        return overWrite(imageFile, loadBitmap(imageFile), format)
    }
}

fun ClassImageCompression.format(format: Bitmap.CompressFormat) {
    cons_function(FormatImageInterface(format))
}