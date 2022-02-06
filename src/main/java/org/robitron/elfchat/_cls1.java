package org.robitron.elfchat;// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3)


final class _cls1
{

    _cls1(char ac[], int i)
    {
        _fld0101 = ac;
        _fld0102 = i;
    }

    _cls1(char ac[])
    {
        this(ac, 0);
    }

    char _mth010D()
    {
        int i = _fld0102;
        if(i < _fld0101.length && i >= 0)
            return _fld0101[i];
        else
            return '\uFFFF';
    }

    char _mth010D(int i)
    {
        if(i < _fld0101.length && i >= 0)
            return _fld0101[i];
        else
            return '\uFFFF';
    }

    char _mth010C(int i)
    {
        int j = _fld0102 + i;
        if(j < _fld0101.length && j >= 0)
            return _fld0101[j];
        else
            return '\uFFFF';
    }

    int _mth010B()
    {
        return _fld0101.length;
    }

    int _mth010A()
    {
        return _fld0102;
    }

    void _mth0109(int i)
    {
        _fld0102 = i;
    }

    boolean _mth0108()
    {
        return _fld0102 >= _fld0101.length;
    }

    char _mth0107(int i)
    {
        _fld0102 += i;
        if(_fld0102 >= _fld0101.length)
        {
            _fld0102 = _fld0101.length;
            return '\uFFFF';
        } else
        {
            return _fld0101[_fld0102];
        }
    }

    char _mth0107()
    {
        return _mth0107(1);
    }

    char _mth0106(int i)
    {
        _fld0102 -= i;
        if(_fld0102 < 0)
            _fld0102 = 0;
        return _fld0101[_fld0102];
    }

    char _mth0106()
    {
        return _mth0106(1);
    }

    char _mth0105()
    {
        int i = _fld0102;
        char c = i >= _fld0101.length || i < 0 ? '\uFFFF' : _fld0101[i];
        _mth0107(1);
        return c;
    }

    char _mth0104()
    {
        int i = _fld0102;
        char c = i >= _fld0101.length || i < 0 ? '\uFFFF' : _fld0101[i];
        _mth0106(1);
        return c;
    }

    String toString(int i)
    {
        return new String(_fld0101, i, _fld0101.length - i);
    }

    public String toString()
    {
        return new String(_fld0101, 0, _fld0101.length);
    }

    static final char _fld0103 = 65535;
    private int _fld0102;
    char _fld0101[];
}
