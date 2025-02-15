package com.kaede.controller.sceneHandler;

import com.kaede.controller.Logger;
import com.kaede.view.GamePanel;

public class TitleScnHandler implements SceneHandler
{
    private GamePanel gPanel;

    TitleScnHandler(GamePanel gPanel)
    {
        this.gPanel = gPanel;

        Logger.log("@TitleScnHandler: TitleScnHandler is ready.");
    }

    public void onEnter()
    {

        Logger.log("@TitleScnHandler: Component initialization successfully.");
    }

    @Override
    public void handle()
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handle'");
    }
}
