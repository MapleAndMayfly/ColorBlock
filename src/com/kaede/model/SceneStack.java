package com.kaede.model;

import com.kaede.controller.Logger;

import java.util.ArrayList;

public class SceneStack
{
    public static boolean isScnChanged = false;

    public static enum scene
    {
        TITLE,
        POPUP_EXIT
    }
    private static ArrayList<scene> scnArr = new ArrayList<>();

    public static void init()
    {
        Logger.log("@ScenStack: SceneStack is ready.");
        changeScn(scene.TITLE);
    }

    public static scene getCurScn() { return scnArr.getLast(); }

    /**
     * 未使用 - not used
    public static scene getLastScn()
    {
        int last = scnArr.size() - 2;

        if (last < 0)
        {
            return null;
        }
        else
        {
            return scnArr.get(last);
        }
    }
    */

    private static boolean addScn(scene newScn)
    {
        Logger.log("@SceneStack: Current scene changed to [" + newScn + "].");
        return scnArr.add(newScn);
    }

    private static boolean rmvScn(scene outScn)
    {
        if (outScn != null)
        {
            Logger.log("@ScenStack: Current scene back to [" + outScn + "].");
            return scnArr.remove(outScn);
        }
        else
        {
            Logger.error("@SceneStack", new IllegalArgumentException("No scene left to be removed!"));
            return false;
        }
    }

    private static void printScnStack()
    {
        StringBuilder scnArrStr = new StringBuilder();
        for (scene s : scnArr)
        {
            scnArrStr.append(" | ").append(s);
        }
        Logger.log("@SceneStack: Scene stack:" + scnArrStr.toString());

    }

    public static void changeScn(scene to)
    {
        addScn(to);
        isScnChanged = true;
        printScnStack();
    }

    public static void exitScn()
    {
        rmvScn(getCurScn());
        isScnChanged = true;
        printScnStack();
    }
}
