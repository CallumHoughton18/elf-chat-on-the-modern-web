package org.robitron.elfchat;// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TSVector.java

import java.util.StringTokenizer;
import java.util.Vector;

public class TSVector extends Vector
{

    public TSVector(String s, String s1, boolean flag, boolean flag1)
    {
        breaks = "\t";
        StringTokenizer stringtokenizer = new StringTokenizer(s, s1, true);
        boolean flag2 = true;
        while(stringtokenizer.hasMoreTokens()) 
        {
            String s2 = stringtokenizer.nextToken();
            boolean flag3 = true;
            if(s1.indexOf(s2) < 0)
                flag2 = false;
            else
            if(flag2)
            {
                s2 = new String("");
            } else
            {
                flag3 = false;
                flag2 = true;
            }
            if(flag3)
            {
                if(flag1)
                    System.out.println(s2);
                addElement(s2);
            }
        }
    }

    public String pop()
    {
        String s = "";
        if(size() > 0)
        {
            s = (String)elementAt(0);
            removeElementAt(0);
        }
        return s;
    }

    protected String breaks;
}
