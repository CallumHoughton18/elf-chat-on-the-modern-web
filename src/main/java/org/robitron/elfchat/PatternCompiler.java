package org.robitron.elfchat;// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3)


// Referenced classes of package com.oroinc.text.regex:
//            MalformedPatternException, Pattern

public interface PatternCompiler
{

    public abstract Pattern compile(String s)
        throws MalformedPatternException;

    public abstract Pattern compile(char ac[])
        throws MalformedPatternException;
}
