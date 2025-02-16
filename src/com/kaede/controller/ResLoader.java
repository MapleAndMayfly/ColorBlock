package com.kaede.controller;

import com.kaede.model.Global;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ResLoader
{
    // 所有图像 - all the images
    private static Map<String, BufferedImage> imgMap = new HashMap<>();

    public static BufferedImage getImg(String key)
    {
        if (imgMap.containsKey(key))
        {
            return imgMap.get(key);
        }
        else
        {
            throw new IllegalArgumentException("Can't get value from key: " + key);
        }
    }

    /**
     * 重载所有资源 - reload all the resources
     */
    public void reload()
    {
        imgMap.clear();

        Map<String, Map<String, String>> dict = null;
        try
        {
            MetaReader metaReader = new MetaReader();
            dict = metaReader.readMeta(Global.dictPath);
        }
        catch (Exception e)
        {
            Logger.error("@ResLoader", e);
        }

        if (dict != null) {
            dict.forEach((key, valueMap) ->
            {
                switch (key) {
                    case "image":
                        loadImg(valueMap);
                        break;

                    default:
                        break;
                }
            });
        }
    }

    private void loadImg(Map<String, String> target)
    {
        imgMap.put("null", new BufferedImage(10, 10, BufferedImage.TYPE_BYTE_GRAY));

        target.forEach((key, value) ->
        {
            try
            {
                String imgPath = value + key + ".png";
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream(imgPath);
                BufferedImage img = ImageIO.read(inputStream);
                if (img != null)
                {
                    imgMap.put(key, img);
                }
                else
                {
                   Logger.log("@ResLoader: Failed to load image: " + imgPath);
                }
            }
            catch (IOException e)
            {
                Logger.log("@ResLoader: Error reading file: " + value + key + ".png");
                Logger.error("@ResLoader", e);
            }
        });
    }
}