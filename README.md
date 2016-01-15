Module that uses [Paranamer](http://paranamer.codehaus.org) library to auto-detect names
of Creator (constructor, static factory method, annotated with `@JsonCreator`) methods.

Functionality consists of two `AnnotationIntrospector` implementations:

* `ParanamerAnnotationIntrospector` is a stand-alone introspector to be used with other `AnnotationIntrospectors` (usually using `AnnotationIntrospectorPair`)
* `ParanamerOnJacksonAnnotationIntrospector` is a sub-class of `JacksonAnnotationIntrospector` that can be used instead of default introspector

## Status

[![Build Status](https://travis-ci.org/FasterXML/jackson-module-paranamer.svg)](https://travis-ci.org/FasterXML/jackson-module-paranamer)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.fasterxml.jackson.module/jackson-module-paranamer/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.fasterxml.jackson.module/jackson-module-paranamer/)
[![Javadoc](https://javadoc-emblem.rhcloud.com/doc/com.fasterxml.jackson.module/jackson-module-paranamer/badge.svg)](http://www.javadoc.io/doc/com.fasterxml.jackson.module/jackson-module-paranamer)

Version 2.2 is the first official version; and 2.3 is the first version considered production-ready.

## Usage

Functionality can be used either by directly overriding `AnnotationIntrospector` that `ObjectMapper` uses
or by registering `ParanamerModule` -- module simply appends `ParanamerAnnotationIntrospector` after
current introspector:

```java
ObjectMapper mapper = new ObjectMapper();
// either via module
mapper.registerModule(new ParanamerModule());
// or by directly assigning annotation introspector (but not both!)
mapper.setAnnotationIntrospector(new ParanamerOnJacksonAnnotationIntrospector());
```

Maven information for jar is:

* Group id: `com.fasterxml.jackson.module`
* Artifact id: `jackson-module-paranamer`

## Other

For Javadocs, Download, see: [Wiki](../../wiki).
