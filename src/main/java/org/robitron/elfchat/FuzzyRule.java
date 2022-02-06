package org.robitron.elfchat;// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FuzzyRule.java

import java.util.Hashtable;

// Referenced classes of package com.robitron.phred:
//            Rule, FuzzySet

public class FuzzyRule extends Rule
{

    public FuzzyRule(String s)
    {
        super(s);
    }

    public void addElement(int i, String s)
    {
        addElement(new FuzzySet(i, s));
    }

    public void normalize(Hashtable hashtable)
    {
        int i = 0;
        for(int j = 0; j < size(); j++)
        {
            FuzzySet fuzzyset = (FuzzySet)elementAt(j);
            fuzzyset.linkRule(hashtable);
            i += fuzzyset.prob;
        }

        for(int k = 0; k < size(); k++)
        {
            FuzzySet fuzzyset1 = (FuzzySet)elementAt(k);
            fuzzyset1.prob = (int)Math.round(100D * ((double)fuzzyset1.prob / (double)i));
        }

    }

    public boolean testWord(String s)
    {
        return super.name.equals("*") || super.name.equalsIgnoreCase(s);
    }
}
