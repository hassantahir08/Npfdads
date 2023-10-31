package com.app.image.compressor.constraint

import java.io.File

 interface ImageInterface {

    fun satis(imageFile: File): File



     fun requirement_satisfaction(imageFile: File): Boolean
}