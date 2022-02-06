package org.robitron.elfchat;// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3)


// Referenced classes of package com.oroinc.text.regex:
//            Pattern, PatternMatcherInput, MatchResult

public interface PatternMatcher
{

    public abstract boolean matches(String s, Pattern pattern);

    public abstract boolean matches(char ac[], Pattern pattern);

    public abstract boolean matches(PatternMatcherInput patternmatcherinput, Pattern pattern);

    public abstract boolean contains(String s, Pattern pattern);

    public abstract boolean contains(char ac[], Pattern pattern);

    public abstract boolean contains(PatternMatcherInput patternmatcherinput, Pattern pattern);

    public abstract MatchResult getMatch();
}
