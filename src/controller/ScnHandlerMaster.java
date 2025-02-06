package controller;

import java.util.NoSuchElementException;

import view.GamePanel;

interface SceneHandler
{
    public void handle(GamePanel gp);
}

public class ScnHandlerMaster
{
    /**
     * 处理游戏各场景 - to handle each scene of the game
     * 
     * @param gp 主界面 - main interface
     */
    public void handle(GamePanel gp)
    {
        try
        {
            switch (gp.curScene.getLast())
            {
                    case GamePanel.scene.TITLE:
                    // 退出游戏 - Exit the game
                    if (gp.getKeyH().EscDown)
                    {
                        // 避免出现复数弹窗 - to avoid multiple popup
                        gp.curScene.add(GamePanel.scene.POPUP);
                    }
                    
                    break;
                default:
                    break;
            }
        }
        catch (NoSuchElementException e)
        {
            // TODO: handle exception
        }
    }
}
