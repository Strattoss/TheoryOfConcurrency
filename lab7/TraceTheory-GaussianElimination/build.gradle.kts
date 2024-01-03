plugins {
    id("java")
    application
}

application {
    mainClass = "tt/concurrent_gaussian_elimination/ConcurrentLinearEquationSolver"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}


tasks.test {
    useJUnitPlatform()
}