package org.robitron.elfchat;// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3)


// Referenced classes of package com.oroinc.text.regex:
//            MalformedPatternException, Perl5Pattern, _cls1, PatternCompiler, 
//            _cls2, Pattern

public final class Perl5Compiler
    implements PatternCompiler
{

    static boolean _mth016D(char c)
    {
        return c == '*' || c == '+' || c == '?';
    }

    static boolean _mth016C(char ac[], int i)
    {
        if(i < ac.length && i >= 0)
            return ac[i] == '*' || ac[i] == '+' || ac[i] == '?' || ac[i] == '{' && _mth016B(ac, i);
        else
            return false;
    }

    static boolean _mth016B(char ac[], int i)
    {
        if(ac[i] != '{')
            return false;
        if(++i >= ac.length || !Character.isDigit(ac[i]))
            return false;
        for(; i < ac.length && Character.isDigit(ac[i]); i++);
        if(i < ac.length && ac[i] == ',')
            i++;
        for(; i < ac.length && Character.isDigit(ac[i]); i++);
        return i < ac.length && ac[i] == '}';
    }

    static int _mth016A(char ac[], int i, int j, int ai[])
    {
        int k = 0;
        int l;
        for(ai[0] = 0; i < ac.length && j-- > 0 && (l = "0123456789abcdef0123456789ABCDEFx".indexOf(ac[i])) != -1; ai[0]++)
        {
            k <<= 4;
            k |= l & 0xf;
            i++;
        }

        return k;
    }

    static int _mth0169(char ac[], int i, int j, int ai[])
    {
        int k = 0;
        for(ai[0] = 0; i < ac.length && j > 0 && ac[i] >= '0' && ac[i] <= '7'; ai[0]++)
        {
            k <<= 3;
            k |= ac[i] - 48;
            j--;
            i++;
        }

        return k;
    }

    static void _mth0168(char ac[], char c)
    {
        switch(c)
        {
        case 105: // 'i'
            ac[0] |= '\001';
            return;

        case 103: // 'g'
            ac[0] |= '\002';
            return;

        case 111: // 'o'
            ac[0] |= '\004';
            return;

        case 109: // 'm'
            ac[0] |= '\b';
            return;

        case 115: // 's'
            ac[0] |= '\020';
            return;

        case 120: // 'x'
            ac[0] |= ' ';
            return;
        }
    }

    void _mth0167(char c)
    {
        if(_fld0147 != null)
            _fld0147[_fld0149] = c;
        _fld0149++;
    }

    int _mth0166(char c)
    {
        int i = _fld0149;
        if(_fld0147 == null)
        {
            _fld0149 += 2;
        } else
        {
            _fld0147[_fld0149++] = c;
            _fld0147[_fld0149++] = '\0';
        }
        return i;
    }

    int _mth0165(char c, char c1)
    {
        int i = _fld0149;
        if(_fld0147 == null)
        {
            _fld0149 += 3;
        } else
        {
            _fld0147[_fld0149++] = c;
            _fld0147[_fld0149++] = '\0';
            _fld0147[_fld0149++] = c1;
        }
        return i;
    }

    void _mth0164(char c, int i)
    {
        int l = _cls2._fld0112[c] != '\n' ? 0 : 2;
        if(_fld0147 == null)
        {
            _fld0149 += 2 + l;
            return;
        }
        int j = _fld0149;
        _fld0149 += 2 + l;
        int k = _fld0149;
        while(j > i) 
        {
            j--;
            k--;
            _fld0147[k] = _fld0147[j];
        }
        _fld0147[i++] = c;
        for(_fld0147[i++] = '\0'; l-- > 0; _fld0147[i++] = '\0');
    }

    void _mth0163(int i, int j)
    {
        if(_fld0147 == null || i == -1)
            return;
        int k = i;
        do
        {
            int l = _cls2._mth0138(_fld0147, k);
            if(l == -1)
                break;
            k = l;
        } while(true);
        int i1;
        if(_fld0147[k] == '\r')
            i1 = k - j;
        else
            i1 = j - k;
        _fld0147[k + 1] = (char)i1;
    }

    void _mth0162(int i, int j)
    {
        if(_fld0147 == null || i == -1 || _cls2._fld0112[_fld0147[i]] != '\f')
        {
            return;
        } else
        {
            _mth0163(_cls2._mth013A(i), j);
            return;
        }
    }

    char _mth0161()
    {
        char c = _fld014D._mth0105();
        do
        {
            char c1 = _fld014D._mth010D();
            if(c1 == '(' && _fld014D._mth010C(1) == '?' && _fld014D._mth010C(2) == '#')
            {
                for(; c1 != '\uFFFF' && c1 != ')'; c1 = _fld014D._mth0107());
                _fld014D._mth0107();
                continue;
            }
            if((_fld014B[0] & 0x20) == 0)
                break;
            if(Character.isSpace(c1))
            {
                _fld014D._mth0107();
                continue;
            }
            if(c1 != '#')
                break;
            for(; c1 != '\uFFFF' && c1 != '\n'; c1 = _fld014D._mth0107());
            _fld014D._mth0107();
        } while(true);
        return c;
    }

    int _mth0160(int ai[])
        throws MalformedPatternException
    {
        int l = 0;
        ai[0] = 0;
        int j = _mth0166('\f');
        int i = -1;
        if(_fld014D._mth010A() == 0)
        {
            _fld014D._mth0109(-1);
            _mth0161();
        } else
        {
            _fld014D._mth0106();
            _mth0161();
        }
        for(char c = _fld014D._mth010D(); c != '\uFFFF' && c != '|' && c != ')';)
        {
            l &= -9;
            int k = _mth015C(ai);
            if(k == -1)
            {
                if((l & 8) != 0)
                    c = _fld014D._mth010D();
                else
                    return -1;
            } else
            {
                ai[0] |= l & 1;
                if(i == -1)
                {
                    ai[0] |= l & 4;
                } else
                {
                    _fld0148++;
                    _mth0163(i, k);
                }
                i = k;
                c = _fld014D._mth010D();
            }
        }

        if(i == -1)
            _mth0166('\017');
        return j;
    }

    int _mth015F(int ai[])
        throws MalformedPatternException
    {
        int ai1[] = {
            0
        };
        ai[0] = 0;
        boolean flag = false;
        int i = -1;
label0:
        do
        {
            char c = _fld014D._mth010D();
            switch(c)
            {
            default:
                break;

            case 94: // '^'
                _mth0161();
                if((_fld014B[0] & 8) != 0)
                    i = _mth0166('\002');
                else
                if((_fld014B[0] & 0x10) != 0)
                    i = _mth0166('\003');
                else
                    i = _mth0166('\001');
                break label0;

            case 36: // '$'
                _mth0161();
                if((_fld014B[0] & 8) != 0)
                    i = _mth0166('\005');
                else
                if((_fld014B[0] & 0x10) != 0)
                    i = _mth0166('\006');
                else
                    i = _mth0166('\004');
                break label0;

            case 46: // '.'
                _mth0161();
                if((_fld014B[0] & 0x10) != 0)
                    i = _mth0166('\b');
                else
                    i = _mth0166('\007');
                _fld0148++;
                ai[0] |= 3;
                break label0;

            case 91: // '['
                _fld014D._mth0107();
                i = _mth015D();
                ai[0] |= 3;
                break label0;

            case 40: // '('
                _mth0161();
                i = _mth015B(true, ai1);
                if(i == -1)
                {
                    if((ai1[0] & 8) == 0)
                        return -1;
                    continue;
                }
                ai[0] |= ai1[0] & 5;
                break label0;

            case 41: // ')'
            case 124: // '|'
                if((ai1[0] & 8) != 0)
                {
                    ai[0] |= 8;
                    return -1;
                } else
                {
                    throw new MalformedPatternException("Error in expression at " + _fld014D.toString(_fld014D._mth010A()));
                }

            case 42: // '*'
            case 43: // '+'
            case 63: // '?'
                throw new MalformedPatternException("?+* follows nothing in expression");

            case 92: // '\\'
                char c1 = _fld014D._mth0107();
                switch(c1)
                {
                case 65: // 'A'
                    i = _mth0166('\003');
                    ai[0] |= 2;
                    _mth0161();
                    break;

                case 71: // 'G'
                    i = _mth0166('\036');
                    ai[0] |= 2;
                    _mth0161();
                    break;

                case 90: // 'Z'
                    i = _mth0166('\006');
                    ai[0] |= 2;
                    _mth0161();
                    break;

                case 119: // 'w'
                    i = _mth0166('\022');
                    ai[0] |= 3;
                    _mth0161();
                    break;

                case 87: // 'W'
                    i = _mth0166('\023');
                    ai[0] |= 3;
                    _mth0161();
                    break;

                case 98: // 'b'
                    i = _mth0166('\024');
                    ai[0] |= 2;
                    _mth0161();
                    break;

                case 66: // 'B'
                    i = _mth0166('\025');
                    ai[0] |= 2;
                    _mth0161();
                    break;

                case 115: // 's'
                    i = _mth0166('\026');
                    ai[0] |= 3;
                    _mth0161();
                    break;

                case 83: // 'S'
                    i = _mth0166('\027');
                    ai[0] |= 3;
                    _mth0161();
                    break;

                case 100: // 'd'
                    i = _mth0166('\030');
                    ai[0] |= 3;
                    _mth0161();
                    break;

                case 68: // 'D'
                    i = _mth0166('\031');
                    ai[0] |= 3;
                    _mth0161();
                    break;

                case 48: // '0'
                case 97: // 'a'
                case 99: // 'c'
                case 101: // 'e'
                case 102: // 'f'
                case 110: // 'n'
                case 114: // 'r'
                case 116: // 't'
                case 120: // 'x'
                    flag = true;
                    break;

                case 49: // '1'
                case 50: // '2'
                case 51: // '3'
                case 52: // '4'
                case 53: // '5'
                case 54: // '6'
                case 55: // '7'
                case 56: // '8'
                case 57: // '9'
                    StringBuffer stringbuffer = new StringBuffer(10);
                    int j = 0;
                    for(char c2 = _fld014D._mth010C(j); Character.isDigit(c2); c2 = _fld014D._mth010C(j))
                    {
                        stringbuffer.append(c2);
                        j++;
                    }

                    try
                    {
                        j = Integer.parseInt(stringbuffer.toString());
                    }
                    catch(NumberFormatException numberformatexception)
                    {
                        throw new MalformedPatternException("Unexpected number format exception.  Please report this bug.NumberFormatException message: " + numberformatexception.getMessage());
                    }
                    if(j > 9 && j >= _fld014A)
                    {
                        flag = true;
                    } else
                    {
                        _fld014C = true;
                        i = _mth0165('\032', (char)j);
                        ai[0] |= 1;
                        for(char c3 = _fld014D._mth010D(); Character.isDigit(c3); c3 = _fld014D._mth0107());
                        _fld014D._mth0106();
                        _mth0161();
                    }
                    break;

                case 0: // '\0'
                case 65535: 
                    if(_fld014D._mth0108())
                        throw new MalformedPatternException("Trailing \\ in expression.");
                    // fall through

                default:
                    flag = true;
                    break;
                }
                break label0;

            case 35: // '#'
                if((_fld014B[0] & 0x20) == 0)
                    break;
                for(; !_fld014D._mth0108() && _fld014D._mth010D() != '\n'; _fld014D._mth0107());
                if(!_fld014D._mth0108())
                    continue;
                break;
            }
            _fld014D._mth0107();
            flag = true;
            break;
        } while(true);
        if(flag)
        {
            i = _mth0166('\016');
            if(_fld0147 != null)
                _fld0147[_fld0149] = '\uFFFF';
            _fld0149++;
            int k = 0;
            int l = _fld014D._mth010A() - 1;
label1:
            for(int i1 = _fld014D._mth010B(); k < 127 && l < i1; k++)
            {
                int j1 = l;
                char c4 = _fld014D._mth010D(l);
                char c8;
                switch(c4)
                {
                case 36: // '$'
                case 40: // '('
                case 41: // ')'
                case 46: // '.'
                case 91: // '['
                case 94: // '^'
                case 124: // '|'
                    break label1;

                case 92: // '\\'
                    char c5 = _fld014D._mth010D(++l);
                    switch(c5)
                    {
                    case 65: // 'A'
                    case 66: // 'B'
                    case 68: // 'D'
                    case 71: // 'G'
                    case 83: // 'S'
                    case 87: // 'W'
                    case 90: // 'Z'
                    case 98: // 'b'
                    case 100: // 'd'
                    case 115: // 's'
                    case 119: // 'w'
                        l--;
                        break label1;

                    case 110: // 'n'
                        c8 = '\n';
                        l++;
                        break;

                    case 114: // 'r'
                        c8 = '\r';
                        l++;
                        break;

                    case 116: // 't'
                        c8 = '\t';
                        l++;
                        break;

                    case 102: // 'f'
                        c8 = '\f';
                        l++;
                        break;

                    case 101: // 'e'
                        c8 = '\033';
                        l++;
                        break;

                    case 97: // 'a'
                        c8 = '\007';
                        l++;
                        break;

                    case 120: // 'x'
                        int ai2[] = new int[1];
                        c8 = (char)_mth016A(_fld014D._fld0101, ++l, 2, ai2);
                        l += ai2[0];
                        break;

                    case 99: // 'c'
                        l++;
                        c8 = _fld014D._mth010D(l++);
                        if(Character.isLowerCase(c8))
                            c8 = Character.toUpperCase(c8);
                        c8 ^= '@';
                        break;

                    case 48: // '0'
                    case 49: // '1'
                    case 50: // '2'
                    case 51: // '3'
                    case 52: // '4'
                    case 53: // '5'
                    case 54: // '6'
                    case 55: // '7'
                    case 56: // '8'
                    case 57: // '9'
                        boolean flag1 = false;
                        char c6 = _fld014D._mth010D(l);
                        if(c6 == '0')
                            flag1 = true;
                        c6 = _fld014D._mth010D(l + 1);
                        if(Character.isDigit(c6))
                        {
                            StringBuffer stringbuffer1 = new StringBuffer(10);
                            int k1 = l;
                            for(char c7 = _fld014D._mth010D(k1); Character.isDigit(c7); c7 = _fld014D._mth010D(k1))
                            {
                                stringbuffer1.append(c7);
                                k1++;
                            }

                            try
                            {
                                k1 = Integer.parseInt(stringbuffer1.toString());
                            }
                            catch(NumberFormatException numberformatexception1)
                            {
                                throw new MalformedPatternException("Unexpected number format exception.  Please report this bug.NumberFormatException message: " + numberformatexception1.getMessage());
                            }
                            flag1 = k1 >= _fld014A;
                        }
                        if(flag1)
                        {
                            int ai3[] = new int[1];
                            c8 = (char)_mth0169(_fld014D._fld0101, l, 3, ai3);
                            l += ai3[0];
                        } else
                        {
                            l--;
                            break label1;
                        }
                        break;

                    case 0: // '\0'
                    case 65535: 
                        if(l >= i1)
                            throw new MalformedPatternException("Trailing \\ in expression.");
                        // fall through

                    default:
                        c8 = _fld014D._mth010D(l++);
                        break;
                    }
                    break;

                case 35: // '#'
                    if((_fld014B[0] & 0x20) != 0)
                        for(; l < i1 && _fld014D._mth010D(l) != '\n'; l++);
                    // fall through

                case 9: // '\t'
                case 10: // '\n'
                case 11: // '\013'
                case 12: // '\f'
                case 13: // '\r'
                case 32: // ' '
                    if((_fld014B[0] & 0x20) != 0)
                    {
                        l++;
                        k--;
                        continue;
                    }
                    // fall through

                default:
                    c8 = _fld014D._mth010D(l++);
                    break;
                }
                if((_fld014B[0] & '\001') != 0 && Character.isUpperCase(c8))
                    c8 = Character.toLowerCase(c8);
                if(l < i1 && _mth016C(_fld014D._fld0101, l))
                {
                    if(k > 0)
                    {
                        l = j1;
                    } else
                    {
                        k++;
                        if(_fld0147 != null)
                            _fld0147[_fld0149] = c8;
                        _fld0149++;
                    }
                    break;
                }
                if(_fld0147 != null)
                    _fld0147[_fld0149] = c8;
                _fld0149++;
            }

            _fld014D._mth0109(l - 1);
            _mth0161();
            if(k < 0)
                throw new MalformedPatternException("Unexpected compilation failure.  Please report this bug!");
            if(k > 0)
                ai[0] |= 1;
            if(k == 1)
                ai[0] |= 2;
            if(_fld0147 != null)
                _fld0147[_cls2._mth013C(i)] = (char)k;
            if(_fld0147 != null)
                _fld0147[_fld0149] = '\uFFFF';
            _fld0149++;
        }
        return i;
    }

    void _mth015E(char ac[], int i, char c, char c1)
    {
        if(_fld0147 == null || c1 >= '\u0100')
            return;
        c1 &= '\uFFFF';
        if(c == 0)
        {
            ac[i + (c1 >> 4)] |= 1 << (c1 & 0xf);
            return;
        } else
        {
            ac[i + (c1 >> 4)] &= ~(1 << (c1 & 0xf));
            return;
        }
    }

    int _mth015D()
        throws MalformedPatternException
    {
        boolean flag = false;
        char c2 = '\uFFFF';
        int ai[] = {
            0
        };
        int j = _mth0166('\t');
        int i;
        if(_fld014D._mth010D() == '^')
        {
            _fld0148++;
            _fld014D._mth0107();
            i = 0;
        } else
        {
            i = 65535;
        }
        int l = _fld0149;
        for(char c = '\0'; c < 16; c++)
        {
            if(_fld0147 != null)
                _fld0147[_fld0149] = (char) i;
            _fld0149++;
        }

        char c1 = _fld014D._mth010D();
        int k;
        if(c1 == ']' || c1 == '-')
            k = 0;
        else
            k = 1;
        while(!_fld014D._mth0108() && (c1 = _fld014D._mth010D()) != ']' || k++ == 0) 
        {
            _fld014D._mth0107();
            if(c1 == '\\')
            {
                c1 = _fld014D._mth0105();
                switch(c1)
                {
                default:
                    break;

                case 119: // 'w'
                    for(c1 = '\0'; c1 < '\u0100'; c1++)
                        if(_cls2._mth0137(c1))
                            _mth015E(_fld0147, l, (char) i, c1);

                    c2 = '\uFFFF';
                    continue;

                case 87: // 'W'
                    for(c1 = '\0'; c1 < '\u0100'; c1++)
                        if(!_cls2._mth0137(c1))
                            _mth015E(_fld0147, l, (char) i, c1);

                    c2 = '\uFFFF';
                    continue;

                case 115: // 's'
                    for(c1 = '\0'; c1 < '\u0100'; c1++)
                        if(Character.isSpace(c1))
                            _mth015E(_fld0147, l, (char) i, c1);

                    c2 = '\uFFFF';
                    continue;

                case 83: // 'S'
                    for(c1 = '\0'; c1 < '\u0100'; c1++)
                        if(!Character.isSpace(c1))
                            _mth015E(_fld0147, l, (char) i, c1);

                    c2 = '\uFFFF';
                    continue;

                case 100: // 'd'
                    for(c1 = '0'; c1 <= '9'; c1++)
                        _mth015E(_fld0147, l, (char) i, c1);

                    c2 = '\uFFFF';
                    continue;

                case 68: // 'D'
                    for(c1 = '\0'; c1 < '0'; c1++)
                        _mth015E(_fld0147, l, (char) i, c1);

                    for(c1 = ':'; c1 < '\u0100'; c1++)
                        _mth015E(_fld0147, l, (char) i, c1);

                    c2 = '\uFFFF';
                    continue;

                case 110: // 'n'
                    c1 = '\n';
                    break;

                case 114: // 'r'
                    c1 = '\r';
                    break;

                case 116: // 't'
                    c1 = '\t';
                    break;

                case 98: // 'b'
                    c1 = '\b';
                    break;

                case 101: // 'e'
                    c1 = '\033';
                    break;

                case 97: // 'a'
                    c1 = '\007';
                    break;

                case 120: // 'x'
                    c1 = (char)_mth016A(_fld014D._fld0101, _fld014D._mth010A(), 2, ai);
                    _fld014D._mth0107(ai[0]);
                    break;

                case 99: // 'c'
                    c1 = _fld014D._mth0105();
                    if(Character.isLowerCase(c1))
                        c1 = Character.toUpperCase(c1);
                    c1 ^= '@';
                    break;

                case 48: // '0'
                case 49: // '1'
                case 50: // '2'
                case 51: // '3'
                case 52: // '4'
                case 53: // '5'
                case 54: // '6'
                case 55: // '7'
                case 56: // '8'
                case 57: // '9'
                    c1 = (char)_mth0169(_fld014D._fld0101, _fld014D._mth010A() - 1, 3, ai);
                    _fld014D._mth0107(ai[0] - 1);
                    break;
                }
            }
            if(flag)
            {
                if(c2 > c1)
                    throw new MalformedPatternException("Invalid [] range in expression.");
                flag = false;
            } else
            {
                c2 = c1;
                if(_fld014D._mth010D() == '-' && _fld014D._mth010A() + 1 < _fld014D._mth010B() && _fld014D._mth010C(1) != ']')
                {
                    _fld014D._mth0107();
                    flag = true;
                    continue;
                }
            }
            for(; c2 <= c1; c2++)
            {
                _mth015E(_fld0147, l, (char) i, c2);
                if((_fld014B[0] & '\001') != 0 && Character.isUpperCase(c2))
                    _mth015E(_fld0147, l, (char) i, Character.toLowerCase(c2));
            }

            c2 = c1;
        }
        if(_fld014D._mth010D() != ']')
        {
            throw new MalformedPatternException("Unmatched [] in expression.");
        } else
        {
            _mth0161();
            return j;
        }
    }

    int _mth015C(int ai[])
        throws MalformedPatternException
    {
        boolean flag = false;
        boolean flag1 = false;
        int ai1[] = {
            0
        };
        int k = 0;
        int l = 65535;
        int i = _mth015F(ai1);
        if(i == -1)
        {
            if((ai1[0] & 8) != 0)
                ai[0] |= 8;
            return -1;
        }
        char c = _fld014D._mth010D();
        if(c == '(' && _fld014D._mth010C(1) == '?' && _fld014D._mth010C(2) == '#')
        {
            for(; c != '\uFFFF' && c != ')'; c = _fld014D._mth0107());
            if(c != '\uFFFF')
            {
                _mth0161();
                c = _fld014D._mth010D();
            }
        }
        if(c == '{' && _mth016B(_fld014D._fld0101, _fld014D._mth010A()))
        {
            int j = _fld014D._mth010A() + 1;
            int i1;
            int j1 = i1 = _fld014D._mth010B();
            char c1;
            for(c1 = _fld014D._mth010D(j); Character.isDigit(c1) || c1 == ','; c1 = _fld014D._mth010D(j))
            {
                if(c1 == ',')
                {
                    if(j1 != i1)
                        break;
                    j1 = j;
                }
                j++;
            }

            if(c1 == '}')
            {
                StringBuffer stringbuffer = new StringBuffer(10);
                if(j1 == i1)
                    j1 = j;
                _fld014D._mth0107();
                int k1 = _fld014D._mth010A();
                for(char c2 = _fld014D._mth010D(k1); Character.isDigit(c2); c2 = _fld014D._mth010D(k1))
                {
                    stringbuffer.append(c2);
                    k1++;
                }

                try
                {
                    k = Integer.parseInt(stringbuffer.toString());
                }
                catch(NumberFormatException numberformatexception)
                {
                    throw new MalformedPatternException("Unexpected number format exception.  Please report this bug.NumberFormatException message: " + numberformatexception.getMessage());
                }
                char c3 = _fld014D._mth010D(j1);
                if(c3 == ',')
                    j1++;
                else
                    j1 = _fld014D._mth010A();
                k1 = j1;
                stringbuffer = new StringBuffer(10);
                for(char c4 = _fld014D._mth010D(k1); Character.isDigit(c4); c4 = _fld014D._mth010D(k1))
                {
                    stringbuffer.append(c4);
                    k1++;
                }

                try
                {
                    if(k1 != j1)
                        l = Integer.parseInt(stringbuffer.toString());
                }
                catch(NumberFormatException numberformatexception1)
                {
                    throw new MalformedPatternException("Unexpected number format exception.  Please report this bug.NumberFormatException message: " + numberformatexception1.getMessage());
                }
                if(l == 0 && _fld014D._mth010D(j1) != '0')
                    l = 65535;
                _fld014D._mth0109(j);
                _mth0161();
                flag = true;
                flag1 = true;
            }
        }
        if(!flag)
        {
            flag1 = false;
            if(c != '*' && c != '+' && c != '?')
            {
                ai[0] = ai1[0];
                return i;
            }
            _mth0161();
            ai[0] = c == '+' ? 1 : 4;
            if(c == '*' && (ai1[0] & 2) != 0)
            {
                _mth0164('\020', i);
                _fld0148 += 4;
            } else
            if(c == '*')
            {
                k = 0;
                flag1 = true;
            } else
            if(c == '+' && (ai1[0] & 2) != 0)
            {
                _mth0164('\021', i);
                _fld0148 += 3;
            } else
            if(c == '+')
            {
                k = 1;
                flag1 = true;
            } else
            if(c == '?')
            {
                k = 0;
                l = 1;
                flag1 = true;
            }
        }
        if(flag1)
        {
            if((ai1[0] & 2) != 0)
            {
                _fld0148 += (2 + _fld0148) / 2;
                _mth0164('\n', i);
            } else
            {
                _fld0148 += 4 + _fld0148;
                _mth0163(i, _mth0166('"'));
                _mth0164('\013', i);
                _mth0163(i, _mth0166('\017'));
            }
            if(k > 0)
                ai[0] = 1;
            if(l != 0 && l < k)
                throw new MalformedPatternException("Invalid interval {" + k + "," + l + "}");
            if(_fld0147 != null)
            {
                _fld0147[i + 2] = (char)k;
                _fld0147[i + 3] = (char)l;
            }
        }
        if(_fld014D._mth010D() == '?')
        {
            _mth0161();
            _mth0164('\035', i);
            _mth0163(i, i + 2);
        }
        if(_mth016C(_fld014D._fld0101, _fld014D._mth010A()))
            throw new MalformedPatternException("Nested repetitions *?+ in expression");
        else
            return i;
    }

    int _mth015B(boolean flag, int ai[])
        throws MalformedPatternException
    {
        int i = -1;
        int j = 0;
        int ai1[] = {
            0
        };
        ai[0] = 1;
        char c3;
        if(flag)
        {
            c3 = '\001';
            if(_fld014D._mth010D() == '?')
            {
                _fld014D._mth0107();
                char c;
                c3 = c = _fld014D._mth0105();
                switch(c)
                {
                case 35: // '#'
                    char c1;
                    for(c1 = _fld014D._mth010D(); c1 != '\uFFFF' && c1 != ')'; c1 = _fld014D._mth0107());
                    if(c1 != ')')
                    {
                        throw new MalformedPatternException("Sequence (?#... not terminated");
                    } else
                    {
                        _mth0161();
                        ai[0] = 8;
                        return -1;
                    }

                default:
                    _fld014D._mth0106();
                    char c2;
                    for(c2 = _fld014D._mth010D(); c2 != '\uFFFF' && "iogmsx".indexOf(c2) != -1; c2 = _fld014D._mth0107())
                        _mth0168(_fld014B, c2);

                    if(c2 != ')')
                    {
                        throw new MalformedPatternException("Sequence (?" + c2 + "...) not recognized");
                    } else
                    {
                        _mth0161();
                        ai[0] = 8;
                        return -1;
                    }

                case 33: // '!'
                case 58: // ':'
                case 61: // '='
                    break;
                }
            } else
            {
                j = _fld014A;
                _fld014A++;
                i = _mth0165('\033', (char)j);
            }
        } else
        {
            c3 = '\0';
        }
        int k = _mth0160(ai1);
        if(k == -1)
            return -1;
        if(i != -1)
            _mth0163(i, k);
        else
            i = k;
        if((ai1[0] & 1) == 0)
            ai[0] &= -2;
        for(ai[0] |= ai1[0] & 4; _fld014D._mth010D() == '|'; ai[0] |= ai1[0] & 4)
        {
            _mth0161();
            int l = _mth0160(ai1);
            if(l == -1)
                return -1;
            _mth0163(i, l);
            if((ai1[0] & 1) == 0)
                ai[0] &= -2;
        }

        int j1;
        switch(c3)
        {
        case 58: // ':'
            j1 = _mth0166('\017');
            break;

        case 1: // '\001'
            j1 = _mth0165('\034', (char)j);
            break;

        case 33: // '!'
        case 61: // '='
            j1 = _mth0166('!');
            ai[0] &= -2;
            break;

        case 0: // '\0'
        default:
            j1 = _mth0166('\0');
            break;
        }
        _mth0163(i, j1);
        for(int i1 = i; i1 != -1; i1 = _cls2._mth0138(_fld0147, i1))
            _mth0162(i1, j1);

        if(c3 == '=')
        {
            _mth0164('\037', i);
            _mth0163(i, _mth0166('\017'));
        } else
        if(c3 == '!')
        {
            _mth0164(' ', i);
            _mth0163(i, _mth0166('\017'));
        }
        if(c3 != 0 && (_fld014D._mth0108() || _mth0161() != ')'))
            throw new MalformedPatternException("Unmatched parentheses.");
        if(c3 == 0 && !_fld014D._mth0108())
        {
            if(_fld014D._mth010D() == ')')
                throw new MalformedPatternException("Unmatched parentheses.");
            else
                throw new MalformedPatternException("Unreached characters at end of expression.  Please report this bug!");
        } else
        {
            return i;
        }
    }

    public Pattern compile(char ac[], int i)
        throws MalformedPatternException
    {
        int ai[] = {
            0
        };
        boolean flag = false;
        boolean flag1 = false;
        int j1 = 0;
        _fld014D = new _cls1(ac);
        int j = i & 1;
        _fld014B[0] = (char)i;
        _fld014C = false;
        _fld014A = 1;
        _fld0149 = 0;
        _fld0148 = 0;
        _fld0147 = null;
        if(_fld0147 != null)
            _fld0147[_fld0149] = '\0';
        _fld0149++;
        if(_mth015B(false, ai) == -1)
            throw new MalformedPatternException("Unknown compilation error.");
        if(_fld0149 >= 65534)
            throw new MalformedPatternException("Expression is too large.");
        _fld0147 = new char[_fld0149];
        Perl5Pattern perl5pattern = new Perl5Pattern();
        perl5pattern._fld0193 = _fld0147;
        perl5pattern._fld019C = new String(ac);
        _fld014D._mth0109(0);
        _fld014A = 1;
        _fld0149 = 0;
        _fld0148 = 0;
        if(_fld0147 != null)
            _fld0147[_fld0149] = '\0';
        _fld0149++;
        if(_mth015B(false, ai) == -1)
            throw new MalformedPatternException("Unknown compilation error.");
        j = _fld014B[0] & '\001';
        perl5pattern._fld0194 = _fld0148 >= 10;
        perl5pattern._fld0192 = -1;
        perl5pattern._fld0191 = 0;
        perl5pattern._fld0198 = -1;
        perl5pattern._fld019A = null;
        perl5pattern._fld019B = null;
        String s = null;
        String s1 = null;
        int k = 1;
        if(_fld0147[_cls2._mth0138(_fld0147, k)] == 0)
        {
            int l = k = _cls2._mth013A(k);
            for(char c = _fld0147[l]; c == '\033' && (flag = true) || c == '\f' && _fld0147[_cls2._mth0138(_fld0147, l)] != '\f' || c == '\021' || c == '\035' || _cls2._fld0112[c] == '\n' && _cls2._mth013E(_fld0147, l) > 0; c = _fld0147[l])
            {
                if(c == '\021')
                    flag1 = true;
                else
                    l += _cls2._fld0113[c];
                l = _cls2._mth013A(l);
            }

            boolean flag2 = true;
            while(flag2) 
            {
                flag2 = false;
                char c1 = _fld0147[l];
                if(c1 == '\016')
                    s1 = new String(_fld0147, _cls2._mth013C(l + 1), _fld0147[_cls2._mth013C(l)]);
                else
                if(_cls2._mth013B(c1, _cls2._fld0110, 2))
                    perl5pattern._fld0192 = l;
                else
                if(c1 == '\024' || c1 == '\025')
                    perl5pattern._fld0192 = l;
                else
                if(_cls2._fld0112[c1] == '\001')
                {
                    perl5pattern._fld0191 = 1;
                    l = _cls2._mth013A(l);
                    flag2 = true;
                } else
                if(c1 == '\020' && _cls2._fld0112[_fld0147[_cls2._mth013A(l)]] == '\007' && (perl5pattern._fld0191 & 1) != 0)
                {
                    perl5pattern._fld0191 = 5;
                    l = _cls2._mth013A(l);
                    flag2 = true;
                }
            }
            if(flag1 && (!flag || !_fld014C))
                perl5pattern._fld0191 |= 2;
            StringBuffer stringbuffer = new StringBuffer();
            StringBuffer stringbuffer1 = new StringBuffer();
            int i1 = 0;
            j1 = 0;
            int k1 = 0;
            int l1 = 0;
            int i2 = 0;
            char c2;
            while(k > 0 && (c2 = _fld0147[k]) != 0) 
                if(c2 == '\f')
                {
                    if(_fld0147[_cls2._mth0138(_fld0147, k)] == '\f')
                    {
                        k1 = -30000;
                        for(; _fld0147[k] == '\f'; k = _cls2._mth0138(_fld0147, k));
                    } else
                    {
                        k = _cls2._mth013A(k);
                    }
                } else
                if(c2 == ' ')
                {
                    k1 = -30000;
                    k = _cls2._mth0138(_fld0147, k);
                } else
                {
                    if(c2 == '\016')
                    {
                        l = k;
                        int j2;
                        while(_fld0147[j2 = _cls2._mth0138(_fld0147, k)] == '\034') 
                            k = j2;
                        j1 += _fld0147[_cls2._mth013C(l)];
                        j2 = _fld0147[_cls2._mth013C(l)];
                        if(k1 - l1 == i1)
                        {
                            stringbuffer.append(new String(_fld0147, _cls2._mth013C(l) + 1, j2));
                            i1 += j2;
                            k1 += j2;
                            l = _cls2._mth0138(_fld0147, k);
                        } else
                        if(j2 >= i1 + (k1 < 0 ? 0 : 1))
                        {
                            i1 = j2;
                            stringbuffer = new StringBuffer(new String(_fld0147, _cls2._mth013C(l) + 1, j2));
                            l1 = k1;
                            k1 += i1;
                            l = _cls2._mth0138(_fld0147, k);
                        } else
                        {
                            k1 += j2;
                        }
                    } else
                    if(_cls2._mth013B(c2, _cls2._fld0111, 0))
                    {
                        k1 = -30000;
                        i1 = 0;
                        if(stringbuffer.length() > stringbuffer1.length())
                        {
                            stringbuffer1 = stringbuffer;
                            i2 = l1;
                        }
                        stringbuffer = new StringBuffer();
                        if(c2 == '\021' && _cls2._mth013B(_fld0147[_cls2._mth013A(k)], _cls2._fld0110, 0))
                            j1++;
                        else
                        if(_cls2._fld0112[c2] == '\n' && _cls2._mth013B(_fld0147[_cls2._mth013A(k) + 2], _cls2._fld0110, 0))
                            j1 += _cls2._mth013E(_fld0147, k);
                    } else
                    if(_cls2._mth013B(c2, _cls2._fld0110, 0))
                    {
                        k1++;
                        j1++;
                        i1 = 0;
                        if(stringbuffer.length() > stringbuffer1.length())
                        {
                            stringbuffer1 = stringbuffer;
                            i2 = l1;
                        }
                        stringbuffer = new StringBuffer();
                    }
                    k = _cls2._mth0138(_fld0147, k);
                }
            if(stringbuffer.length() + (_cls2._fld0112[_fld0147[l]] != '\004' ? 0 : 1) > stringbuffer1.length())
            {
                stringbuffer1 = stringbuffer;
                i2 = l1;
            } else
            {
                stringbuffer = new StringBuffer();
            }
            if(stringbuffer1.length() > 0 && s1 == null)
            {
                s = stringbuffer1.toString();
                if(i2 < 0)
                    i2 = -1;
                perl5pattern._fld0198 = i2;
            } else
            {
                stringbuffer1 = null;
            }
        }
        perl5pattern._fld0195 = (j & 1) != 0;
        perl5pattern._fld0196 = _fld014A - 1;
        perl5pattern._fld0197 = j1;
        perl5pattern._fld0190 = new int[_fld014A];
        perl5pattern._fld018F = new int[_fld014A];
        if(s != null)
        {
            perl5pattern._fld019B = s.toCharArray();
            perl5pattern._fld0199 = 100;
        }
        if(s1 != null)
            perl5pattern._fld019A = s1.toCharArray();
        return perl5pattern;
    }

    public Pattern compile(char ac[])
        throws MalformedPatternException
    {
        return compile(ac, 0);
    }

    public Pattern compile(String s)
        throws MalformedPatternException
    {
        return compile(s.toCharArray(), 0);
    }

    public Pattern compile(String s, int i)
        throws MalformedPatternException
    {
        return compile(s.toCharArray(), i);
    }

    public Perl5Compiler()
    {
    }

    private static final int _fld015A = 0;
    private static final int _fld0159 = 1;
    private static final int _fld0158 = 2;
    private static final int _fld0157 = 4;
    private static final int _fld0156 = 8;
    private static final char _fld0155 = 1;
    private static final char _fld0154 = 2;
    private static final char _fld0153 = 4;
    private static final char _fld0152 = 8;
    private static final char _fld0151 = 16;
    private static final char _fld0150 = 32;
    private static final String _fld014F = "^$.[()|?+*\\";
    private static final String _fld014E = "0123456789abcdef0123456789ABCDEFx";
    private _cls1 _fld014D;
    private boolean _fld014C;
    private char _fld014B[] = {
        '\0'
    };
    private int _fld014A;
    private int _fld0149;
    private int _fld0148;
    private char _fld0147[];
    public static final int DEFAULT_MASK = 0;
    public static final int CASE_INSENSITIVE_MASK = 1;
    public static final int MULTILINE_MASK = 8;
    public static final int SINGLELINE_MASK = 16;
    public static final int EXTENDED_MASK = 32;
}
