package com.test.databaselibrary

import com.google.auto.service.AutoService
import java.io.File
import java.util.concurrent.Flow
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement



@AutoService(Flow.Processor::class) // For registering the service
@SupportedSourceVersion(SourceVersion.RELEASE_8) // to support Java 8
@SupportedOptions(FileGenerator.KAPT_KOTLIN_GENERATED_OPTION_NAME)
class FileGenerator : AbstractProcessor() {

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(DaoGenerator::class.java.name)
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latest()
    }


    override fun process(
        set: MutableSet<out TypeElement>?,
        roundEnvironment: RoundEnvironment?
    ): Boolean {

        roundEnvironment?.getElementsAnnotatedWith(DaoGenerator::class.java)
            ?.forEach {
                val className = it.simpleName.toString()
                val pack = processingEnv.elementUtils.getPackageOf(it).toString()
                generateClass(className, pack)
            }
        return true
    }

    private fun generateClass(className: String, pack: String) {
        val fileName = className
        val fileContent = KotlinClassBuilder(fileName, pack).getContent()

        val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME]
        val file = File(kaptKotlinGeneratedDir, "$fileName.kt")

        file.writeText(fileContent)
    }

    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }

}