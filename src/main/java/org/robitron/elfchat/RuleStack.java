package org.robitron.elfchat;// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RuleStack.java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Stack;

// Referenced classes of package com.robitron.phred:
//            ActionRule, Rule

public class RuleStack extends Stack
{

    public boolean hasRule(Rule rule)
    {
        for(Enumeration enumeration = elements(); enumeration.hasMoreElements();)
        {
            Rule rule1 = (Rule)enumeration.nextElement();
            if(rule1.getName().equals(rule.getName()))
                return true;
        }

        return false;
    }

    public void removeRule(Rule rule)
    {
        removeElement(rule);
    }

    public void addRule(Rule rule)
    {
        if(rule != null)
        {
            if(hasRule(rule))
                removeRule(rule);
            push(rule);
        }
    }

    public void loadRules(BufferedReader bufferedreader, Hashtable hashtable)
    {
        try
        {
            String s;
            if((s = bufferedreader.readLine()) == null)
                return;
            removeAllElements();
            int i = Integer.parseInt(s);
            for(int j = 0; j < i; j++)
            {
                String s1;
                if((s1 = bufferedreader.readLine()) == null)
                    return;
                ActionRule actionrule = (ActionRule)hashtable.get(s1.trim());
                addRule(actionrule);
            }

            byte byte0 = 50;
            for(int k = 0; k < size() - byte0; k++)
                removeElementAt(0);

            return;
        }
        catch(IOException _ex)
        {
            return;
        }
    }

    public void saveRules(PrintWriter printwriter)
    {
        printwriter.println(size());
        for(int i = 0; i < size(); i++)
        {
            ActionRule actionrule = (ActionRule)elementAt(i);
            printwriter.println(actionrule.getName().trim());
        }

    }

    public RuleStack()
    {
    }
}
