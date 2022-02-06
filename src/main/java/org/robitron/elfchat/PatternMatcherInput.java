package org.robitron.elfchat;// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3)


public final class PatternMatcherInput
{

    public PatternMatcherInput(String s, int i, int j)
    {
        setInput(s, i, j);
    }

    public PatternMatcherInput(String s)
    {
        this(s, 0, s.length());
    }

    public PatternMatcherInput(char ac[], int i, int j)
    {
        setInput(ac, i, j);
    }

    public PatternMatcherInput(char ac[])
    {
        this(ac, 0, ac.length);
    }

    public int length()
    {
        return _fld0141 - _fld0142;
    }

    public void setInput(String s, int i, int j)
    {
        _fld0146 = s;
        _fld0145 = null;
        _fld0143 = null;
        _fld0144 = s.toCharArray();
        _fld0140 = _fld0142 = i;
        _fld0141 = _fld0142 + j;
    }

    public void setInput(String s)
    {
        setInput(s, 0, s.length());
    }

    public void setInput(char ac[], int i, int j)
    {
        _fld0146 = null;
        _fld0143 = null;
        _fld0144 = _fld0145 = ac;
        _fld0140 = _fld0142 = i;
        _fld0141 = _fld0142 + j;
    }

    public void setInput(char ac[])
    {
        setInput(ac, 0, ac.length);
    }

    public Object getInput()
    {
        if(_fld0146 == null)
            return _fld0145;
        else
            return _fld0146;
    }

    public boolean endOfInput()
    {
        return _fld0140 >= _fld0141;
    }

    public int getBeginOffset()
    {
        return _fld0142;
    }

    public int getEndOffset()
    {
        return _fld0141;
    }

    public int getCurrentOffset()
    {
        return _fld0140;
    }

    public void setBeginOffset(int i)
    {
        _fld0142 = i;
    }

    public void setEndOffset(int i)
    {
        _fld0141 = i;
    }

    public void setCurrentOffset(int i)
    {
        _fld0140 = i;
    }

    public String toString()
    {
        if(_fld0146 == null)
            return new String(_fld0145);
        else
            return _fld0146;
    }

    String _fld0146;
    char _fld0145[];
    char _fld0144[];
    char _fld0143[];
    int _fld0142;
    int _fld0141;
    int _fld0140;
}
