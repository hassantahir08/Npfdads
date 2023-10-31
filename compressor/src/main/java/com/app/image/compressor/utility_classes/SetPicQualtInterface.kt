package com.app.image.compressor.constraint

import com.app.image.compressor.loadBitmap
import com.app.image.compressor.overWrite
import java.io.File

class QualityImageInterface(private val quality: Int) : ImageInterface {
    private var isResolved = false

    override fun requirement_satisfaction(imageFile: File): Boolean {
        return isResolved
    }

    override fun satis(imageFile: File): File {
        val result = overWrite(imageFile, loadBitmap(imageFile), quality = quality)
        isResolved = true
        return result
    }
}

fun ClassImageCompression.quality(quality: Int) {
    cons_function(QualityImageInterface(quality))
}

