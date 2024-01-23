
val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val exposed_version: String by project
val h2_version: String by project
val postgres_version: String by project
val koin_version:String by project
val jedis_version:String by project
val mockk_version : String by project
val hikaricp_version: String by project



plugins {
    kotlin("jvm") version "1.9.10"
    id("io.ktor.plugin") version "2.3.5"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.10"
}

group = "com.example"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")

    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")//doo)

    implementation("com.h2database:h2:$h2_version")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("org.postgresql:postgresql:$postgres_version")

    implementation("io.ktor:ktor-server-request-validation:$ktor_version")

    implementation("io.ktor:ktor-server-status-pages:$ktor_version")

    //Redis
    implementation("redis.clients:jedis:$jedis_version")

    implementation("com.zaxxer:HikariCP:$hikaricp_version")


    implementation("io.ktor:ktor-serialization-gson-jvm")
    implementation("io.ktor:ktor-server-host-common-jvm")
    implementation("io.ktor:ktor-server-status-pages-jvm")

    implementation("io.ktor:ktor-server-sessions:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.insert-koin:koin-ktor:$koin_version")

    //pdf  and xml

    implementation("com.itextpdf:itextpdf:5.5.13.3")
    implementation("javax.xml.bind:jaxb-api:2.3.1")
    implementation("com.sun.xml.bind:jaxb-core:2.3.0.1")
    implementation("com.sun.xml.bind:jaxb-impl:2.3.3")

    //gmail
    implementation("org.apache.commons:commons-email:1.5")
//    implementation("com.sun.mail:jakarta.mail:2.0.1")
//    implementation("com.sun.mail:javax.mail:1.6.2")





    testImplementation("io.mockk:mockk:$mockk_version")

    testImplementation("io.ktor:ktor-client-mock:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    testImplementation("io.ktor:ktor-server-test-host:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin_version")
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    testImplementation(kotlin("script-runtime"))
//    implementation("io.ktor:ktor-server-core-jvm")
//    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
//    implementation("io.ktor:ktor-server-content-negotiation-jvm")
//
//    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
//    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
//    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")//doo)
//
//    implementation("com.h2database:h2:$h2_version")
//
//    implementation("org.postgresql:postgresql:$postgres_version")
//
//    implementation("io.ktor:ktor-server-request-validation:$ktor_version")
//
//    implementation("io.ktor:ktor-server-status-pages:$ktor_version")
//
//    implementation("io.ktor:ktor-serialization-gson-jvm")
//    implementation("io.ktor:ktor-server-host-common-jvm")
//    implementation("io.ktor:ktor-server-status-pages-jvm")
//    implementation("io.ktor:ktor-server-sessions-jvm")
//    implementation("io.ktor:ktor-server-auth-jvm")
//    implementation("io.ktor:ktor-server-netty-jvm")
//    implementation("ch.qos.logback:logback-classic:$logback_version")
//    implementation("io.insert-koin:koin-ktor:$koin_version")
//
//    testImplementation("io.ktor:ktor-server-test-host:$ktor_version")
//    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin_version")
//    testImplementation("io.ktor:ktor-server-tests-jvm")
//    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
//    testImplementation(kotlin("script-runtime"))
}
