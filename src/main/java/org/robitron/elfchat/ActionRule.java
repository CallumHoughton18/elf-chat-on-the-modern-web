package org.robitron.elfchat;// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ActionRule.java

import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

// Referenced classes of package com.robitron.phred:
//            Rule, Fred

public class ActionRule extends Rule
{

    public ActionRule(String s)
    {
        super(s);
        url = new Vector();
    }

    public void shuffle()
    {
        sel = Math.abs(Fred.rand.nextInt()) % size();
    }

    public void setPriority()
    {
        if(hasProperty("priority"))
            priority = Integer.parseInt(getProperty("priority"));
    }

    public int select()
    {
        int i = sel++;
        if(sel == size())
            sel = 0;
        return i;
    }

    public String nextAction(Properties properties)
    {
        String s = (String)elementAt(select());
        StringTokenizer stringtokenizer = new StringTokenizer(s, " ", true);
        StringBuffer stringbuffer = new StringBuffer(200);
        String s1;
        for(; stringtokenizer.hasMoreTokens(); stringbuffer.append(s1))
        {
            s1 = stringtokenizer.nextToken();
            if(s1.startsWith("$"))
                s1 = properties.getProperty(s1.substring(1));
        }

        return stringbuffer.toString();
    }

    private int sel;
    public int sum;
    public int priority;
    public Vector url;
}
