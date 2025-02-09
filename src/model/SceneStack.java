package model;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import controller.Logger;

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

    private static boolean addScn(scene newScn)
    {
        Logger.log("@SceneStack: Current scene changed to [" + newScn + "].");
        return scnArr.add(newScn);
    }

    public static void changeScn(scene to)
    {
        addScn(to);

        StringBuilder scnArrStr = new StringBuilder();
        for (scene s : scnArr)
        {
            scnArrStr.append(" | ").append(s);
        }
        Logger.log("@SceneStack: Scene stack:" + scnArrStr.toString());
    }
}
