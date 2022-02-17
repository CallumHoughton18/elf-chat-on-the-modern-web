package org.robitron.elfchat;// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Fred.java

import java.io.*;
import java.net.URL;
import java.util.Hashtable;
import java.util.Random;
import java.util.Vector;

// Referenced classes of package com.robitron.phred:
//            ActionRule, Context, FuzzyRule, FuzzyUnion, 
//            Grammar, Phrase, RegexRule, Rule

public class Fred {
    public Fred(Grammar grammar1, boolean flag, URL ruleSetUrl) {
        isVerbose = true;
        vRegx = new Vector();
        vFuzz = new Vector();
        fuzzHash = new Hashtable();
        vActs = new Vector();
        actsHash = new Hashtable();
        this.ruleSetUrl = ruleSetUrl;
        grammar = grammar1;
        isVerbose = flag;
        setupDefaults();
        loadFile();
        prepRules();
    }

    protected void setupDefaults() {
    }

    public void setGrammar(Grammar grammar1, boolean flag) {
        grammar = grammar1;
        isVerbose = flag;
    }

    public Grammar getGrammar() {
        return grammar;
    }

    public void setVerbose(boolean flag) {
        isVerbose = flag;
    }

    public boolean getVerbose() {
        return isVerbose;
    }

    public void prepRules() {
        vRegx.copyInto(regx = new RegexRule[vRegx.size()]);
        vRegx = null;
        vFuzz.copyInto(fuzz = new FuzzyRule[vFuzz.size()]);
        vFuzz = null;
        for (int i = 0; i < fuzz.length; i++)
            fuzz[i].normalize(actsHash);

        vActs.copyInto(acts = new ActionRule[vActs.size()]);
        vActs = null;
        for (int j = 0; j < acts.length; j++) {
            acts[j].shuffle();
            acts[j].setPriority();
        }

        if (intro != null)
            intro.shuffle();
    }

    public boolean loadFile(InputStream stream) {
        Object obj = null;
        try {
            DataInputStream datainputstream = new DataInputStream(stream);
            Object obj1 = null;
            System.out.print("\nloading: " + ruleSetUrl);
            String s;
            while ((s = datainputstream.readLine()) != null)
                if (!s.trim().equals("") && !s.trim().startsWith("#"))
                    if (s.charAt(0) != '\t') {
                        if (s.toLowerCase().startsWith("intro:")) {
                            obj1 = new ActionRule(s.substring("intro:".length()).trim());
                            if (intro != null)
                                System.out.println("redefining intro rule: " + ((Rule) (obj1)).getName());
                            intro = (ActionRule) obj1;
                        } else if (s.toLowerCase().startsWith("regex:")) {
                            String s1 = s.substring("regex:".length()).trim();
                            vRegx.addElement(obj1 = new RegexRule(s1));
                        } else if (s.toLowerCase().startsWith("action:")) {
                            String s2 = s.substring("action:".length()).trim();
                            obj1 = (ActionRule) actsHash.get(s2);
                            if (obj1 != null)
                                System.out.println("redefining action rule: " + ((Rule) (obj1)).getName());
                            vActs.addElement(obj1 = new ActionRule(s2));
                            actsHash.put(((Rule) (obj1)).getName(), obj1);
                        } else if (s.toLowerCase().startsWith("fuzzy:")) {
                            String s3 = s.substring("fuzzy:".length()).trim();
                            obj1 = (FuzzyRule) fuzzHash.get(s3);
                            if (obj1 == null) {
                                vFuzz.addElement(obj1 = new FuzzyRule(s3));
                                fuzzHash.put(((Rule) (obj1)).getName(), obj1);
                            }
                        }
                    } else if (!testProperty(s, ((Rule) (obj1))))
                        if (obj1 instanceof RegexRule) {
                            RegexRule regexrule = (RegexRule) obj1;
                            regexrule.addElement(new Phrase(s.trim(), Grammar.breaks), compiler);
                        } else if (obj1 instanceof FuzzyRule) {
                            FuzzyRule fuzzyrule = (FuzzyRule) obj1;
                            if (matcher.contains(s, patFuzz)) {
                                MatchResult matchresult = matcher.getMatch();
                                String s4 = matchresult.group(1).trim();
                                String s5 = matchresult.group(2).trim();
                                try {
                                    fuzzyrule.addElement(Integer.parseInt(s4), s5);
                                } catch (NumberFormatException numberformatexception) {
                                    System.err.println("Fred: fuzzy set probability not understood - " + s4);
                                    System.err.println(numberformatexception.getMessage());
                                    numberformatexception.printStackTrace();
                                }
                            } else {
                                System.err.println("Fred: fuzzy set not understood - " + s);
                            }
                        } else {
                            ((Vector) (obj1)).addElement(s.trim());
                        }
            datainputstream.close();
            System.out.println(" " + (vActs.size() + vRegx.size() + vFuzz.size()) + " rules");
            return true;
        } catch (IOException ioexception) {
            System.err.println("Fred: I/O error - " + ioexception.getMessage());
            ioexception.printStackTrace();
            return false;
        }
    }

    public boolean loadFile() {
        Object obj = null;
        try {
            DataInputStream datainputstream = new DataInputStream(ruleSetUrl.openStream());
            Object obj1 = null;
            System.out.print("\nloading: " + ruleSetUrl);
            String s;
            while ((s = datainputstream.readLine()) != null)
                if (!s.trim().equals("") && !s.trim().startsWith("#"))
                    if (s.charAt(0) != '\t') {
                        if (s.toLowerCase().startsWith("intro:")) {
                            obj1 = new ActionRule(s.substring("intro:".length()).trim());
                            if (intro != null)
                                System.out.println("redefining intro rule: " + ((Rule) (obj1)).getName());
                            intro = (ActionRule) obj1;
                        } else if (s.toLowerCase().startsWith("regex:")) {
                            String s1 = s.substring("regex:".length()).trim();
                            vRegx.addElement(obj1 = new RegexRule(s1));
                        } else if (s.toLowerCase().startsWith("action:")) {
                            String s2 = s.substring("action:".length()).trim();
                            obj1 = (ActionRule) actsHash.get(s2);
                            if (obj1 != null)
                                System.out.println("redefining action rule: " + ((Rule) (obj1)).getName());
                            vActs.addElement(obj1 = new ActionRule(s2));
                            actsHash.put(((Rule) (obj1)).getName(), obj1);
                        } else if (s.toLowerCase().startsWith("fuzzy:")) {
                            String s3 = s.substring("fuzzy:".length()).trim();
                            obj1 = (FuzzyRule) fuzzHash.get(s3);
                            if (obj1 == null) {
                                vFuzz.addElement(obj1 = new FuzzyRule(s3));
                                fuzzHash.put(((Rule) (obj1)).getName(), obj1);
                            }
                        }
                    } else if (!testProperty(s, ((Rule) (obj1))))
                        if (obj1 instanceof RegexRule) {
                            RegexRule regexrule = (RegexRule) obj1;
                            regexrule.addElement(new Phrase(s.trim(), Grammar.breaks), compiler);
                        } else if (obj1 instanceof FuzzyRule) {
                            FuzzyRule fuzzyrule = (FuzzyRule) obj1;
                            if (matcher.contains(s, patFuzz)) {
                                MatchResult matchresult = matcher.getMatch();
                                String s4 = matchresult.group(1).trim();
                                String s5 = matchresult.group(2).trim();
                                try {
                                    fuzzyrule.addElement(Integer.parseInt(s4), s5);
                                } catch (NumberFormatException numberformatexception) {
                                    System.err.println("Fred: fuzzy set probability not understood - " + s4);
                                    System.err.println(numberformatexception.getMessage());
                                    numberformatexception.printStackTrace();
                                }
                            } else {
                                System.err.println("Fred: fuzzy set not understood - " + s);
                            }
                        } else {
                            ((Vector) (obj1)).addElement(s.trim());
                        }
            datainputstream.close();
            System.out.println(" " + (vActs.size() + vRegx.size() + vFuzz.size()) + " rules");
            return true;
        } catch (IOException ioexception) {
            System.err.println("Fred: I/O error - " + ioexception.getMessage());
            ioexception.printStackTrace();
            return false;
        }
    }

    protected boolean testProperty(String s, Rule rule) {
        if (matcher.contains(s, patProp)) {
            MatchResult matchresult = matcher.getMatch();
            String s1 = matchresult.group(1).toLowerCase().trim();
            String s2 = matchresult.group(2).trim();
            if (s1.equals("url") && (rule instanceof ActionRule)) {
                ActionRule actionrule = (ActionRule) rule;
                actionrule.url.addElement(s2);
            } else {
                rule.putProperty(s1, s2);
            }
            return true;
        } else {
            return false;
        }
    }

    protected ActionRule chooseReply(Phrase phrase, Context context, boolean flag) {
        FuzzyUnion fuzzyunion = new FuzzyUnion();
        for (int i = 0; i < context.next.size(); i++) {
            ActionRule actionrule = (ActionRule) context.next.elementAt(i);
            fuzzyunion.addRule(actionrule);
        }

        for (int j = 0; j < fuzz.length; j++)
            if (phrase.testRule(fuzz[j]))
                fuzzyunion.addRule(fuzz[j]);

        for (int k = 0; k < regx.length; k++) {
            if (!regx[k].testPhrase(phrase.expanded, context.dirt, matcher, grammar))
                continue;
            fuzzyunion.addRules(regx[k].lookup(actsHash));
            break;
        }

        fuzzyunion.cullProperties(context.dirt, flag);
        fuzzyunion.sort();
        fuzzyunion.priorUse(context.used);
        if (isVerbose)
            fuzzyunion.printTrace(context.used);
        ActionRule actionrule1 = fuzzyunion.selectOne(acts[0]);
        context.logRule(actionrule1);
        return actionrule1;
    }

    public String formReply(Phrase phrase, Context context, boolean flag) {
        StringBuffer stringbuffer = new StringBuffer(4096);
        ActionRule actionrule = null;
        String s = "";
        actionrule = chooseReply(phrase, context, flag);
        String s1 = actionrule.nextAction(context.dirt);
        int i = s1.indexOf("[]");
        if (i < 0) {
            stringbuffer.append(s1);
        } else {
            stringbuffer.append(s1.substring(0, i - 1));
            phrase.bindRule(actionrule);
            stringbuffer.append(phrase.rePhrase(grammar, context.dirt).toLowerCase());
            stringbuffer.append(s1.substring(i + 2));
        }
        if (context.dirt.containsKey("tellme")) {
            String s2 = context.dirt.getProperty("tellme");
            if (!s2.equals(s)) {
                if (s2.toLowerCase().startsWith("about "))
                    s2 = s2.substring(6).trim();
                context.query = s2;
            }
        }
        String s3 = stringbuffer.toString();
        context.logChat(phrase.stimulus, s3);
        return s3;
    }

    private static final boolean debug = false;
    public static Random rand = new Random();
    protected boolean isVerbose;
    protected Grammar grammar;
    protected ActionRule intro;
    protected Vector vRegx;
    protected RegexRule regx[];
    protected Vector vFuzz;
    protected FuzzyRule fuzz[];
    public Hashtable fuzzHash;
    protected Vector vActs;
    protected ActionRule acts[];
    public Hashtable actsHash;
    public URL ruleSetUrl;
    public static Perl5Compiler compiler;
    public static PatternMatcher matcher = new Perl5Matcher();
    public static Pattern patProp = null;
    public static Pattern patFuzz = null;

    static {
        compiler = new Perl5Compiler();
        try {
            patProp = compiler.compile("^\\s+(\\S+):\\s+(\\S+.*)$", 1);
            patFuzz = compiler.compile("^\\s+(\\S+)\\s+(\\S+.*)$", 1);
        } catch (MalformedPatternException malformedpatternexception) {
            System.err.println(malformedpatternexception.getMessage());
            malformedpatternexception.printStackTrace();
        }
    }
}
