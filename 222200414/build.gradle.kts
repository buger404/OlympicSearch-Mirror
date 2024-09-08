plugins {
    id("java")
    application
    `java-library`
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "io.github.buger404"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("io.github.buger404.OlympicSearch")
}

repositories {
    mavenCentral()
}

dependencies {
    api("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.1")
    api("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.17.1")
    api("com.fasterxml.jackson.module:jackson-module-parameter-names:2.17.1")
    api("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.17.1")
    api("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.17.1")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    compileOnly ("org.projectlombok:lombok:1.18.34")
    annotationProcessor ("org.projectlombok:lombok:1.18.34")

    testCompileOnly ("org.projectlombok:lombok:1.18.34")
    testAnnotationProcessor ("org.projectlombok:lombok:1.18.34")
}

tasks{
    test {
        useJUnitPlatform()
    }

    shadowJar {

    }

    withType<JavaCompile>{
        options.encoding = "UTF-8"
    }

    withType<JavaExec> {
        defaultCharacterEncoding = "UTF-8"
    }
}
