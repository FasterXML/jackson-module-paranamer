package com.fasterxml.jackson.module.paranamer;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Convenience module that registers stand-alone {@link ParanamerOnJacksonAnnotationIntrospector}
 * after existing introspectors, to add support for discovering names of
 * creator (constructor, factory method) parameters automatically, without
 * explicit annotations.
 *<p>
 * Note that use of this module is optional: the only thing it does is register
 * annotation introspector; so you can instead choose to do this from your
 * custom module, or directly configure {@link ObjectMapper}.
 */
public class ParanamerModule
    extends SimpleModule
{
    private static final long serialVersionUID = 1L;
    
    public ParanamerModule() {
        super(PackageVersion.VERSION);
    }
    
    @Override
    public void setupModule(Module.SetupContext context)
    {
        super.setupModule(context);
        // Append after other introspectors (instead of before) since
        // explicit annotations should have precedence
        context.appendAnnotationIntrospector(new ParanamerAnnotationIntrospector());
    }
}
