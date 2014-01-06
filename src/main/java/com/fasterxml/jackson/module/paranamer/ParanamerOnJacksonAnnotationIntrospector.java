package com.fasterxml.jackson.module.paranamer;

import java.lang.reflect.*;

import com.thoughtworks.paranamer.BytecodeReadingParanamer;
import com.thoughtworks.paranamer.CachingParanamer;
import com.thoughtworks.paranamer.Paranamer;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.introspect.*;

/**
 * Stand-alone {@link AnnotationIntrospector} that defines functionality
 * to discover names of constructor (and factory method) parameters,
 * on top of default Jackson annotation processing.
 * It can be used as the replacement for vanilla
 * {@link JacksonAnnotationIntrospector}.
 */
public class ParanamerOnJacksonAnnotationIntrospector
    extends JacksonAnnotationIntrospector
{
    private static final long serialVersionUID = 1;

    protected final Paranamer _paranamer;

    public ParanamerOnJacksonAnnotationIntrospector() {
        this(new CachingParanamer(new BytecodeReadingParanamer()));
    }

    public ParanamerOnJacksonAnnotationIntrospector(Paranamer pn) {
        _paranamer = pn;
    }

    @Override
    public PropertyName findNameForDeserialization(Annotated a)
    {
        PropertyName name = super.findNameForDeserialization(a);
        if (name == null) {
            if (a instanceof AnnotatedParameter) {
                String rawName = _findParaName((AnnotatedParameter) a);
                if (rawName != null) {
                    name = new PropertyName(rawName);
                }
            }
        }
        return name;
    }

    /*
    /**********************************************************
    /* Internal methods
    /**********************************************************
     */

    protected String _findParaName(AnnotatedParameter param)
    {
        int index = param.getIndex();
        AnnotatedElement ctor = param.getOwner().getAnnotated();
        String[] names = _paranamer.lookupParameterNames((AccessibleObject) ctor);
        if (names != null && index < names.length) {
            return names[index];
        }
        return null;
    }
}
