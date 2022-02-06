package org.robitron.elfchat;// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

import java.io.IOException;
import java.util.Stack;

// Referenced classes of package com.oroinc.text.regex:
//            Perl5Pattern, _cls4, _cls3, PatternMatcher, 
//            Perl5StreamInput, PatternMatcherInput, _cls2, Pattern, 
//            MatchResult

public final class Perl5Matcher
    implements PatternMatcher
{

    static boolean _mth018E(char ac[], int i, char ac1[], int j, int k)
    {
        for(int l = 0; l < k;)
        {
            if(i >= ac.length)
                return false;
            if(j >= ac1.length)
                return false;
            if(ac[i] != ac1[j])
                return false;
            l++;
            i++;
            j++;
        }

        return true;
    }

    static int _mth018D(char ac[], int i, int j, char ac1[])
    {
        if(ac.length == 0)
            return j;
        char c = ac1[0];
        for(; i < j; i++)
        {
            if(c != ac[i])
                continue;
            int l = i;
            int k;
            for(k = 0; i < j && k < ac1.length; i++)
            {
                if(ac1[k] != ac[i])
                    break;
                k++;
            }

            i = l;
            if(k >= ac1.length)
                break;
        }

        return i;
    }

    void _mth018C(int i)
    {
        int j = 3 * (_fld0179 - i);
        int ai[];
        if(j <= 0)
            ai = new int[3];
        else
            ai = new int[j + 3];
        ai[0] = _fld0179;
        ai[1] = _fld0177;
        ai[2] = _fld0178;
        for(int k = _fld0179; k > i;)
        {
            ai[j] = _fld0175[k];
            ai[j + 1] = _fld0176[k];
            ai[j + 2] = k;
            k -= 3;
            j -= 3;
        }

        _fld0174.push(ai);
    }

    void _mth018B()
    {
        int ai[] = (int[])_fld0174.pop();
        _fld0179 = ai[0];
        _fld0177 = ai[1];
        _fld0178 = ai[2];
        for(int i = 3; i < ai.length; i += 3)
        {
            int j = ai[i + 2];
            _fld0176[j] = ai[i + 1];
            if(j <= _fld0177)
                _fld0175[j] = ai[i];
        }

        for(int k = _fld0177 + 1; k < _fld017F; k++)
        {
            if(k > _fld0179)
                _fld0176[k] = -1;
            _fld0175[k] = -1;
        }

    }

    boolean _mth018A(Perl5Pattern perl5pattern, char ac[], int i, int j)
    {
        boolean flag;
label0:
        {
            _cls4 _lcls4 = new _cls4();
            int l = 0;
            int i1 = 0;
            _fld0182 = ac;
            _fld017B = j;
            _lcls4._fld01A7 = 0;
            _lcls4._fld01A0 = null;
            _fld0180 = _lcls4;
            _fld017A = perl5pattern._fld0193;
            _fld0174.setSize(0);
            int k = i;
            if(k == 0)
            {
                _fld0183 = '\n';
            } else
            {
                _fld0183 = ac[k - 1];
                if(!_fld0184 && _fld0183 == '\n')
                    _fld0183 = '\0';
            }
            _fld0176 = perl5pattern._fld0190;
            _fld0175 = perl5pattern._fld018F;
            _fld017F = perl5pattern._fld0196;
            _fld017C = k;
            flag = false;
            char ac1[] = perl5pattern._fld019B;
            if(ac1 != null && ((perl5pattern._fld0191 & 1) == 0 || _fld0184 && perl5pattern._fld0198 >= 0))
            {
                _fld017C = _mth018D(_fld0182, _fld017C, j, ac1);
                if(_fld017C >= j)
                {
                    perl5pattern._fld0199++;
                    flag = false;
                    break label0;
                }
                if(perl5pattern._fld0198 >= 0)
                {
                    _fld017C -= perl5pattern._fld0198;
                    if(_fld017C < k)
                        _fld017C = k;
                    l = perl5pattern._fld0198 + ac1.length;
                } else
                if(!perl5pattern._fld0194 && --perl5pattern._fld0199 < 0)
                {
                    ac1 = perl5pattern._fld019B = null;
                    _fld017C = k;
                } else
                {
                    _fld017C = k;
                    l = ac1.length;
                }
            }
            _fld017E = k;
            _fld017D = j;
            int j1;
            if((perl5pattern._fld0191 & 1) != 0)
            {
                if(_mth0189(perl5pattern, k))
                    flag = true;
                else
                if(_fld0184 || (perl5pattern._fld0191 & 4) != 0)
                {
                    if(l > 0)
                        i1 = l - 1;
                    j -= i1;
                    if(_fld017C > k)
                        _fld017C--;
                    while(_fld017C < j) 
                        if(_fld0182[_fld017C++] == '\n' && _fld017C < j && _mth0189(perl5pattern, _fld017C))
                        {
                            flag = true;
                            break;
                        }
                }
            } else
            if(perl5pattern._fld019A != null)
            {
                char ac2[] = perl5pattern._fld019A;
                if((perl5pattern._fld0191 & 2) != 0)
                {
                    char c = ac2[0];
                    for(; _fld017C < j; _fld017C++)
                    {
                        if(c != _fld0182[_fld017C])
                            continue;
                        if(_mth0189(perl5pattern, _fld017C))
                        {
                            flag = true;
                            break;
                        }
                        for(_fld017C++; _fld017C < j && _fld0182[_fld017C] == c; _fld017C++);
                    }

                } else
                {
                    for(; (_fld017C = _mth018D(_fld0182, _fld017C, j, ac2)) < j; _fld017C++)
                    {
                        if(!_mth0189(perl5pattern, _fld017C))
                            continue;
                        flag = true;
                        break;
                    }

                }
            } else
            if((j1 = perl5pattern._fld0192) != -1)
            {
                boolean flag1 = (perl5pattern._fld0191 & 2) == 0;
                if(l > 0)
                    i1 = l - 1;
                j -= i1;
                boolean flag2 = true;
label1:
                switch(_fld017A[j1])
                {
                default:
                    break;

                case 9: // '\t'
                    j1 = _cls2._mth013C(j1);
                    for(; _fld017C < j; _fld017C++)
                    {
                        char c1 = _fld0182[_fld017C];
                        if(c1 < '\u0100' && (_fld017A[j1 + (c1 >> 4)] & 1 << (c1 & 0xf)) == 0)
                        {
                            if(flag2 && _mth0189(perl5pattern, _fld017C))
                            {
                                flag = true;
                                break;
                            }
                            flag2 = flag1;
                        } else
                        {
                            flag2 = true;
                        }
                    }

                    break;

                case 20: // '\024'
                    if(l > 0)
                    {
                        i1++;
                        j--;
                    }
                    if(_fld017C != k)
                    {
                        char c2 = _fld0182[_fld017C - 1];
                        flag2 = _cls2._mth0137(c2);
                    } else
                    {
                        flag2 = _cls2._mth0137(_fld0183);
                    }
                    for(; _fld017C < j; _fld017C++)
                    {
                        char c3 = _fld0182[_fld017C];
                        if(flag2 == _cls2._mth0137(c3))
                            continue;
                        flag2 = !flag2;
                        if(!_mth0189(perl5pattern, _fld017C))
                            continue;
                        flag = true;
                        break label1;
                    }

                    if((l > 0 || flag2) && _mth0189(perl5pattern, _fld017C))
                        flag = true;
                    break;

                case 21: // '\025'
                    if(l > 0)
                    {
                        i1++;
                        j--;
                    }
                    if(_fld017C != k)
                    {
                        char c4 = _fld0182[_fld017C - 1];
                        flag2 = _cls2._mth0137(c4);
                    } else
                    {
                        flag2 = _cls2._mth0137(_fld0183);
                    }
                    for(; _fld017C < j; _fld017C++)
                    {
                        char c5 = _fld0182[_fld017C];
                        if(flag2 != _cls2._mth0137(c5))
                        {
                            flag2 = !flag2;
                            continue;
                        }
                        if(!_mth0189(perl5pattern, _fld017C))
                            continue;
                        flag = true;
                        break label1;
                    }

                    if((l > 0 || !flag2) && _mth0189(perl5pattern, _fld017C))
                        flag = true;
                    break;

                case 18: // '\022'
                    for(; _fld017C < j; _fld017C++)
                    {
                        char c6 = _fld0182[_fld017C];
                        if(_cls2._mth0137(c6))
                        {
                            if(flag2 && _mth0189(perl5pattern, _fld017C))
                            {
                                flag = true;
                                break label1;
                            }
                            flag2 = flag1;
                        } else
                        {
                            flag2 = true;
                        }
                    }

                    break;

                case 19: // '\023'
                    for(; _fld017C < j; _fld017C++)
                    {
                        char c7 = _fld0182[_fld017C];
                        if(!_cls2._mth0137(c7))
                        {
                            if(flag2 && _mth0189(perl5pattern, _fld017C))
                            {
                                flag = true;
                                break label1;
                            }
                            flag2 = flag1;
                        } else
                        {
                            flag2 = true;
                        }
                    }

                    break;

                case 22: // '\026'
                    for(; _fld017C < j; _fld017C++)
                        if(Character.isSpace(_fld0182[_fld017C]))
                        {
                            if(flag2 && _mth0189(perl5pattern, _fld017C))
                            {
                                flag = true;
                                break label1;
                            }
                            flag2 = flag1;
                        } else
                        {
                            flag2 = true;
                        }

                    break;

                case 23: // '\027'
                    for(; _fld017C < j; _fld017C++)
                        if(!Character.isSpace(_fld0182[_fld017C]))
                        {
                            if(flag2 && _mth0189(perl5pattern, _fld017C))
                            {
                                flag = true;
                                break label1;
                            }
                            flag2 = flag1;
                        } else
                        {
                            flag2 = true;
                        }

                    break;

                case 24: // '\030'
                    for(; _fld017C < j; _fld017C++)
                        if(Character.isDigit(_fld0182[_fld017C]))
                        {
                            if(flag2 && _mth0189(perl5pattern, _fld017C))
                            {
                                flag = true;
                                break label1;
                            }
                            flag2 = flag1;
                        } else
                        {
                            flag2 = true;
                        }

                    break;

                case 25: // '\031'
                    for(; _fld017C < j; _fld017C++)
                        if(!Character.isDigit(_fld0182[_fld017C]))
                        {
                            if(flag2 && _mth0189(perl5pattern, _fld017C))
                            {
                                flag = true;
                                break label1;
                            }
                            flag2 = flag1;
                        } else
                        {
                            flag2 = true;
                        }

                    break;
                }
            } else
            {
                if(l > 0)
                    i1 = l - 1;
                j -= i1;
                do
                {
                    if(!_mth0189(perl5pattern, _fld017C))
                        continue;
                    flag = true;
                    break;
                } while(_fld017C++ < j);
            }
        }
        if(flag)
        {
            _fld0173 = new _cls3(_fld017F + 1);
            _fld0173._fld016F = new String(_fld0181, _fld0176[0], _fld0175[0] - _fld0176[0]);
            _fld0173._fld0172 = _fld0176[0];
            for(; _fld017F >= 0; _fld017F--)
            {
                int k1 = _fld0176[_fld017F];
                if(k1 >= 0)
                    _fld0173._fld0171[_fld017F] = k1 - _fld0173._fld0172;
                k1 = _fld0175[_fld017F];
                if(k1 >= 0)
                    _fld0173._fld0170[_fld017F] = k1 - _fld0173._fld0172;
            }

            return true;
        } else
        {
            _fld0173 = null;
            return false;
        }
    }

    boolean _mth0189(Perl5Pattern perl5pattern, int i)
    {
        _fld0178 = i;
        _fld0177 = 0;
        _fld0179 = 0;
        if(_fld017F > 0)
        {
            for(int j = 0; j < _fld017F; j++)
            {
                _fld0176[j] = -1;
                _fld0175[j] = -1;
            }

        }
        if(_mth0187(1))
        {
            _fld0176[0] = i;
            _fld0175[0] = _fld0178;
            return true;
        } else
        {
            return false;
        }
    }

    int _mth0188(int i, int j)
    {
        int k = _fld0178;
        int l = _fld017D;
        if(j != 65535 && j < l - k)
            l = k + j;
        int i1 = _cls2._mth013C(i);
label0:
        switch(_fld017A[i])
        {
        default:
            break;

        case 7: // '\007'
            for(; k < l && _fld0182[k] != '\n'; k++);
            break;

        case 8: // '\b'
            k = l;
            break;

        case 14: // '\016'
            for(i1++; k < l && _fld017A[i1] == _fld0182[k]; k++);
            break;

        case 9: // '\t'
            char c;
            if(k >= l || (c = _fld0182[k]) >= '\u0100')
                break;
            for(; (_fld017A[i1 + (c >> 4)] & 1 << (c & 0xf)) == 0; c = _fld0182[k])
                if(++k >= l)
                    break label0;

            break;

        case 18: // '\022'
            for(; k < l && _cls2._mth0137(_fld0182[k]); k++);
            break;

        case 19: // '\023'
            for(; k < l && !_cls2._mth0137(_fld0182[k]); k++);
            break;

        case 22: // '\026'
            for(; k < l && Character.isSpace(_fld0182[k]); k++);
            break;

        case 23: // '\027'
            for(; k < l && !Character.isSpace(_fld0182[k]); k++);
            break;

        case 24: // '\030'
            for(; k < l && Character.isDigit(_fld0182[k]); k++);
            break;

        case 25: // '\031'
            for(; k < l && !Character.isDigit(_fld0182[k]); k++);
            break;
        }
        int j1 = k - _fld0178;
        _fld0178 = k;
        return j1;
    }

    boolean _mth0187(int i)
    {
        boolean flag = true;
        boolean flag1 = false;
        int j1 = _fld0178;
        flag = j1 < _fld017B;
        int j = flag ? ((int) (_fld0182[j1])) : 65535;
        int l = i;
        int i1;
        for(int k1 = _fld017A.length; l < k1; l = i1)
        {
            i1 = _cls2._mth0138(_fld017A, l);
            char c;
            switch(c = _fld017A[l])
            {
            case 13: // '\r'
            case 15: // '\017'
            default:
                break;

            case 1: // '\001'
            case 2: // '\002'
                if(j1 != _fld017E ? !flag && j1 >= _fld017D || _fld0182[j1 - 1] != '\n' && true : _fld0183 != '\n' && true)
                    return false;
                break;

            case 3: // '\003'
                if(j1 != _fld017E || _fld0183 != '\n')
                    return false;
                break;

            case 30: // '\036'
                if(j1 != _fld017E)
                    return true;
                break;

            case 4: // '\004'
                if((flag || j1 < _fld017D) && j != 10)
                    return false;
                if(!_fld0184 && _fld017D - j1 > 1)
                    return false;
                break;

            case 5: // '\005'
                if((flag || j1 < _fld017D) && j != 10)
                    return false;
                break;

            case 6: // '\006'
                if((flag || j1 < _fld017D) && j != 10)
                    return false;
                if(_fld017D - j1 > 1)
                    return false;
                break;

            case 8: // '\b'
                if(!flag && j1 >= _fld017D)
                    return false;
                flag = ++j1 < _fld017B;
                j = flag ? ((int) (_fld0182[j1])) : 65535;
                break;

            case 7: // '\007'
                if(!flag && j1 >= _fld017D || j == 10)
                    return false;
                flag = ++j1 < _fld017B;
                j = flag ? ((int) (_fld0182[j1])) : 65535;
                break;

            case 14: // '\016'
                int l1 = _cls2._mth013C(l);
                char c2 = _fld017A[l1++];
                if(_fld017A[l1] != j)
                    return false;
                if(_fld017D - j1 < c2)
                    return false;
                if(c2 > '\001' && !_mth018E(_fld017A, l1, _fld0182, j1, c2))
                    return false;
                j1 += c2;
                flag = j1 < _fld017B;
                j = flag ? ((int) (_fld0182[j1])) : 65535;
                break;

            case 9: // '\t'
                int i2 = _cls2._mth013C(l);
                if(j == 65535 && flag)
                    j = _fld0182[j1];
                if(j >= 256 || (_fld017A[i2 + (j >> 4)] & 1 << (j & 0xf)) != 0)
                    return false;
                if(!flag && j1 >= _fld017D)
                    return false;
                flag = ++j1 < _fld017B;
                j = flag ? ((int) (_fld0182[j1])) : 65535;
                break;

            case 18: // '\022'
                if(!flag)
                    return false;
                if(!_cls2._mth0137((char) j))
                    return false;
                flag = ++j1 < _fld017B;
                j = flag ? ((int) (_fld0182[j1])) : 65535;
                break;

            case 19: // '\023'
                if(!flag && j1 >= _fld017D)
                    return false;
                if(_cls2._mth0137((char) j))
                    return false;
                flag = ++j1 < _fld017B;
                j = flag ? ((int) (_fld0182[j1])) : 65535;
                break;

            case 20: // '\024'
            case 21: // '\025'
                boolean flag3;
                if(j1 == _fld017E)
                    flag3 = _cls2._mth0137(_fld0183);
                else
                    flag3 = _cls2._mth0137(_fld0182[j1 - 1]);
                boolean flag4 = _cls2._mth0137((char) j);
                if((flag3 == flag4) == (_fld017A[l] == '\024'))
                    return false;
                break;

            case 22: // '\026'
                if(!flag && j1 >= _fld017D)
                    return false;
                if(!Character.isSpace((char) j))
                    return false;
                flag = ++j1 < _fld017B;
                j = flag ? ((int) (_fld0182[j1])) : 65535;
                break;

            case 23: // '\027'
                if(!flag)
                    return false;
                if(Character.isSpace((char) j))
                    return false;
                flag = ++j1 < _fld017B;
                j = flag ? ((int) (_fld0182[j1])) : 65535;
                break;

            case 24: // '\030'
                if(!Character.isDigit(j))
                    return false;
                flag = ++j1 < _fld017B;
                j = flag ? ((int) (_fld0182[j1])) : 65535;
                break;

            case 25: // '\031'
                if(!flag && j1 >= _fld017D)
                    return false;
                if(Character.isDigit(j))
                    return false;
                flag = ++j1 < _fld017B;
                j = flag ? ((int) (_fld0182[j1])) : 65535;
                break;

            case 26: // '\032'
                char c3 = _cls2._mth013E(_fld017A, l);
                int j2 = _fld0176[c3];
                if(j2 == -1)
                    return false;
                if(_fld0175[c3] == -1)
                    return false;
                if(j2 == _fld0175[c3])
                    break;
                if(_fld0182[j2] != j)
                    return false;
                int k2 = _fld0175[c3] - j2;
                if(j1 + k2 > _fld017D)
                    return false;
                if(k2 > 1 && !_mth018E(_fld0182, j2, _fld0182, j1, k2))
                    return false;
                j1 += k2;
                flag = j1 < _fld017B;
                j = flag ? ((int) (_fld0182[j1])) : 65535;
                break;

            case 27: // '\033'
                int l3 = _cls2._mth013E(_fld017A, l);
                _fld0176[l3] = j1;
                if(l3 > _fld0179)
                    _fld0179 = l3;
                break;

            case 28: // '\034'
                int i4 = _cls2._mth013E(_fld017A, l);
                _fld0175[i4] = j1;
                if(i4 > _fld0177)
                    _fld0177 = i4;
                break;

            case 11: // '\013'
                _cls4 _lcls4 = new _cls4();
                _lcls4._fld01A0 = _fld0180;
                _fld0180 = _lcls4;
                _lcls4._fld01A8 = _fld0177;
                _lcls4._fld01A7 = -1;
                _lcls4._fld01A6 = _cls2._mth013E(_fld017A, l);
                _lcls4._fld01A5 = _cls2._mth013D(_fld017A, l);
                _lcls4._fld01A3 = _cls2._mth013A(l) + 2;
                _lcls4._fld01A2 = i1;
                _lcls4._fld01A4 = flag1;
                _lcls4._fld01A1 = -1;
                _fld0178 = j1;
                flag1 = _mth0187(_cls2._mth0139(i1));
                _fld0180 = _lcls4._fld01A0;
                return flag1;

            case 34: // '"'
                _cls4 _lcls4_1 = _fld0180;
                int j4 = _lcls4_1._fld01A7 + 1;
                _fld0178 = j1;
                if(j1 == _lcls4_1._fld01A1)
                {
                    _fld0180 = _lcls4_1._fld01A0;
                    int l2 = _fld0180._fld01A7;
                    if(_mth0187(_lcls4_1._fld01A2))
                    {
                        return true;
                    } else
                    {
                        _fld0180._fld01A7 = l2;
                        _fld0180 = _lcls4_1;
                        return false;
                    }
                }
                if(j4 < _lcls4_1._fld01A6)
                {
                    _lcls4_1._fld01A7 = j4;
                    _lcls4_1._fld01A1 = j1;
                    if(_mth0187(_lcls4_1._fld01A3))
                    {
                        return true;
                    } else
                    {
                        _lcls4_1._fld01A7 = j4 - 1;
                        return false;
                    }
                }
                if(_lcls4_1._fld01A4)
                {
                    _fld0180 = _lcls4_1._fld01A0;
                    int i3 = _fld0180._fld01A7;
                    if(_mth0187(_lcls4_1._fld01A2))
                        return true;
                    _fld0180._fld01A7 = i3;
                    _fld0180 = _lcls4_1;
                    if(j4 >= _lcls4_1._fld01A5)
                        return false;
                    _fld0178 = j1;
                    _lcls4_1._fld01A7 = j4;
                    _lcls4_1._fld01A1 = j1;
                    if(_mth0187(_lcls4_1._fld01A3))
                    {
                        return true;
                    } else
                    {
                        _lcls4_1._fld01A7 = j4 - 1;
                        return false;
                    }
                }
                if(j4 < _lcls4_1._fld01A5)
                {
                    _mth018C(_lcls4_1._fld01A8);
                    _lcls4_1._fld01A7 = j4;
                    _lcls4_1._fld01A1 = j1;
                    if(_mth0187(_lcls4_1._fld01A3))
                        return true;
                    _mth018B();
                    _fld0178 = j1;
                }
                _fld0180 = _lcls4_1._fld01A0;
                int j3 = _fld0180._fld01A7;
                if(_mth0187(_lcls4_1._fld01A2))
                {
                    return true;
                } else
                {
                    _lcls4_1._fld01A7 = j3;
                    _fld0180 = _lcls4_1;
                    _lcls4_1._fld01A7 = j4 - 1;
                    return false;
                }

            case 12: // '\f'
                if(_fld017A[i1] != '\f')
                {
                    i1 = _cls2._mth013A(l);
                    break;
                }
                int i5 = _fld0177;
                do
                {
                    _fld0178 = j1;
                    if(_mth0187(_cls2._mth013A(l)))
                        return true;
                    int k4;
                    for(k4 = _fld0177; k4 > i5; k4--)
                        _fld0175[k4] = 0;

                    _fld0177 = k4;
                    l = _cls2._mth0138(_fld017A, l);
                } while(l != -1 && _fld017A[l] == '\f');
                return false;

            case 29: // '\035'
                flag1 = true;
                break;

            case 10: // '\n'
            case 16: // '\020'
            case 17: // '\021'
                int k3;
                int l4;
                if(c == '\n')
                {
                    k3 = _cls2._mth013E(_fld017A, l);
                    l4 = _cls2._mth013D(_fld017A, l);
                    l = _cls2._mth013A(l) + 2;
                } else
                if(c == '\020')
                {
                    k3 = 0;
                    l4 = 65535;
                    l = _cls2._mth013A(l);
                } else
                {
                    k3 = 1;
                    l4 = 65535;
                    l = _cls2._mth013A(l);
                }
                int k;
                char c1;
                if(_fld017A[i1] == '\016')
                {
                    k = _fld017A[_cls2._mth013C(i1) + 1];
                    c1 = '\0';
                } else
                {
                    k = 65535;
                    c1 = '\uFC18';
                }
                _fld0178 = j1;
                if(flag1)
                {
                    boolean flag2 = false;
                    if(k3 > 0 && _mth0188(l, k3) < k3)
                        return false;
                    while(l4 >= k3 || l4 == 65535 && k3 > 0) 
                    {
                        if((c1 == '\uFC18' || _fld0178 >= _fld017B || _fld0182[_fld0178] == k) && _mth0187(i1))
                            return true;
                        _fld0178 = j1 + k3;
                        if(_mth0188(l, 1) != 0)
                        {
                            k3++;
                            _fld0178 = j1 + k3;
                        } else
                        {
                            return false;
                        }
                    }
                } else
                {
                    l4 = _mth0188(l, l4);
                    if(k3 < l4 && _cls2._fld0112[_fld017A[i1]] == '\004' && (!_fld0184 || _fld017A[i1] == '\006'))
                        k3 = l4;
                    while(l4 >= k3) 
                    {
                        if((c1 == '\uFC18' || _fld0178 >= _fld017B || _fld0182[_fld0178] == k) && _mth0187(i1))
                            return true;
                        l4--;
                        _fld0178 = j1 + l4;
                    }
                }
                return false;

            case 0: // '\0'
            case 33: // '!'
                _fld0178 = j1;
                return true;

            case 31: // '\037'
                _fld0178 = j1;
                l = _cls2._mth013A(l);
                if(!_mth0187(l))
                    return false;
                break;

            case 32: // ' '
                _fld0178 = j1;
                l = _cls2._mth013A(l);
                if(_mth0187(l))
                    return false;
                break;
            }
        }

        return false;
    }

    public void setMultiline(boolean flag)
    {
        _fld0184 = flag;
    }

    public boolean isMultiline()
    {
        return _fld0184;
    }

    char[] _mth0186(char ac[])
    {
        char ac1[] = new char[ac.length];
        System.arraycopy(ac, 0, ac1, 0, ac.length);
        ac = ac1;
        for(int i = 0; i < ac.length; i++)
            if(Character.isUpperCase(ac[i]))
                ac[i] = Character.toLowerCase(ac[i]);

        return ac;
    }

    public boolean matches(String s, Pattern pattern)
    {
        return matches(s.toCharArray(), pattern);
    }

    public boolean matches(char ac[], Pattern pattern)
    {
        Perl5Pattern perl5pattern = (Perl5Pattern)pattern;
        _fld0181 = ac;
        if(perl5pattern._fld0195)
            ac = _mth0186(ac);
        if(_mth018A(perl5pattern, ac, 0, ac.length) && _fld0173.beginOffset(0) == 0 && _fld0173.endOffset(0) == ac.length)
        {
            return true;
        } else
        {
            _fld0173 = null;
            return false;
        }
    }

    public boolean matches(PatternMatcherInput patternmatcherinput, Pattern pattern)
    {
        Perl5Pattern perl5pattern = (Perl5Pattern)pattern;
        _fld0181 = patternmatcherinput._fld0144;
        char ac[];
        if(perl5pattern._fld0195)
        {
            if(patternmatcherinput._fld0143 == null)
                patternmatcherinput._fld0143 = _mth0186(_fld0181);
            ac = patternmatcherinput._fld0143;
        } else
        {
            ac = _fld0181;
        }
        if(_mth018A(perl5pattern, ac, patternmatcherinput._fld0142, patternmatcherinput._fld0141))
        {
            if(_fld0173.beginOffset(0) == patternmatcherinput._fld0142 && _fld0173.endOffset(0) == patternmatcherinput._fld0141)
                return true;
            if(patternmatcherinput.length() == 0 || patternmatcherinput._fld0142 == patternmatcherinput._fld0141)
                return true;
        }
        _fld0173 = null;
        return false;
    }

    public boolean contains(String s, Pattern pattern)
    {
        return contains(s.toCharArray(), pattern);
    }

    public boolean contains(char ac[], Pattern pattern)
    {
        Perl5Pattern perl5pattern = (Perl5Pattern)pattern;
        _fld0181 = ac;
        if(perl5pattern._fld0195)
            ac = _mth0186(ac);
        return _mth018A(perl5pattern, ac, 0, ac.length);
    }

    public boolean contains(PatternMatcherInput patternmatcherinput, Pattern pattern)
    {
        if(patternmatcherinput._fld0140 > patternmatcherinput._fld0141)
            return false;
        Perl5Pattern perl5pattern = (Perl5Pattern)pattern;
        _fld0181 = patternmatcherinput._fld0144;
        _fld0181 = patternmatcherinput._fld0144;
        char ac[];
        if(perl5pattern._fld0195)
        {
            if(patternmatcherinput._fld0143 == null)
                patternmatcherinput._fld0143 = _mth0186(_fld0181);
            ac = patternmatcherinput._fld0143;
        } else
        {
            ac = _fld0181;
        }
        boolean flag = _mth018A(perl5pattern, ac, patternmatcherinput._fld0140, patternmatcherinput._fld0141);
        if(flag)
        {
            if(_fld0175[0] > _fld0176[0])
                patternmatcherinput.setCurrentOffset(_fld0175[0]);
            else
                patternmatcherinput.setCurrentOffset(_fld0176[0] + 1);
        } else
        {
            patternmatcherinput.setCurrentOffset(patternmatcherinput._fld0141 + 1);
        }
        return flag;
    }

    public boolean contains(Perl5StreamInput perl5streaminput, Perl5Pattern perl5pattern)
        throws IOException
    {
        boolean flag = false;
        if(perl5streaminput._fld01AC == 0 && perl5streaminput._fld01AD == 0)
        {
            if(!perl5streaminput._mth01B4(perl5pattern._fld0195))
                return false;
        } else
        if(perl5streaminput._fld01AB >= perl5streaminput._fld01AD)
        {
            perl5streaminput._mth01B5(perl5streaminput._fld01AD, perl5pattern._fld0195);
            perl5streaminput._fld01AB = 0;
        }
        if(perl5streaminput._fld01AF)
            return false;
        int i = 0x7fffffff;
label0:
        do
        {
            do
            {
                if(perl5streaminput._fld01AD < perl5streaminput._fld01AA.length)
                    i = perl5streaminput._fld01AD;
                try
                {
                    _fld0181 = perl5streaminput._fld01A9;
                    flag = _mth018A(perl5pattern, perl5streaminput._fld01AA, perl5streaminput._fld01AB, i);
                    break;
                }
                catch(ArrayIndexOutOfBoundsException _ex)
                {
                    if(_fld017C > perl5streaminput._fld01AD)
                        perl5streaminput._mth01B5(perl5streaminput._fld01AB, perl5pattern._fld0195);
                    else
                        perl5streaminput._mth01B5(_fld017C, perl5pattern._fld0195);
                    if(perl5streaminput._fld01AF)
                    {
                        if(i != 0x7fffffff)
                            break label0;
                        i = perl5streaminput._fld01AD;
                    } else
                    {
                        perl5streaminput._fld01AB = 0;
                    }
                }
            } while(true);
            if(flag)
            {
                perl5streaminput._fld01AB = _fld0175[0];
                if(perl5streaminput._fld01AB == _fld017C)
                    perl5streaminput._fld01AB++;
                _fld0173._fld0172 = perl5streaminput._fld01AC + _fld017C;
                break;
            }
            perl5streaminput._mth01B5(perl5streaminput._fld01AD, perl5pattern._fld0195);
            perl5streaminput._fld01AB = 0;
        } while(!perl5streaminput._fld01AF);
        return flag;
    }

    public MatchResult getMatch()
    {
        return _fld0173;
    }

    public Perl5Matcher()
    {
        _fld0184 = true;
        _fld0174 = new Stack();
    }

    private static final char _fld0185 = 65535;
    private boolean _fld0184;
    private char _fld0183;
    private char _fld0182[];
    private char _fld0181[];
    private _cls4 _fld0180;
    private int _fld017F;
    private int _fld017E;
    private int _fld017D;
    private int _fld017C;
    private int _fld017B;
    private char _fld017A[];
    private int _fld0179;
    private int _fld0178;
    private int _fld0177;
    private int _fld0176[];
    private int _fld0175[];
    private Stack _fld0174;
    private _cls3 _fld0173;
}
