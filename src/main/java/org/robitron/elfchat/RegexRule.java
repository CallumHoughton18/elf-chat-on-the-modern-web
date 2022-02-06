package org.robitron.elfchat;// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RegexRule.java

import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

// Referenced classes of package com.robitron.phred:
//            Rule, ActionRule, Grammar, Phrase

public class RegexRule extends Rule
{
    class RegexPattern
    {

        Pattern pat;
        Vector var;

        RegexPattern(Pattern pattern, Vector vector)
        {
            pat = pattern;
            var = vector;
        }
    }


    public RegexRule(String s)
    {
        super(s);
    }

    public void addElement(Phrase phrase, Perl5Compiler perl5compiler)
    {
        StringBuffer stringbuffer = new StringBuffer();
        Vector vector = new Vector();
        Object obj = null;
        for(int i = 0; i < phrase.size(); i++)
        {
            String s = (String)phrase.elementAt(i);
            if(s.startsWith("$"))
            {
                stringbuffer.append("(.+)");
                vector.addElement(s.substring(1));
            } else
            {
                stringbuffer.append(s);
            }
        }

        try
        {
            Pattern pattern = perl5compiler.compile(stringbuffer.toString(), 1);
            addElement(new RegexPattern(pattern, vector));
            return;
        }
        catch(MalformedPatternException malformedpatternexception)
        {
            System.err.println(malformedpatternexception.getMessage());
            malformedpatternexception.printStackTrace();
            return;
        }
    }

    public boolean testPhrase(String s, Properties properties, PatternMatcher patternmatcher, Grammar grammar)
    {
        for(int i = 0; i < size(); i++)
        {
            RegexPattern regexpattern = (RegexPattern)elementAt(i);
            if(patternmatcher.contains(s, regexpattern.pat))
            {
                MatchResult matchresult = patternmatcher.getMatch();
                for(int j = 0; j < regexpattern.var.size(); j++)
                {
                    String s1 = (String)regexpattern.var.elementAt(j);
                    Phrase phrase = new Phrase(matchresult.group(j + 1).trim(), Grammar.breaks);
                    grammar.removeFluff(phrase);
                    properties.put(s1, phrase.rePhrase(grammar, properties).trim());
                }

                return true;
            }
        }

        return false;
    }

    public Vector lookup(Hashtable hashtable)
    {
        Vector vector = new Vector();
        String s = getProperty("invokes");
        if(s != null)
        {
            TSVector tsvector = new TSVector(s, " ", false, false);
            for(int i = 0; i < tsvector.size(); i++)
            {
                String s1 = tsvector.pop();
                ActionRule actionrule = (ActionRule)hashtable.get(s1);
                vector.addElement(actionrule);
            }

        }
        return vector;
    }
}
