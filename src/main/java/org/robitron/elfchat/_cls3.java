package org.robitron.elfchat;// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3)


// Referenced classes of package com.oroinc.text.regex:
//            MatchResult

final class _cls3
    implements MatchResult
{

    _cls3(int i)
    {
        _fld0171 = new int[i];
        _fld0170 = new int[i];
    }

    public int length()
    {
        return _fld016F.length();
    }

    public int groups()
    {
        return _fld0171.length;
    }

    public String group(int i)
    {
        if(i < _fld0171.length)
        {
            int j = _fld0171[i];
            int k = _fld0170[i];
            int l = _fld016F.length();
            if(j >= 0 && k >= 0)
            {
                if(j < l && k <= l)
                    return _fld016F.substring(j, k);
                if(j <= k)
                    return new String("");
            }
        }
        return null;
    }

    public int begin(int i)
    {
        if(i < _fld0171.length)
        {
            int j = _fld0171[i];
            int k = _fld0170[i];
            int l = _fld016F.length();
            if(j >= 0 && k >= 0)
                return _fld0171[i];
        }
        return -1;
    }

    public int end(int i)
    {
        if(i < _fld0171.length)
        {
            int j = _fld0171[i];
            int k = _fld0170[i];
            int l = _fld016F.length();
            if(j >= 0 && k >= 0)
                return _fld0170[i];
        }
        return -1;
    }

    public int beginOffset(int i)
    {
        if(i < _fld0171.length)
        {
            int j = _fld0171[i];
            int k = _fld0170[i];
            int l = _fld016F.length();
            if(j >= 0 && k >= 0)
                return _fld0172 + _fld0171[i];
        }
        return -1;
    }

    public int endOffset(int i)
    {
        if(i < _fld0170.length)
        {
            int j = _fld0171[i];
            int k = _fld0170[i];
            int l = _fld016F.length();
            if(j >= 0 && k >= 0)
                return _fld0172 + _fld0170[i];
        }
        return -1;
    }

    public String toString()
    {
        return group(0);
    }

    int _fld0172;
    int _fld0171[];
    int _fld0170[];
    String _fld016F;
}
