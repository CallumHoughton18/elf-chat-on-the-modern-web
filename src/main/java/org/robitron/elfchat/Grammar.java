package org.robitron.elfchat;// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Grammar.java

import java.util.Vector;

public class Grammar
{

    public String lookup(String s)
    {
        if(s.equals("I"))
            return "I";
        else
            return "";
    }

    public void removeCants(Vector vector)
    {
        for(int i = 0; i < vector.size(); i++)
        {
            String s = (String)vector.elementAt(i);
            if(s.equalsIgnoreCase("cannot"))
            {
                vector.setElementAt("can", i);
                vector.insertElementAt("not", i + 1);
            } else
            if(s.charAt(0) == '\'' && i < vector.size() - 1)
            {
                String s1 = (String)vector.elementAt(i + 1);
                boolean flag = false;
                for(int j = 0; j < contraIn.length; j++)
                    if(s1.equalsIgnoreCase(contraIn[j]))
                    {
                        vector.setElementAt(contraOut[j], i + 1);
                        vector.removeElementAt(i);
                        flag = true;
                    }

                if(!flag && i > 0)
                {
                    String s2 = (String)vector.elementAt(i - 1);
                    if(s2.endsWith("in"))
                    {
                        vector.setElementAt(s2 + "g", i - 1);
                        vector.removeElementAt(i + 1);
                        vector.removeElementAt(i);
                    } else
                    if(s2.equalsIgnoreCase("y") && s1.equalsIgnoreCase("all"))
                    {
                        vector.setElementAt("you", i - 1);
                        vector.removeElementAt(i);
                    } else
                    if(s1.equalsIgnoreCase("t"))
                    {
                        for(int k = 0; k < negateIn.length; k++)
                            if(s2.equalsIgnoreCase(negateIn[k]))
                            {
                                vector.setElementAt(negateOut[k], i - 1);
                                vector.setElementAt("not", i + 1);
                                vector.removeElementAt(i);
                            }

                    }
                }
            }
        }

    }

    public void removeFluff(Vector vector)
    {
        for(int i = 0; i < vector.size(); i++)
        {
            String s = (String)vector.elementAt(i);
            if(s.equals("&"))
                vector.setElementAt("and", i);
            else
            if(breaks.indexOf(s.charAt(0)) >= 0)
                vector.removeElementAt(i);
        }

    }

    public String mapTense(String s)
    {
        boolean flag = false;
        String s1 = s;
        for(int i = 0; i < tenseIn.length && !flag; i++)
            if(s.equalsIgnoreCase(tenseIn[i]))
            {
                s1 = tenseOut[i];
                flag = true;
            }

        return s1;
    }

    public Grammar()
    {
    }

    public static String breaks = "\t ~`'!&*()+={}[]|:;<,>.?/";
    public static final String contraIn[] = {
        "m", "d", "s", "ll", "re", "ve"
    };
    public static final String contraOut[] = {
        "am", "would", "is", "will", "are", "have"
    };
    public static final String negateIn[] = {
        "can", "don", "didn", "isn", "aren", "won", "shan", "couldn", "wouldn", "shouldn", 
        "haven", "doesn"
    };
    static String negateOut[] = {
        "can", "do", "did", "is", "are", "will", "shall", "could", "would", "should", 
        "have", "does"
    };
    public static final String tenseIn[] = {
        "you", "i", "me", "am", "are", "my", "your", "yourself", "myself", "mine", 
        "yours", "us"
    };
    public static final String tenseOut[] = {
        "I", "you", "you", "are", "am", "your", "my", "myself", "yourself", "yours", 
        "mine", "y'all"
    };

}
