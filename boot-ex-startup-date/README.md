# Introduction

Startupdate module designed to provide extension of `info` endpoint information with application startup date.

# Installation

_Ametiste-Boot-Ex_ uses maven for build and deployment and its artifacts are available at Bintray repositories. 
You can use the maven dependencies with any dependency management system that supports 
maven dependencies such as Maven, Ivy and Gradle.

To add module to your project using _gradle_:

1. Add _Bintray_ maven repo to your gradle
   
        repositories {
            maven {
                url  "http://dl.bintray.com/ametiste-oss/maven"
            }
        }

2. Add artifact dependency

        dependencies {
            compile "org.ametiste:boot-ex-startup-date:0.2.0-RELEASE"
        }

## Restrictions
 
- Spring Boot - v1.4.0 or later 

# Usage

Module is a Spring Boot starter and provides auto-configuration code that automatically adds _StartupDateInfoContributor_ 
bean to context. 

So all you need to do its add module to project dependencies and enable auto configuration. 