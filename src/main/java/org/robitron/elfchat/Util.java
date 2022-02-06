package org.robitron.elfchat;// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

import java.util.Vector;

// Referenced classes of package com.oroinc.text.regex:
//            PatternMatcherInput, PatternMatcher, MatchResult, Pattern

public final class Util
{

    private static String _mth01B9(Vector vector, MatchResult matchresult)
    {
        StringBuffer stringbuffer = new StringBuffer(10);
        _mth01B7(stringbuffer, vector, matchresult);
        return stringbuffer.toString();
    }

    private static Vector _mth01B8(String s)
    {
        Vector vector = new Vector(5);
        StringBuffer stringbuffer = new StringBuffer(5);
        StringBuffer stringbuffer1 = new StringBuffer(10);
        char ac[] = s.toCharArray();
        int i = 0;
        boolean flag = false;
        boolean flag1 = false;
        for(; i < ac.length; i++)
            if(flag && Character.isDigit(ac[i]))
            {
                stringbuffer.append(ac[i]);
                if(stringbuffer1.length() > 0)
                {
                    vector.addElement(stringbuffer1.toString());
                    stringbuffer1.setLength(0);
                }
            } else
            {
                if(flag)
                {
                    try
                    {
                        vector.addElement(new Integer(stringbuffer.toString()));
                        flag1 = true;
                    }
                    catch(NumberFormatException _ex)
                    {
                        vector.addElement(stringbuffer.toString());
                    }
                    stringbuffer.setLength(0);
                    flag = false;
                }
                if(ac[i] == '$' && i + 1 < ac.length && ac[i + 1] != '0' && Character.isDigit(ac[i + 1]))
                    flag = true;
                else
                    stringbuffer1.append(ac[i]);
            }

        if(flag)
            try
            {
                vector.addElement(new Integer(stringbuffer.toString()));
                flag1 = true;
            }
            catch(NumberFormatException _ex)
            {
                vector.addElement(stringbuffer.toString());
            }
        else
        if(stringbuffer1.length() > 0)
            vector.addElement(stringbuffer1.toString());
        if(flag1)
            return vector;
        else
            return null;
    }

    private static void _mth01B7(StringBuffer stringbuffer, Vector vector, MatchResult matchresult)
    {
        int i = vector.size();
        for(int j = 0; j < i; j++)
        {
            Object obj = vector.elementAt(j);
            if(obj instanceof String)
            {
                stringbuffer.append(obj);
            } else
            {
                Integer integer = (Integer)obj;
                int k = integer.intValue();
                if(k > 0 && k < matchresult.groups())
                {
                    String s = matchresult.group(k);
                    if(s != null)
                        stringbuffer.append(s);
                } else
                {
                    stringbuffer.append('$');
                    stringbuffer.append(k);
                }
            }
        }

    }

    private Util()
    {
    }

    public static Vector split(PatternMatcher patternmatcher, Pattern pattern, String s, int i)
    {
        Vector vector = new Vector(20);
        PatternMatcherInput patternmatcherinput = new PatternMatcherInput(s);
        int j;
        MatchResult matchresult;
        for(j = 0; --i != 0 && patternmatcher.contains(patternmatcherinput, pattern); j = matchresult.endOffset(0))
        {
            matchresult = patternmatcher.getMatch();
            vector.addElement(s.substring(j, matchresult.beginOffset(0)));
        }

        vector.addElement(s.substring(j, s.length()));
        return vector;
    }

    public static Vector split(PatternMatcher patternmatcher, Pattern pattern, String s)
    {
        return split(patternmatcher, pattern, s, 0);
    }

    public static String substitute(PatternMatcher patternmatcher, Pattern pattern, String s, String s1, int i, int j)
    {
        Vector vector = null;
        StringBuffer stringbuffer = new StringBuffer(s1.length());
        if(j != -1 && s.indexOf('$') != -1)
            vector = _mth01B8(s);
        PatternMatcherInput patternmatcherinput = new PatternMatcherInput(s1);
        int k;
        MatchResult matchresult;
        for(k = 0; i-- != 0 && patternmatcher.contains(patternmatcherinput, pattern); k = matchresult.endOffset(0))
        {
            matchresult = patternmatcher.getMatch();
            stringbuffer.append(s1.substring(k, matchresult.beginOffset(0)));
            if(vector == null)
                stringbuffer.append(s);
            else
            if(j < 1 || j-- > 1)
            {
                _mth01B7(stringbuffer, vector, matchresult);
            } else
            {
                s = _mth01B9(vector, matchresult);
                stringbuffer.append(s);
                vector = null;
            }
        }

        stringbuffer.append(s1.substring(k, s1.length()));
        return stringbuffer.toString();
    }

    public static String substitute(PatternMatcher patternmatcher, Pattern pattern, String s, String s1, int i)
    {
        return substitute(patternmatcher, pattern, s, s1, i, 0);
    }

    public static String substitute(PatternMatcher patternmatcher, Pattern pattern, String s, String s1)
    {
        return substitute(patternmatcher, pattern, s, s1, 1, 0);
    }

    public static final int SUBSTITUTE_ALL = -1;
    public static final int SPLIT_ALL = 0;
    public static final int INTERPOLATE_ALL = 0;
    public static final int INTERPOLATE_NONE = -1;
}
