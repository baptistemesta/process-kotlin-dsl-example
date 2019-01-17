import com.bonitasoft.engine.dsl.process.Process
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.script.jsr223.KotlinJsr223JvmLocalScriptEngineFactory
import javax.script.ScriptEngineManager

class BonitaProcessPlugin : Plugin<Project> {
    override fun apply(project: Project) {

        with(project) {

            val extension = extensions.create("process", BonitaProcessExtension::class.java)

            task("buildProcess") {
                description = "build the designated bonita process"
                doFirst {
                    val scriptEngineManager = ScriptEngineManager()
                    val receiver = scriptEngineManager.getEngineByExtension("kts")
                    logger.info("engine: $receiver")

                    scriptEngineManager.engineFactories.forEach {
                        logger.info(it.toString())
                    }
                    with(receiver) {
                        val readText = file("src/main/resources/${extension.fileName}.kts").readText()
                        logger.info("Building process \n${readText}")
                        val eval = eval(readText)
                        logger.info("Eval result: \n${eval}")
                        val process = eval as Process
                        buildDir.mkdir()
                        val file = buildDir.resolve("${extension.fileName}.bar")
                        file.delete()
                        process.export(file)
                    }
                }
            }

            afterEvaluate {
                dependencies.add("compile", "com.bonitasoft.engine.dsl:process-kotlin-dsl:0.0.1")
            }
        }


    }
}