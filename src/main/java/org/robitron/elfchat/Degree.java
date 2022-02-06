package org.robitron.elfchat;// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Degree.java

import java.util.Date;

public class Degree
{

    public Degree(Date date)
    {
        value = date.getTime() / 0x3a980L;
    }

    public String toString()
    {
        return String.valueOf(value);
    }

    public static final long TICKS = 0x3a980L;
    protected long value;
}
