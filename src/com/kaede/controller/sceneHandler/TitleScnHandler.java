package com.kaede.controller.sceneHandler;

import com.kaede.controller.Logger;
import com.kaede.model.Global;
import com.kaede.model.MultiLang;
import com.kaede.view.GamePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;

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
        gPanel.changeBgi(Global.bgiName);

        JLabel enterLabel = new JLabel(MultiLang.getText("text.title.press_to_start"));
        enterLabel.setFont(new Font(null, Font.BOLD, 32));
        enterLabel.setForeground(Color.WHITE);
        enterLabel.setHorizontalAlignment(JLabel.CENTER);
        enterLabel.setOpaque(false);

        gPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.weightx = gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 0, gPanel.getHeight() * 1/5, 0);
        gPanel.add(enterLabel, gbc);
        gPanel.revalidate();

        Logger.log("@TitleScnHandler: Component initialization successfully.");
    }

    @Override
    public void handle()
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handle'");
    }
}
