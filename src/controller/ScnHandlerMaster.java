package controller;

import view.MainWindow;

interface SceneHandler
{
    public void handle(MainWindow gp);
}

public class ScnHandlerMaster
{
    /**
     * 处理游戏各场景 - to handle each scene of the game
     * 
     * @param gp 主界面 - main interface
     */
    public void handle(MainWindow gp)
    {
        switch (gp.curScene)
        {
            case MainWindow.scene.TITLE:
                if (gp.getKeyH().EscDown)   // 退出游戏 - Exit the game
                {
                    // 避免出现复数弹窗 - to avoid multiple popup
                    gp.curScene = MainWindow.scene.POPUP;
                }
                
                break;
            default:
                break;
        }
    }
}
