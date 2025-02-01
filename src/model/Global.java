package model;

import java.util.Map;

public class Global
{
    /**
     * 全局常量 - global consts
     */
    public static final String GAME_NAME = "ColorBlock";
    public static final String GAME_VER = "0.0 beta";
    public static final int FPS[] = { 60, 120, 240, 480, -1 };
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
    public static String scrProp = "3:2";
    public static String lang = "zh_cn";
    public static String bgi = "titleBgi";
    public static int fpsLevel = 1;
}
