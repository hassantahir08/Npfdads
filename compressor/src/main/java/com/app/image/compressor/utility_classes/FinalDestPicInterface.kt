package com.app.image.compressor.constraint

import java.io.File

class DestinationImageInterface(private val destination: File) : ImageInterface {
    override fun requirement_satisfaction(imageFile: File): Boolean {
        return imageFile.absolutePath == destination.absolutePath
    }


//    all functions isn this place

    override fun satis(imageFile: File): File {
        return imageFile.copyTo(destination, true)
    }
}




fun ClassImageCompression.destination(destination: File) {
    cons_function(DestinationImageInterface(destination))
}