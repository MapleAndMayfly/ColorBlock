package com.kaede.model;

import com.kaede.controller.Logger;
import com.kaede.controller.MetaReader;

import java.util.HashMap;
import java.util.Map;

public class MultiLang
{
    public static enum supports
    {
        zh_CN
    }
    public static Map<String, String> bundle = new HashMap<>();

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
            changeLocale("zh_CN");
        }
        
    }

    private static void getBundle(supports s)
    {
        MetaReader metaReader = new MetaReader();
        try
        {
            bundle = metaReader.readMeta(Global.langPath + Global.locale + ".cbmeta").get(Global.locale);
        }
        catch (Exception e)
        {
            Logger.error("@MultiLang", e);
        }
    }
}
