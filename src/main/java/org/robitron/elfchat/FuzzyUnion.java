package org.robitron.elfchat;// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FuzzyUnion.java

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

// Referenced classes of package com.robitron.phred:
//            ActionRule, Fred, FuzzySet, Rule, 
//            RuleStack, FuzzyRule

public class FuzzyUnion extends Vector
{

    public void addRule(FuzzyRule fuzzyrule)
    {
        for(int i = 0; i < fuzzyrule.size(); i++)
        {
            FuzzySet fuzzyset = (FuzzySet)fuzzyrule.elementAt(i);
            if(!contains(fuzzyset.rule))
            {
                addElement(fuzzyset.rule);
                fuzzyset.rule.sum = fuzzyset.prob;
            } else
            {
                fuzzyset.rule.sum += fuzzyset.prob;
            }
        }

    }

    public void addRule(ActionRule actionrule)
    {
        if(!contains(actionrule))
        {
            addElement(actionrule);
            actionrule.sum = 100;
            return;
        } else
        {
            actionrule.sum += 100;
            return;
        }
    }

    public void addRules(Vector vector)
    {
        for(int i = 0; i < vector.size(); i++)
        {
            ActionRule actionrule = (ActionRule)vector.elementAt(i);
            addRule(actionrule);
        }

    }

    public void cullProperties(Properties properties, boolean flag)
    {
        Vector vector = new Vector();
        for(Enumeration enumeration = elements(); enumeration.hasMoreElements();)
        {
            ActionRule actionrule = (ActionRule)enumeration.nextElement();
            if(!flag && actionrule.hasProperty("html") && actionrule.getProperty("html").equals("true"))
                vector.addElement(actionrule);
            if(actionrule.hasProperty("requires"))
            {
                String s = properties.getProperty(actionrule.getProperty("requires"));
                if(s == null || s.trim().equals(""))
                    vector.addElement(actionrule);
            }
            if(actionrule.hasProperty("equals"))
            {
                String s1 = properties.getProperty(actionrule.getProperty("equals"));
                if(actionrule.testProperty(s1, properties))
                    vector.addElement(actionrule);
            }
        }

        ActionRule actionrule1;
        for(Enumeration enumeration1 = vector.elements(); enumeration1.hasMoreElements(); removeElement(actionrule1))
            actionrule1 = (ActionRule)enumeration1.nextElement();

    }

    public void priorUse(RuleStack rulestack)
    {
        Vector vector = new Vector();
        Vector vector1 = new Vector();
        for(Enumeration enumeration = elements(); enumeration.hasMoreElements();)
        {
            ActionRule actionrule = (ActionRule)enumeration.nextElement();
            if(rulestack.hasRule(actionrule))
                if(!actionrule.hasProperty("repeat"))
                {
                    vector.addElement(actionrule);
                    rulestack.removeRule(actionrule);
                } else
                if(actionrule.getProperty("repeat").equals("false"))
                {
                    vector.addElement(actionrule);
                    vector1.addElement(actionrule);
                }
        }

        for(Enumeration enumeration1 = vector.elements(); enumeration1.hasMoreElements();)
        {
            ActionRule actionrule1 = (ActionRule)enumeration1.nextElement();
            removeElement(actionrule1);
            if(!vector1.contains(actionrule1))
                addElement(actionrule1);
        }

    }

    public void printTrace(RuleStack rulestack)
    {
        for(int i = 0; i < size(); i++)
        {
            ActionRule actionrule = (ActionRule)elementAt(i);
            String s = ((Rule) (actionrule)).name + " " + actionrule.sum + " " + actionrule.priority;
            if(rulestack.hasRule(actionrule))
                s = s + " (" + rulestack.search(actionrule) + ")";
            System.out.println("\t[ " + s + " ]");
        }

    }

    public ActionRule selectOne(ActionRule actionrule)
    {
        int i = 0;
        for(int j = 0; j < size(); j++)
        {
            ActionRule actionrule1 = (ActionRule)elementAt(j);
            i += actionrule1.sum;
        }

        double d = 0.0D;
        double ad[] = new double[size()];
        for(int k = 0; k < size(); k++)
        {
            ActionRule actionrule2 = (ActionRule)elementAt(k);
            ad[k] = Math.exp((double)actionrule2.sum / (double)i) * -2D;
            d += ad[k];
        }

        if(size() > 0)
        {
            double d1 = Fred.rand.nextDouble() * (d - ad[0]) + ad[0];
            for(int l = 0; l < size(); l++)
                if(d1 <= ad[l])
                    return (ActionRule)elementAt(l);

        }
        return actionrule;
    }

    public void sort()
    {
        quickSort(super.elementData, 0, size() - 1);
    }

    private boolean compare(Object aobj[], int i, int j)
    {
        ActionRule actionrule = (ActionRule)aobj[i];
        ActionRule actionrule1 = (ActionRule)aobj[j];
        return actionrule.priority > actionrule1.priority || actionrule.sum > actionrule1.sum;
    }

    private void swap(Object aobj[], int i, int j)
    {
        Object obj = aobj[i];
        aobj[i] = aobj[j];
        aobj[j] = obj;
    }

    private void quickSort(Object aobj[], int i, int j)
    {
        int k = i;
        int l = j;
        if(j > i)
        {
            int i1 = (i + j) / 2;
            while(k <= l) 
            {
                while(k < j && compare(aobj, k, i1)) 
                    k++;
                for(; l > i && compare(aobj, i1, l); l--);
                if(k <= l)
                {
                    swap(aobj, k, l);
                    k++;
                    l--;
                }
            }
            if(i < l)
                quickSort(aobj, i, l);
            if(k < j)
                quickSort(aobj, k, j);
        }
    }

    public FuzzyUnion()
    {
    }
}
