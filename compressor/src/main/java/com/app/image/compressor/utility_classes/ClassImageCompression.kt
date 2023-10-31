package com.app.image.compressor.constraint

import java.io.FileNotFoundException

class ClassImageCompression {
    internal val imageInterfaces: MutableList<ImageInterface> = mutableListOf()

    fun cons_function(imageInterface: ImageInterface) {

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
                                imageInterfaces.add(imageInterface)
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
}