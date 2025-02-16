package com.kaede.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import com.kaede.controller.MetaReader;

public class Global
{
    /**
     * 全局常量 - global consts
     */
    public static final String GAME_NAME = "ColorBlock";
    public static final String GAME_VER = "0.1 Prototype";
    public static final String dictPath = "assets/dict.cbmeta";
    public static final String langPath = "assets/lang/";
    public static final String logsPath = "logs/";
    public static final String confPath = ".conf";
    public static final int FPS[] = { 60, 120, 240, 480, -1 };
    public static final int PPS[] = { 120, 240, 240, 480, -1 };
    public static final Map<String, Double> SCR_PROPORTION = Map.of
    (
        "1:1",  1.0,
        "4:3",  4.0/3.0,
        "3:2",  3.0/2.0,
        "16:9", 16.0/9.0
    );

    /**
     * 全局变量 - global variables
     */
    public static String bgiName = "Background_Title";  // 用GamePanel.changeBgi以更改 - Use GamePanel.changeBgi to alter
    public static String locale = "zh";                 // 用MultiLang.changeLocale以更改 - Use MultiLang.changeLocale to alter
    public static String scrProp = "3:2";
    public static int fpsLevel = 1;
    public static int logIdx = 0;
    public static int masterVol = 100;

    /**
     * 读取变量 - read variables
     */
    public static void init()
    {
        try
        {
            File configFile = new File(confPath);
            if (configFile.exists())
            {
                Properties properties = new Properties();

                try (FileInputStream inputStream = new FileInputStream(confPath))
                {
                    properties.load(inputStream);

                    locale = properties.getProperty("locale", locale);
                    scrProp = properties.getProperty("scrPorp", scrProp);
                    fpsLevel = Integer.parseInt(properties.getProperty("fpsLevel", String.valueOf(fpsLevel)));
                    logIdx = Integer.parseInt(properties.getProperty("logIdx", String.valueOf(logIdx)));
                    masterVol = Integer.parseInt(properties.getProperty("masterVol", String.valueOf(masterVol)));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                configFile.createNewFile();
                save();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 保存变量
     */
    public static void save()
    {
        Properties properties = new Properties();

        properties.setProperty("locale", locale);
        properties.setProperty("scrPorp", scrProp);
        properties.setProperty("fpsLevel", String.valueOf(fpsLevel));
        properties.setProperty("logIdx", String.valueOf(logIdx));
        properties.setProperty("masterVol", String.valueOf(masterVol));

        try (FileOutputStream outputStream = new FileOutputStream(confPath))
        {
            properties.store(outputStream, "UserConfig");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
