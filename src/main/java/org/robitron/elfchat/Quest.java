package org.robitron.elfchat;// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Quest.java

import java.util.Vector;

public class Quest
{

    public Quest()
    {
        query = new Vector(10, 10);
        signal = new Vector(10, 10);
        width = 0;
    }

    public void addName(String s)
    {
        name = s;
    }

    public void addQuery(String s)
    {
        query.addElement(s);
        width++;
    }

    public void addSignal(String s)
    {
        signal.addElement(s);
    }

    public String getQuery(int i)
    {
        return (String)query.elementAt(i);
    }

    public String getSignal(int i)
    {
        return (String)signal.elementAt(i);
    }

    public String name;
    public int width;
    public Vector signal;
    public Vector query;
}
