package org.robitron.elfchat;// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

import java.io.IOException;
import java.io.InputStream;

public final class Perl5StreamInput
{

    public Perl5StreamInput(InputStream inputstream, int i)
    {
        _fld01B0 = inputstream;
        _fld01B2 = i;
        _fld01AF = false;
        _fld01AE = false;
        _fld01AD = _fld01AB = _fld01AC = 0;
        _fld01B1 = new byte[i];
        _fld01A9 = _fld01AA = new char[i];
    }

    public Perl5StreamInput(InputStream inputstream)
    {
        this(inputstream, 4096);
    }

    private void _mth01B6(int i, int j, boolean flag)
    {
        j += i;
        if(flag)
        {
            for(; i < j; i++)
            {
                byte byte0 = _fld01B1[i];
                char c;
                _fld01A9[i] = c = (char)(byte0 >= 0 ? byte0 : 256 + byte0);
                _fld01AA[i] = Character.isUpperCase(c) ? Character.toLowerCase(c) : c;
            }

            return;
        }
        for(; i < j; i++)
        {
            byte byte1 = _fld01B1[i];
            _fld01AA[i] = (char)(byte1 >= 0 ? byte1 : 256 + byte1);
        }

    }

    void _mth01B5(int i, boolean flag)
        throws IOException
    {
        if(_fld01AF)
            return;
        if(_fld01AE)
        {
            _fld01AE = false;
            _fld01AF = true;
            return;
        }
        int j = _fld01AD - i;
        if(j < 0)
            throw new IOException("Non-negative offset assertion violation.\nPlease report this error to bugs@oroinc.com\n");
        byte abyte0[] = new byte[j + _fld01B2];
        int k = _fld01B0.read(abyte0, j, _fld01B2);
        if(k <= 0)
        {
            _fld01AF = true;
            if(k == 0)
                throw new IOException("read from input stream returned 0 bytes.");
        } else
        {
            _fld01AC += i;
            for(_fld01AD = j + k; _fld01AD < abyte0.length;)
            {
                int l = _fld01B0.read(abyte0, _fld01AD, abyte0.length - _fld01AD);
                if(l == 0)
                    throw new IOException("read from input stream returned 0 bytes.");
                if(l < 0)
                {
                    _fld01AE = true;
                    break;
                }
                _fld01AD += l;
                k += l;
            }

            System.arraycopy(_fld01B1, i, abyte0, 0, j);
            char ac[] = new char[j + _fld01B2];
            System.arraycopy(_fld01AA, i, ac, 0, j);
            _fld01B1 = abyte0;
            _fld01AA = ac;
            if(flag)
            {
                char ac1[] = new char[j + _fld01B2];
                System.arraycopy(_fld01A9, i, ac1, 0, j);
                _fld01A9 = ac1;
            } else
            {
                _fld01A9 = _fld01AA;
            }
            _mth01B6(j, k, flag);
        }
    }

    boolean _mth01B4(boolean flag)
        throws IOException
    {
        _fld01AC += _fld01AD;
        int i = _fld01AD = _fld01B0.read(_fld01B1);
        if(_fld01AD > 0)
        {
            int j;
            for(; _fld01AD < _fld01B1.length; _fld01AD += j)
            {
                j = _fld01B0.read(_fld01B1, _fld01AD, _fld01B1.length - _fld01AD);
                if(j == 0)
                    throw new IOException("read from input stream returned 0 bytes.");
                if(j < 0)
                    break;
            }

        } else
        {
            _fld01AF = true;
        }
        if(!_fld01AF)
        {
            if(flag)
                _fld01AA = new char[_fld01A9.length];
            _mth01B6(0, _fld01AD, flag);
        }
        return !_fld01AF;
    }

    public boolean endOfStream()
    {
        return _fld01AF;
    }

    private static final int _fld01B3 = 4096;
    private int _fld01B2;
    private byte _fld01B1[];
    private InputStream _fld01B0;
    boolean _fld01AF;
    boolean _fld01AE;
    int _fld01AD;
    int _fld01AC;
    int _fld01AB;
    char _fld01AA[];
    char _fld01A9[];
}
