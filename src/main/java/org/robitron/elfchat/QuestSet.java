package org.robitron.elfchat;// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QuestSet.java

import java.io.*;
import java.net.URL;
import java.util.Vector;

// Referenced classes of package com.robitron.phred:
//            Quest

public class QuestSet {

    public QuestSet() {
        imp = new Vector(1000);
    }

    public boolean loadStream(InputStream s) {
        try {
            Quest quest = null;
            DataInputStream datainputstream = new DataInputStream(s);
            String s1;
            while ((s1 = datainputstream.readLine()) != null)
                if (s1.startsWith("query:")) {
                    String s2 = s1.substring(6);
                    quest = new Quest();
                    quest.addName(s2.trim());
                    imp.addElement(quest);
                } else if (s1.startsWith("target:"))
                    quest.addQuery(s1.substring(7).trim());
                else if (s1.startsWith("signal:"))
                    quest.addSignal(s1.substring(7).trim());
            System.out.println(imp.size() + " Quests Loaded.");
            datainputstream.close();
            return true;
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }
        return false;
    }

    public boolean loadFile(URL url) {
        try {
            Quest quest = null;
            DataInputStream datainputstream = new DataInputStream(url.openStream());

            String s1;
            while ((s1 = datainputstream.readLine()) != null)
                if (s1.startsWith("query:")) {
                    String s2 = s1.substring(6);
                    quest = new Quest();
                    quest.addName(s2.trim());
                    imp.addElement(quest);
                } else if (s1.startsWith("target:"))
                    quest.addQuery(s1.substring(7).trim());
                else if (s1.startsWith("signal:"))
                    quest.addSignal(s1.substring(7).trim());
            System.out.println(imp.size() + " Quests Loaded.");
            datainputstream.close();
            return true;
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }
        return false;
    }

    public void DumpLog() {
        Object obj = null;
        for (int i = 0; i < imp.size(); i++) {
            Quest quest = (Quest) imp.elementAt(i);
            for (int j = 0; j < quest.query.size(); j++) {
                System.out.println(quest.query.elementAt(j));
                System.out.println(quest.signal.elementAt(j));
            }

        }

    }

    public Quest findQuest(String s) {
        boolean flag = false;
        Quest quest = null;
        for (int i = 0; i < imp.size() && !flag; i++) {
            quest = (Quest) imp.elementAt(i);
            for (int j = 1; j < quest.width && !flag; j++)
                if (quest.name.equalsIgnoreCase(s))
                    flag = true;

        }

        return quest;
    }

    public Vector imp;
}
