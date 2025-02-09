package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import javax.imageio.ImageIO;

import model.Global;

public class ResLoader
{
    // 所有图像 - all the images
    private static Map<String, BufferedImage> imgMap = new HashMap<>();
    private MetaReader metaReader = new MetaReader();

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
                BufferedImage img = ImageIO.read(new File(imgPath));
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