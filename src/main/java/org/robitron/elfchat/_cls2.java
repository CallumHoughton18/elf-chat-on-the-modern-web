package org.robitron.elfchat;// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3)


final class _cls2
{

    static final int _mth013F(char ac[], int i)
    {
        return ac[i + 1];
    }

    static final char _mth013E(char ac[], int i)
    {
        return ac[i + 2];
    }

    static final char _mth013D(char ac[], int i)
    {
        return ac[i + 3];
    }

    static final int _mth013C(int i)
    {
        return i + 2;
    }

    static final boolean _mth013B(char c, char ac[], int i)
    {
        while(i < ac.length) 
            if(c == ac[i++])
                return true;
        return false;
    }

    static final int _mth013A(int i)
    {
        return i + 2;
    }

    static final int _mth0139(int i)
    {
        return i - 2;
    }

    static final int _mth0138(char ac[], int i)
    {
        if(ac == null)
            return -1;
        char c = ac[i + 1];
        if(c == 0)
            return -1;
        if(ac[i] == '\r')
            return i - c;
        else
            return i + c;
    }

    static final boolean _mth0137(char c)
    {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9' || c == '_';
    }

    _cls2()
    {
    }

    static final char _fld0136 = 0;
    static final char _fld0135 = 1;
    static final char _fld0134 = 2;
    static final char _fld0133 = 3;
    static final char _fld0132 = 4;
    static final char _fld0131 = 5;
    static final char _fld0130 = 6;
    static final char _fld012F = 7;
    static final char _fld012E = 8;
    static final char _fld012D = 9;
    static final char _fld012C = 10;
    static final char _fld012B = 11;
    static final char _fld012A = 12;
    static final char _fld0129 = 13;
    static final char _fld0128 = 14;
    static final char _fld0127 = 15;
    static final char _fld0126 = 16;
    static final char _fld0125 = 17;
    static final char _fld0124 = 18;
    static final char _fld0123 = 19;
    static final char _fld0122 = 20;
    static final char _fld0121 = 21;
    static final char _fld0120 = 22;
    static final char _fld011F = 23;
    static final char _fld011E = 24;
    static final char _fld011D = 25;
    static final char _fld011C = 26;
    static final char _fld011B = 27;
    static final char _fld011A = 28;
    static final char _fld0119 = 29;
    static final char _fld0118 = 30;
    static final char _fld0117 = 31;
    static final char _fld0116 = 32;
    static final char _fld0115 = 33;
    static final char _fld0114 = 34;
    static final int _fld0113[] = {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 
        0, 0, 0, 0, 0
    };
    static final char _fld0112[] = {
        '\0', '\001', '\001', '\001', '\004', '\004', '\004', '\007', '\007', '\t', 
        '\n', '\n', '\f', '\r', '\016', '\017', '\020', '\021', '\022', '\023', 
        '\024', '\025', '\026', '\027', '\030', '\031', '\032', '\033', '\034', '\035', 
        '\001', '\f', '\f', '\0', '"'
    };
    static final char _fld0111[] = {
        '\f', '\r', '\020', '\021', '\n', '\013', '\032', '"'
    };
    static final char _fld0110[] = {
        '\007', '\b', '\t', '\022', '\023', '\026', '\027', '\030', '\031'
    };
    static final int _fld010F = -1;
    static final char _fld010E = 0;

}
