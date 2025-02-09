package controller.sceneHandler;

import controller.Logger;
import model.SceneStack;
import view.GamePanel;

public class ScnHandlerMaster implements SceneHandler
{
    TitleScnHandler titleH;

    public ScnHandlerMaster()
    {
        titleH = new TitleScnHandler();

        Logger.log("@ScnHandlerMaster: ScnHandlerMaster is ready.");
    }

    /**
     * 处理游戏各场景 - to handle each scene of the game
     * 
     * @param gp 主界面 - main interface
     */
    @Override
    public void handle(GamePanel gp)
    {
        if (SceneStack.isScnChanged) changeScn();

        switch (SceneStack.getCurScn())
        {
            case SceneStack.scene.TITLE:
                // 退出游戏 - Exit the game
                if (gp.getKeyH().EscDown)
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

    }
}
