package org.robitron.elfchat;// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Phrase.java

import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

// Referenced classes of package com.robitron.phred:
//            FuzzyRule, Grammar, Rule, ActionRule

public class Phrase extends Vector
{

    public Phrase(String s, String s1)
    {
        stimulus = s;
        String s2;
        for(StringTokenizer stringtokenizer = new StringTokenizer(s, s1, true); stringtokenizer.hasMoreTokens(); addElement(s2))
            s2 = stringtokenizer.nextToken();

    }

    public void stashExpand()
    {
        StringBuffer stringbuffer = new StringBuffer(stimulus.length());
        for(int i = 0; i < size(); i++)
        {
            String s = (String)elementAt(i);
            if(!s.equals(""))
                stringbuffer.append(s).append(" ");
        }

        expanded = stringbuffer.toString().trim();
    }

    public boolean testRule(FuzzyRule fuzzyrule)
    {
        for(int i = 0; i < size(); i++)
            if(fuzzyrule.testWord((String)elementAt(i)))
                return true;

        return false;
    }

    public void bindRule(ActionRule actionrule)
    {
        if(actionrule.hasProperty("bind"))
        {
            String s = actionrule.getProperty("bind");
            for(int i = 0; i < size(); i++)
                if(s.equalsIgnoreCase((String)elementAt(i)))
                {
                    bindAt = i + 1;
                    return;
                }

        }
    }

    public String rePhrase(Grammar grammar, Properties properties)
    {
        StringBuffer stringbuffer = new StringBuffer(200);
        for(int i = bindAt; i < size(); i++)
        {
            String s = grammar.mapTense((String)elementAt(i));
            if(s.equalsIgnoreCase(grammar.lookup("I")))
                s = grammar.lookup("I");
            if(!s.equals(""))
                stringbuffer.append(" ").append(s);
        }

        return stringbuffer.toString();
    }

    public int bindAt;
    public String stimulus;
    public String expanded;
}
