package com.kaede.controller.sceneHandler;

import com.kaede.controller.Logger;
import com.kaede.model.SceneStack;
import com.kaede.view.GamePanel;

public class ScnHandlerMaster implements SceneHandler
{
    private GamePanel gPanel;
    private TitleScnHandler titleH;

    public ScnHandlerMaster(GamePanel gPanel)
    {
        this.gPanel = gPanel;
        titleH = new TitleScnHandler(gPanel);

        Logger.log("@ScnHandlerMaster: ScnHandlerMaster is ready.");
    }

    /**
     * 处理游戏各场景 - to handle each scene of the game
     * 
     * @param gp 主界面 - main interface
     */
    @Override
    public void handle()
    {
        if (SceneStack.isScnChanged) changeScn();

        switch (SceneStack.getCurScn())
        {
            case SceneStack.scene.TITLE:
                // 退出游戏 - Exit the game
                if (gPanel.getKeyH().EscDown)
                {
                    SceneStack.changeScn(SceneStack.scene.POPUP_EXIT);
                }

                break;
            default:
                break;
        }
    }

    private void changeScn()
    {
        SceneStack.isScnChanged = false;
        gPanel.removeAll();

        switch (SceneStack.getCurScn())
        {
            case SceneStack.scene.TITLE:
                titleH.onEnter();
                break;
            default:
                break;
        }
    }
}
