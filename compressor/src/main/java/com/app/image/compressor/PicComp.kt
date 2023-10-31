package com.app.image.compressor

import android.content.Context
import com.app.image.compressor.constraint.ClassImageCompression
import com.app.image.compressor.constraint.default
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileNotFoundException
import kotlin.coroutines.CoroutineContext

object PicComp {

    lateinit var classImageCompression: ClassImageCompression
    lateinit var result:  File
    suspend fun file(
        context: Context,
        imageFile: File,
        coroutineContext: CoroutineContext = Dispatchers.IO,
        classImageCompressionPatch: ClassImageCompression.() -> Unit = { default() }
    ) = withContext(coroutineContext) {

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
                                    try
                                    {
                                        classImageCompression = ClassImageCompression().apply(classImageCompressionPatch)
                                    }
                                    catch (e: NoSuchFileException)
                                    {

                                    }
                                }
                                catch (e: IllegalStateException)
                                {

                                }
                            }
                            catch (e: IllegalArgumentException)
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
                                try {
                                    try
                                    {
                                        result = copyToCache(context, imageFile)
                                    }
                                    catch (e: NoSuchFileException)
                                    {
                                        result = imageFile
                                    }
                                }
                                catch (e: IllegalStateException)
                                {
                                    result = imageFile
                                }
                            }
                            catch (e: IllegalArgumentException)
                            {
                                result = imageFile
                            }
                        }
                        catch (e: FileNotFoundException)
                        {
                            result = imageFile
                        }
                    }
                    catch (e: UninitializedPropertyAccessException)
                    {
                        result = imageFile
                    }
                }
                catch (e: NullPointerException)
                {
                    result = imageFile
                }
            }
            catch (e: FileNotFoundException)
            {
                result = imageFile
            }
        }
        catch (e: Exception)
        {
            result = imageFile
        }

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
                                try {
                                    try
                                    {
                                        classImageCompression.imageInterfaces.forEach { constraint ->
                                            while (constraint.requirement_satisfaction(result as File).not()) {
                                                result = constraint.satis(result as File)
                                            }
                                        }
                                    }
                                    catch (e: NoSuchFileException)
                                    {

                                    }
                                }
                                catch (e: IllegalStateException)
                                {

                                }
                            }
                            catch (e: IllegalArgumentException)
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



        return@withContext result
    }
}