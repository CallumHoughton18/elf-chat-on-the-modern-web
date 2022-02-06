package org.robitron.elfchat;// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FuzzySet.java

import java.util.Hashtable;

// Referenced classes of package com.robitron.phred:
//            ActionRule

public class FuzzySet
{

    public FuzzySet(int i, String s)
    {
        ref = s;
        prob = i;
    }

    public FuzzySet(int i, ActionRule actionrule)
    {
        rule = actionrule;
        prob = i;
    }

    public void linkRule(Hashtable hashtable)
    {
        if(ref != null)
        {
            rule = (ActionRule)hashtable.get(ref);
            if(rule != null)
            {
                ref = null;
                return;
            }
            System.err.println("FuzzySet: rule " + ref + " not found.");
        }
    }

    public ActionRule rule;
    public int prob;
    protected String ref;
}
