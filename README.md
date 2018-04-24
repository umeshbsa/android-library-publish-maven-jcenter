### Android Library Publish on Maven/jCenter Repository
This is a example of how to publish my own android library on maven or jcenter and i can used in android app gradle dependency.

- [Create Bintray Account](#create-bi)
- [Package Setup - Layer](#package-setup---layer)
- [License](#license)

### Bintray Account
  * Go to bintray site <br/>
    https://bintray.com/
  * Click on link<br/>`For an Open Source Account`<br/>`Sign Up Here`.<br/>
    Do not create account from `START YOUR FREE TRIAL`
    <img src="https://github.com/umeshbsa/android-project-architecture-diagram/blob/master/screen/network_diagram.png"/>
  * Create your account like this
    <img src="https://github.com/umeshbsa/android-project-architecture-diagram/blob/master/screen/network_diagram.png"/>
  * After created your account you will go to your account profile like this.
    <img src="https://github.com/umeshbsa/android-project-architecture-diagram/blob/master/screen/network_diagram.png"/>
    
  * Click on Add New Repository. and fill all data like this.
     <img src="https://github.com/umeshbsa/android-project-architecture-diagram/blob/master/screen/network_diagram.png"/>
  * After created your repository, you will create package   so click on `Add New Package` and fill all data like this
     <img src="https://github.com/umeshbsa/android-project-architecture-diagram/blob/master/screen/network_diagram.png"/>
       
  * After create your package, you must be remember of repository name(maven1), package name(resume-file-upload) and your username(bsaumesh).
    because it will be needed in future of android library project.
  * This is finish of bintray side.
  
  * Now go to Android Studio and create android project and create android library(resume-file-upload) as you know and as you want to publish on bintray.
  * Go to android library gradle(build.gradle)
  ```java
     apply plugin: 'com.android.library'
     apply plugin: 'com.github.dcendents.android-maven'
     apply plugin: 'com.jfrog.bintray'
     
     android {
         compileSdkVersion rootProject.COMPILE_SDK_VERSION // compile sdk version
         defaultConfig {
             minSdkVersion rootProject.MIN_SDK_VERSION // minmum sdk version
             targetSdkVersion rootProject.COMPILE_SDK_VERSION // target sdk version
             versionCode 1
             versionName "1.0"
         }
         buildTypes {
             release {
                 minifyEnabled false
                 proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
             }
         }
     }
     
     dependencies {
         implementation fileTree(dir: 'libs', include: ['*.jar'])
         implementation "com.android.support:appcompat-v7:$rootProject.ANDROID_SUPPORT_VERSION"
     }
     
     task sourcesJar(type: Jar) {
         from android.sourceSets.main.java.srcDirs
         classifier = 'sources'
     }
     
     task javadoc(type: Javadoc) {
         source = android.sourceSets.main.java.srcDirs
         classpath += project.files(android.getBootClasspath().join(File.pathSeparator))
     }
     
     task javadocJar(type: Jar, dependsOn: javadoc) {
         classifier = 'javadoc'
         from javadoc.destinationDir
     }
     
     artifacts {
         archives javadocJar
         archives sourcesJar
     }
     
     group = 'com.app.resume' // library package name
     version = '1.0.0' // library version
     
     install {
         repositories.mavenInstaller {
             pom.project {
                 name 'Umesh Kumar' // Your good name
                 description 'A library for finding out whether someone is on the list or the nice list'
                 url 'https://github.com/umeshbsa/android-library-publish-maven-jcenter' //
                 inceptionYear '2018'
     
                 packaging 'aar'
                 groupId 'com.app.resume' // package name of your library
                 artifactId 'resume-file-upload' //must be same of library name as bintray
                 version '1.0.0'
     
                 licenses {
                     license {
                         name 'The MIT License (MIT)'
                         url 'https://opensource.org/licenses/MIT'
                     }
                 }
                 scm {
                     connection 'https://github.com/umeshbsa/android-library-publish-maven-jcenter.git'
                     url 'https://github.com/umeshbsa/android-library-publish-maven-jcenter'
     
                 }
                 developers {
                     developer {
                         id = 'umeshbsa'
                         name 'Umesh Kumar'
                     }
                 }
             }
         }
     }
     // Bintray
     Properties properties = new Properties()
     properties.load(project.rootProject.file('local.properties').newDataInputStream())
     
     
     bintray {
         // user and key, fetch from local.properties file.
         // Follow this step
         /*
             sdk.dir=/home/umesh/Android/Sdk
             user=umeshbsa // your username of bitray account
             key=dd3e540bf673436cee35fc839607fe6252c117cc // bintray key.
             // To find this key follow this step-
             // 1. Go to edit profile and click 'Api Key' from list. 
                2. Put password and show key. copy and paste here.
                
          Note : Do not this
           user="umeshbsa"
           key="dd3e540bf673436cee35fc839607fe6252c117cc"
          */
         
         user = properties.getProperty('user') 
         key = properties.getProperty('key')
         configurations = ['archives']
         pkg {
             repo = 'maven' // must be same as on bintray repo name
             name = 'resume-file-upload' // must be same of library name as bintray
             userOrg = 'umeshbsa' // this is username of bintray account
             licenses = ['MIT']
             vcsUrl = 'https://github.com/umeshbsa/android-library-publish-maven-jcenter'
             publish = true
             version {
                 name = '1.0.0'
                 desc = 'This is updated version 1.0.0'
                 released = new Date()
                 vcsTag = 'v1.0.0'
             }
         }
     }
```
 * Add this code in Root gradle
```java
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.1.1'
        classpath 'com.jfrog.bintray.gradle:gradle-bintray-plugin:1.7'
        classpath 'com.github.dcendents:android-maven-gradle-plugin:1.5'
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}


ext {
    COMPILE_SDK_VERSION = 27
    MIN_SDK_VERSION = 15
    ANDROID_SUPPORT_VERSION = '27.1.1'
    VERSION_CODE = 1
    VERSION_NAME = '1.0'
}

```
 
#### Licence

    Copyright 2018 Umesh Kumar

    Licensed under the Apache License, Version 2.0 (the "License")
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
          

