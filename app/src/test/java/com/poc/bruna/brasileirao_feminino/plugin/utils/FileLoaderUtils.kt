package com.poc.bruna.brasileirao_feminino.plugin.utils

import java.io.File
import java.nio.file.Files
import java.nio.file.Path

object FileLoaderUtils {

    fun getResponse(testClass: Class<*>, testPath: String): String {
        return try {
            val readerPath: Path = getFile(testClass, testPath)!!.toPath()
            String(Files.readAllBytes(readerPath))
        } catch (e: Exception) {
            ""
        }
    }

    private fun getFile(testClass: Class<*>, testPath: String): File? {
        val classLoader = testClass.classLoader
        val resource = classLoader?.getResource(testPath)
        return resource?.let { File(it.path) }
    }
}