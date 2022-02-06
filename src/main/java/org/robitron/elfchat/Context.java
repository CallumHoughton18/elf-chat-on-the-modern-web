package org.robitron.elfchat;// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Context.java

import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Stack;
import java.util.Vector;

// Referenced classes of package com.robitron.phred:
//            ActionRule, Fred, Rule, RuleStack

public class Context
{

    public Context(Fred fred)
    {
        stimLog = new Stack();
        respLog = new Stack();
        used = new RuleStack();
        next = new RuleStack();
        dirt = new Properties();
        query = "";
        expect = new Vector();
        actsHash = fred.actsHash;
    }

    public void logRule(ActionRule actionrule)
    {
        used.addRule(actionrule);
        if(next.hasRule(actionrule))
            next.removeRule(actionrule);
        if(actionrule.hasProperty("next"))
        {
            TSVector tsvector = new TSVector(actionrule.getProperty("next"), " ", false, false);
            for(int i = 0; i < tsvector.size(); i++)
            {
                String s = tsvector.pop();
                ActionRule actionrule1 = (ActionRule)actsHash.get(s);
                next.addRule(actionrule1);
            }

        }
        url = (Vector)actionrule.url.clone();
    }

    public void logChat(String s, String s1)
    {
        stimLog.push(s);
        respLog.push(s1);
    }

    public void loadFile()
    {
    }

    protected void saveStack(PrintWriter printwriter, Stack stack)
    {
        printwriter.println(stack.size());
        for(int i = 0; i < stack.size(); i++)
        {
            String s = (String)stack.elementAt(i);
            printwriter.println(s);
        }

    }

    public void saveFile()
    {
    }

    public String[] getLinks()
    {
        String as[] = null;
        if(url != null && url.size() > 0)
            url.copyInto(as = new String[url.size()]);
        return as;
    }

    public void dumpLog(StringBuffer stringbuffer)
    {
        for(int i = 0; i < jfdHeader.length; i++)
            if(dirt.containsKey(jfdHeader[i]))
                stringbuffer.append(jfdHeader[i] + ": " + dirt.getProperty(jfdHeader[i])).append("\n");

        stringbuffer.append("\n");
        for(int j = 0; j < stimLog.size(); j++)
        {
            stringbuffer.append("> " + (String)stimLog.elementAt(j)).append("\n");
            stringbuffer.append("< " + (String)respLog.elementAt(j)).append("\n");
        }

        stringbuffer.append("\n");
    }

    public Stack stimLog;
    public Stack respLog;
    public RuleStack used;
    public RuleStack next;
    public Properties dirt;
    public Vector url;
    public String query;
    public Vector expect;
    public Hashtable actsHash;
    protected static String logHeader[] = {
        "handle", "hostname", "referral", "name", "firm", "address", "city", "province", "country", "postalcode", 
        "tel", "fax", "email", "language", "timestamp", "hits", "user-agent", "accept-language"
    };
    protected static String jfdHeader[] = {
        "nickname", "haveyou", "doyou", "areyou", "tellme"
    };

}
