package org.robitron.elfchat;// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Cart.java

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;

// Referenced classes of package com.robitron.util:
//            Degree

public class Cart extends Properties
{

    protected synchronized void makeName(String s)
    {
        dir = s;
        Degree degree = new Degree(expiry);
        fileName = "c" + degree.toString();
        File file = null;
        String s1 = "";
        byte byte0 = 4;
        do
        {
            String s2 = Integer.toString(rand.nextInt());
            s2 = s2.substring(s2.length() - byte0, s2.length());
            file = new File(dir, fileName + s2);
        } while(file.exists());
        fileName = file.getName();
        try
        {
            FileOutputStream fileoutputstream = new FileOutputStream(file);
            save(fileoutputstream, "temp");
            fileoutputstream.close();
            return;
        }
        catch(IOException ioexception)
        {
            System.err.println(ioexception.getMessage());
            ioexception.printStackTrace();
            return;
        }
    }

    public Cart(String s, boolean flag)
    {
        expiry = new Date();
        render = 0;
        dir = s;
        if(flag)
        {
            makeName(s);
            put("timestamp", DateFormat.getDateTimeInstance().format(expiry));
            put("cart", fileName);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(expiry);
            calendar.add(10, 4);
            expiry = calendar.getTime();
            put("expires", DateFormat.getDateTimeInstance().format(expiry));
        }
    }

    public Cart(String s, String s1, String s2)
    {
        expiry = new Date();
        render = 0;
        dir = s;
        fileName = s1 + File.separator + s2;
        if(!loadFile())
            saveFile();
        Date date = new Date();
        put("timestamp", DateFormat.getDateTimeInstance().format(date));
        put("cart", fileName);
        put("expires", "never");
    }

    public Cart(String s, String s1, boolean flag)
    {
        expiry = new Date();
        render = 0;
        dir = s;
        fileName = s1;
        if(!loadFile())
        {
            System.err.println("Cart: can't find file " + s1);
            if(!flag)
                makeName(s);
        }
        put("cart", fileName);
        if(flag)
        {
            put("timestamp", DateFormat.getDateTimeInstance().format(expiry));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(expiry);
            calendar.add(10, 4);
            expiry = calendar.getTime();
            put("expires", DateFormat.getDateTimeInstance().format(expiry));
            return;
        }
        if(containsKey("expires") && !getProperty("expires").equals("never"))
            try
            {
                expiry = DateFormat.getDateTimeInstance().parse(getProperty("expires"));
                return;
            }
            catch(ParseException parseexception)
            {
                System.err.println(parseexception.getMessage());
                parseexception.printStackTrace();
                return;
            }
        else
            return;
    }

    protected void setRenderLevel()
    {
        if(containsKey("render"))
        {
            if(getProperty("render").equals("text"))
            {
                render = -10;
                return;
            }
            if(getProperty("render").equals("html2"))
            {
                render = 0;
                return;
            }
            if(getProperty("render").equals("vrml1"))
            {
                render = 10;
                return;
            }
            if(getProperty("render").equals("vrml2"))
                render = 11;
        }
    }

    public static Cart reOpen(String s, String s1)
    {
        Cart cart = new Cart(s, false);
        try
        {
            FileInputStream fileinputstream = new FileInputStream(new File(s, s1));
            cart.fileName = s1;
            cart.load(fileinputstream);
            fileinputstream.close();
            cart.setRenderLevel();
            if(cart.containsKey("expires") && !cart.getProperty("expires").equals("never"))
                cart.expiry = DateFormat.getDateTimeInstance().parse(cart.getProperty("expires"));
        }
        catch(ParseException parseexception)
        {
            System.err.println(parseexception.getMessage());
            parseexception.printStackTrace();
            cart = null;
        }
        catch(FileNotFoundException _ex)
        {
            cart = null;
        }
        catch(IOException ioexception)
        {
            System.err.println(ioexception.getMessage());
            ioexception.printStackTrace();
            cart = null;
        }
        return cart;
    }

    public static Vector getList(String s, String s1)
    {
        Vector vector = new Vector();
        if(!s1.equals(""))
            s = s.concat(File.separator + s1);
        try
        {
            File file = new File(s);
            String as[] = file.list();
            for(int i = 0; i < as.length; i++)
                if((!s1.equals("") || as[i].startsWith("c") && as[i].length() >= 12) && !as[i].endsWith(".jfd") && !as[i].endsWith(".acf") && !as[i].endsWith(".que"))
                    vector.addElement(as[i]);

        }
        catch(Exception exception)
        {
            System.err.println(exception);
            exception.printStackTrace();
        }
        finally
        {
            return vector;
        }
    }

    protected boolean loadFile()
    {
        if(dir == null || dir.equals(""))
            System.err.println("Cart: attempting to load file from a null directory");
        try
        {
            FileInputStream fileinputstream = new FileInputStream(new File(dir, fileName));
            load(fileinputstream);
            fileinputstream.close();
            setRenderLevel();
            return true;
        }
        catch(FileNotFoundException _ex)
        {
            return false;
        }
        catch(IOException ioexception)
        {
            System.err.println(ioexception.getMessage());
            ioexception.printStackTrace();
            return false;
        }
    }

    public void saveFile()
    {
        if(dir == null || dir.equals(""))
            System.err.println("Cart: attempting to save file to a null directory");
        try
        {
            FileOutputStream fileoutputstream = new FileOutputStream(new File(dir, fileName));
            save(fileoutputstream, getProperty("expires"));
            fileoutputstream.close();
            return;
        }
        catch(IOException ioexception)
        {
            System.err.println(ioexception.getMessage());
            ioexception.printStackTrace();
            return;
        }
    }

    public void delete()
    {
        try
        {
            File file = new File(dir, fileName + ".jfd");
            if(file.exists())
                file.delete();
            file = new File(dir, fileName + ".acf");
            if(file.exists())
                file.delete();
            file = new File(dir, fileName + ".que");
            if(file.exists())
                file.delete();
            file = new File(dir, fileName);
            if(file.exists())
            {
                file.delete();
                return;
            }
        }
        catch(Exception exception)
        {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        }
    }

    public void mingleProperties(Properties properties, boolean flag)
    {
        for(Enumeration enumeration = properties.propertyNames(); enumeration.hasMoreElements();)
        {
            String s = (String)enumeration.nextElement();
            if(!containsKey(s))
                put(s, properties.getProperty(s));
            else
            if(flag)
                put(s, properties.getProperty(s));
        }

    }

    public InputStream getInputStream(String s)
    {
        FileInputStream fileinputstream = null;
        try
        {
            fileinputstream = new FileInputStream(new File(dir, fileName + s));
        }
        catch(FileNotFoundException _ex)
        {
            fileinputstream = null;
        }
        catch(IOException ioexception)
        {
            System.err.println(ioexception.getMessage());
            ioexception.printStackTrace();
        }
        finally
        {
            return fileinputstream;
        }
    }

    public OutputStream getOutputStream(String s)
    {
        FileOutputStream fileoutputstream = null;
        try
        {
            fileoutputstream = new FileOutputStream(new File(dir, fileName + s));
        }
        catch(IOException ioexception)
        {
            System.err.println(ioexception.getMessage());
            ioexception.printStackTrace();
        }
        finally
        {
            return fileoutputstream;
        }
    }

    public static final String CART_FILE = "";
    public static final String CHAT_FILE = ".jfd";
    public static final String MATCH_FILE = ".acf";
    public static final String SEARCH_FILE = ".que";
    public static final int RENDER_TEXT = -10;
    public static final int RENDER_HTML2 = 0;
    public static final int RENDER_VRML1 = 10;
    public static final int RENDER_VRML2 = 11;
    protected static final String FILE_PREFIX = "c";
    protected static final int CART_NAME_LEN = 12;
    protected static final int EXP_HOURS = 4;
    protected static Random rand = new Random();
    public String dir;
    public String fileName;
    public Date expiry;
    public int render;

}
