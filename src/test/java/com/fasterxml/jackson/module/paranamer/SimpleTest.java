package com.fasterxml.jackson.module.paranamer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SimpleTest extends ParanamerTestBase
{
    static class CreatorBean
    {
        protected String name;
        protected int age;

        @JsonCreator
        public CreatorBean(int age, String name)
        {
            this.name = name;
            this.age = age;
        }
    }
    
    /*
    /**********************************************************
    /* Unit tests
    /**********************************************************
     */

    public void testSimple() throws Exception
    {
        final String JSON = "{\"name\":\"Bob\", \"age\":40}";
        // First, try without module
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.readValue(JSON, CreatorBean.class);
            fail("should fail");
        } catch (JsonMappingException e) {
            verifyException(e, "has no property name annotation");
        }

        // then with two available modules:
        mapper = new ObjectMapper().registerModule(new ParanamerModule());
        CreatorBean bean = mapper.readValue(JSON, CreatorBean.class);
        assertEquals("Bob", bean.name);
        assertEquals(40, bean.age);

        mapper = new ObjectMapper();
        mapper.setAnnotationIntrospector(new ParanamerOnJacksonAnnotationIntrospector());
        bean = mapper.readValue(JSON, CreatorBean.class);
        assertEquals("Bob", bean.name);
        assertEquals(40, bean.age);
    }
}
