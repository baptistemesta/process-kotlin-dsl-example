plugins {
    `kotlin-dsl`
    id("org.jetbrains.kotlin.jvm").version("1.3.11")
}

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compile("org.jetbrains.kotlin:kotlin-script-util")
    compile(kotlin("script-runtime"))
    compile(kotlin("script-util"))
    compile(kotlin("compiler-embeddable"))
    compile("com.bonitasoft.engine.dsl:process-kotlin-dsl:0.0.1")
}

repositories {
    mavenLocal()
    jcenter()
}

gradlePlugin {
    plugins {
        register("bonita-process-plugin") {
            id = "bonita-process"
            implementationClass = "BonitaProcessPlugin"
        }
    }
}