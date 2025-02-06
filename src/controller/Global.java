package controller;

import java.util.Map;

public class Global
{
    /**
     * 全局常量 - global consts
     */
    public static final String GAME_NAME = "ColorBlock";
    public static final String GAME_VER = "0.1 Prototype";
    public static final String dictPath = "assets/dict.cbmeta";
    public static final String logsPath = "logs/";
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
    public static String scrProp = "3:2";
    public static String lang = "zh_cn";
    public static String bgiName = "Background_Title";
    public static int fpsLevel = 1;
    public static int logIdx = 0;
}
