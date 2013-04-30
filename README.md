Module that uses [Paranamer](http://paranamer.codehaus.org) library to auto-detect names
of Creator (constructor, static factory method, annotated with `@JsonCreator`) methods.

Functionality consists of two `AnnotationIntrospector` implementations:

* `ParanamerAnnotationIntrospector` is a stand-alone introspector to be used with other `AnnotationIntrospectors` (usually using `AnnotationIntrospectorPair`)
* `ParanamerOnJacksonAnnotationIntrospector` is a sub-class of `JacksonAnnotationIntrospector` that can be used instead of default introspector

## Status

Version 2.2 is the first public version; but due to simplicity it should be production ready
once official Maven release is released.

## Usage

Functionality can be used either by directly overriding `AnnotationIntrospector` that `ObjectMapper` uses
or by registering `ParanamerModule` -- module simply appends `ParanamerAnnotationIntrospector` after
current introspector:

    ObjectMapper mapper = new ObjectMapper();
    // either via module
    mapper.registerModule(new ParanamerModule());
    // or by directly assigning annotation introspector (but not both!)
    mapper.setAnnotationIntrospector(new ParanamerOnJacksonAnnotationIntrospector());

Maven information for jar is:

* Group id: `com.fasterxml.jackson.module`
* Artifact id: `jackson-module-paranamer`

## Other

For Javadocs, Download, see: [Wiki](../../jackson-module-paranamer/wiki).
