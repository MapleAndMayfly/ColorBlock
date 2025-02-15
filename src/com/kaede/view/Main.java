package com.kaede.view;

import com.kaede.controller.Logger;
import com.kaede.controller.ResLoader;
import com.kaede.model.Global;
import com.kaede.model.MultiLang;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

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