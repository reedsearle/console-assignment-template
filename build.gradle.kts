/*
 *  Copyright 2024 CNM Ingenuity, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
plugins {
    // TODO (for curriculum writer): Replace the java plugin with application if the assignment 
    //   includes an application class.
    java 
    jacoco
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(libs.versions.java.get())
    }
}

// TODO (for curriculum writer): Uncomment the following two blocks if the assignment includes an
//   application class.
/*
application {
    mainClass = project.property("mainClass") as String
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}
*/

dependencies {
    testImplementation(libs.junit.aggregator)
    testRuntimeOnly(libs.junit.engine)
}

tasks.javadoc {
    with(options as StandardJavadocDocletOptions) {
        links("https://docs.oracle.com/en/java/javase/${libs.versions.java.get()}/docs/api/")
    }
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
}
