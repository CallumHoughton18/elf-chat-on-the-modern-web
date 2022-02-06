package org.robitron.elfchat;// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3)


public interface MatchResult
{

    public abstract int length();

    public abstract int groups();

    public abstract String group(int i);

    public abstract int begin(int i);

    public abstract int end(int i);

    public abstract int beginOffset(int i);

    public abstract int endOffset(int i);

    public abstract String toString();
}
