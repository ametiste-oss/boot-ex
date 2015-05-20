# Introduction

SmartCORSFilter module designed to provide simple solution to get CORS, and ready to use out of the box.  

# At a Glance

Module provides configurable servlet filter that takes duty on setting up and delivery of simple 
CORS environment.  

By default, module would provide a simpliets, but usable for development, environment that contains
the set of usually required _Access-Control-_ headers:

    Access-Control-Allow-Origin → *
    Access-Control-Allow-Headers → x-requested-with, content-type, user-agent
    Access-Control-Allow-Methods → POST, GET, OPTIONS, DELETE    
    Access-Control-Max-Age → 3600

Provided headers set can be modified using application properties, see _Usage_ section for details.

# Installation

_Ametiste-Boot-Ex_ uses maven for build and deployment and its artifacts are available at Bintray repositories. 
You can use the maven dependencies with any dependency management system that supports 
maven dependencies such as Maven, Ivy and Gradle.

To add _Ametiste-Boot-Ex::SmartCORSFilter_ to your project using _gradle_:

1. Add _Bintray_ maven repo to your gradle
   
        repositories {
            maven {
                url  "http://dl.bintray.com/masted/maven"
            }
        }

2. Add artifact dependency

        dependencies {
            compile "org.ametiste:boot-ex-smart-cors:0.1.0-RELEASE"
        }
      
# Usage

To enable _SmartCORSFilter_ just annotate a _spring-boot_ main application class by 
the _@EnableSmartCORSFilter_ annotation.

Module configuration properties contains only two properties:

* ametiste.boot-ex.smart.cors.headers._headerName_ - map-like property that defines a header name and value.                   
* ametiste.boot-ex.smart.cors.expandDefault - if this property value is set to true, than 
a configuration will expand the set of default properties. Note, default headers will be redefined 
by definitions given in a properties, if present. 

*NOTE*, only _access control_ headers that prefixed as the _Access-Control-_ are allowed to be used 
as _CORS_ headers.

# Configuration Examples

To configure a set of provided headers just add some header-properties to _application.properties_ file: 

    ametiste.boot-ex.smart.cors.headers.Access-Control-Allow-Origin=example.com
    ametiste.boot-ex.smart.cors.headers.Access-Control-Allow-Headers=x-requested-with
      
This configuration would expand the default headers set and redefine the value of 
_Access-Control-Allow-Origin_ and _Access-Control-Allow-Headers_ headers to the provided. Default
_Access-Control-Allow-Methods_ and _Access-Control-Max-Age_ will be presentd and would have default values.

    

To configure an unique set of provided headers just add some header-properties 
to _application.properties_ file and disable default expanding:

    ametiste.boot-ex.smart.cors.expandDefault=false
    ametiste.boot-ex.smart.cors.headers.Access-Control-Allow-Origin=example.com
    ametiste.boot-ex.smart.cors.headers.Access-Control-Allow-Headers=x-requested-with

This configuration would not expand the default headers set and just define the set of two headers: 
_Access-Control-Allow-Origin_ and _Access-Control-Allow-Headers_.   
