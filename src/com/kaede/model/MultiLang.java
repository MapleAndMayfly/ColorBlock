package com.kaede.model;

import com.kaede.controller.Logger;
import com.kaede.controller.MetaReader;

import java.util.HashMap;
import java.util.Map;

public class MultiLang
{
    public static enum supports
    {
        zh,
        en
    }
    public static Map<String, String> bundle = new HashMap<>();

    public static void init()
    {
        changeLocale(Global.locale);

        Logger.log("@MultiLang: MultiLang is ready.");
    }

    public static String getText(String key)
    {
        String ret;
        if (bundle.containsKey(key))
        {
            ret = bundle.get(key);
        }
        else
        {
            ret = key;
            Logger.error("@MultiLang", new IllegalArgumentException("Can't get value from key: " + key));
        }
        return ret;
    }

    public static void changeLocale(String str)
    {
        try
        {
            supports s = supports.valueOf(str);
            Global.locale = str;
            getBundle(s);
        }
        catch (Exception e)
        {
            Logger.error("@MultiLang", e);
            changeLocale("zh");
        }
    }

    private static void getBundle(supports s)
    {
        try
        {
            MetaReader metaReader = new MetaReader();
            bundle = metaReader.readMeta(Global.langPath + Global.locale + ".cbmeta").get(Global.locale);
        }
        catch (Exception e)
        {
            Logger.error("@MultiLang", e);
        }
    }
}
