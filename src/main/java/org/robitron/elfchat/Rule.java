package org.robitron.elfchat;// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Rule.java

import java.util.Properties;
import java.util.Vector;

public class Rule extends Vector
{

    public Rule()
    {
        prop = new Properties();
        name = "";
    }

    public Rule(String s)
    {
        prop = new Properties();
        name = "";
        name = s.trim();
    }

    public String getName()
    {
        return name;
    }

    public boolean hasProperty(String s)
    {
        return prop.containsKey(s);
    }

    public boolean testProperty(String s, Properties properties)
    {
        boolean flag = true;
        if(s != null)
        {
            TSVector tsvector = new TSVector(s, " ", false, false);
            String s1 = tsvector.pop().trim();
            String s2 = s.substring(s1.length() + 1).trim();
            if(properties.containsKey(s1) && !s2.equals(properties.getProperty(s1).trim()))
                flag = false;
        }
        return flag;
    }

    public String getProperty(String s)
    {
        if(hasProperty(s))
            return prop.getProperty(s);
        else
            return null;
    }

    public void putProperty(String s, String s1)
    {
        prop.put(s.trim(), s1.trim());
    }

    public static final String TYPE_ACTION = "action:";
    public static final String TYPE_FUZZY = "fuzzy:";
    public static final String TYPE_INTRO = "intro:";
    public static final String TYPE_REGEX = "regex:";
    public static final String PROP_ABOUT = "about";
    public static final String PROP_EQUALS = "equals";
    public static final String PROP_BIND = "bind";
    public static final String PROP_INVOKES = "invokes";
    public static final String PROP_HTML = "html";
    public static final String PROP_NEXT = "next";
    public static final String PROP_PRIORITY = "priority";
    public static final String PROP_REPEAT = "repeat";
    public static final String PROP_REQUIRES = "requires";
    public static final String PROP_TELLME = "tellme";
    public static final String PROP_URL = "url";
    protected Properties prop;
    protected String name;
}
