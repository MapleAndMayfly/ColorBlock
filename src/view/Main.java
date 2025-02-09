package view;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import controller.Logger;
import controller.ResLoader;
import model.Global;
import model.MultiLang;

public class Main
{
    public static void main(String[] args)
    {
        // TODO: initialize attributes of Global

        Logger.init();

        MultiLang.changeLocale(Global.locale);

        MainWindow mainWindow = new MainWindow();

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        gd.setFullScreenWindow(mainWindow);

        ResLoader resLoader = new ResLoader();
        resLoader.reload();

        mainWindow.onSizeChange();
        mainWindow._gameStart();
        mainWindow.setVisible(true);
    }
}