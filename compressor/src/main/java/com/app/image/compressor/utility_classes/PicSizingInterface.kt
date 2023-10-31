package com.app.image.compressor.constraint

import com.app.image.compressor.loadBitmap
import com.app.image.compressor.overWrite
import java.io.File

class SizeImageInterface(
        private val maxFileSize: Long,
        private val stepSize: Int = 10,
        private val maxIteration: Int = 10,
        private val minQuality: Int = 10
) : ImageInterface {
    private var iteration: Int = 0

    override fun requirement_satisfaction(imageFile: File): Boolean {
        return imageFile.length() <= maxFileSize || iteration >= maxIteration
    }

    override fun satis(imageFile: File): File {
        iteration++
        val quality = (100 - iteration * stepSize).takeIf { it >= minQuality } ?: minQuality
        return overWrite(imageFile, loadBitmap(imageFile), quality = quality)
    }
}

fun ClassImageCompression.size(maxFileSize: Long, stepSize: Int = 10, maxIteration: Int = 10) {
    cons_function(SizeImageInterface(maxFileSize, stepSize, maxIteration))
}