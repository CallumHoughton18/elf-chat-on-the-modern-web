package org.robitron.elfchat;// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3)


// Referenced classes of package com.oroinc.text.regex:
//            _cls2, Perl5Pattern

public final class Perl5Debug
{

    private Perl5Debug()
    {
    }

    public static String printProgram(Perl5Pattern perl5pattern)
    {
        char c = '\033';
        char ac[] = perl5pattern._fld0193;
        int i = 1;
        StringBuffer stringbuffer = new StringBuffer();
        while(c != 0) 
        {
            c = ac[i];
            stringbuffer.append(i);
            _mth016E(ac, i, stringbuffer);
            int j = _cls2._mth0138(ac, i);
            i += _cls2._fld0113[c];
            stringbuffer.append("(" + j + ")");
            i += 2;
            if(c == '\t')
                i += 16;
            else
            if(c == '\016')
            {
                i++;
                stringbuffer.append(" <");
                for(; ac[i] != '\uFFFF'; i++)
                    stringbuffer.append(ac[i]);

                stringbuffer.append(">");
                i++;
            }
            stringbuffer.append('\n');
        }
        if(perl5pattern._fld019A != null)
            stringbuffer.append("start `" + new String(perl5pattern._fld019A) + "' ");
        if(perl5pattern._fld0192 != -1)
        {
            stringbuffer.append("stclass `");
            _mth016E(ac, perl5pattern._fld0192, stringbuffer);
            stringbuffer.append("' ");
        }
        if((perl5pattern._fld0191 & 1) != 0)
            stringbuffer.append("anchored ");
        if((perl5pattern._fld0191 & 2) != 0)
            stringbuffer.append("plus ");
        if((perl5pattern._fld0191 & 4) != 0)
            stringbuffer.append("implicit ");
        if(perl5pattern._fld019B != null)
            stringbuffer.append("must have \"" + new String(perl5pattern._fld019B) + "\" back " + perl5pattern._fld0198 + " ");
        stringbuffer.append("minlen " + perl5pattern._fld0197 + '\n');
        return stringbuffer.toString();
    }

    static void _mth016E(char ac[], int i, StringBuffer stringbuffer)
    {
        String s = null;
        stringbuffer.append(":");
        switch(ac[i])
        {
        case 1: // '\001'
            s = "BOL";
            break;

        case 2: // '\002'
            s = "MBOL";
            break;

        case 3: // '\003'
            s = "SBOL";
            break;

        case 4: // '\004'
            s = "EOL";
            break;

        case 5: // '\005'
            s = "MEOL";
            break;

        case 7: // '\007'
            s = "ANY";
            break;

        case 8: // '\b'
            s = "SANY";
            break;

        case 9: // '\t'
            s = "ANYOF";
            break;

        case 12: // '\f'
            s = "BRANCH";
            break;

        case 14: // '\016'
            s = "EXACTLY";
            break;

        case 15: // '\017'
            s = "NOTHING";
            break;

        case 13: // '\r'
            s = "BACK";
            break;

        case 0: // '\0'
            s = "END";
            break;

        case 18: // '\022'
            s = "ALNUM";
            break;

        case 19: // '\023'
            s = "NALNUM";
            break;

        case 20: // '\024'
            s = "BOUND";
            break;

        case 21: // '\025'
            s = "NBOUND";
            break;

        case 22: // '\026'
            s = "SPACE";
            break;

        case 23: // '\027'
            s = "NSPACE";
            break;

        case 24: // '\030'
            s = "DIGIT";
            break;

        case 25: // '\031'
            s = "NDIGIT";
            break;

        case 10: // '\n'
            stringbuffer.append("CURLY {");
            stringbuffer.append(_cls2._mth013E(ac, i));
            stringbuffer.append(',');
            stringbuffer.append(_cls2._mth013D(ac, i));
            stringbuffer.append('}');
            break;

        case 11: // '\013'
            stringbuffer.append("CURLYX {");
            stringbuffer.append(_cls2._mth013E(ac, i));
            stringbuffer.append(',');
            stringbuffer.append(_cls2._mth013D(ac, i));
            stringbuffer.append('}');
            break;

        case 26: // '\032'
            stringbuffer.append("REF");
            stringbuffer.append(_cls2._mth013E(ac, i));
            break;

        case 27: // '\033'
            stringbuffer.append("OPEN");
            stringbuffer.append(_cls2._mth013E(ac, i));
            break;

        case 28: // '\034'
            stringbuffer.append("CLOSE");
            stringbuffer.append(_cls2._mth013E(ac, i));
            break;

        case 16: // '\020'
            s = "STAR";
            break;

        case 17: // '\021'
            s = "PLUS";
            break;

        case 29: // '\035'
            s = "MINMOD";
            break;

        case 30: // '\036'
            s = "GBOL";
            break;

        case 32: // ' '
            s = "UNLESSM";
            break;

        case 31: // '\037'
            s = "IFMATCH";
            break;

        case 33: // '!'
            s = "SUCCEED";
            break;

        case 34: // '"'
            s = "WHILEM";
            break;

        case 6: // '\006'
        default:
            stringbuffer.append("Operator is unrecognized.  Faulty expression code!");
            break;
        }
        if(s != null)
            stringbuffer.append(s);
    }
}
