package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ResLoader
{
    // 所有图像的路径 - path of all the images
    private static final Map<String, String> imgPath = Map.of
    (
        "titleBgi", "assets\\textures\\愛の流れ星.png"
    );

    // 所有图像 - all the images
    private static HashMap<String, BufferedImage> imgMap = new HashMap<>();


    public static BufferedImage getImg(String key) { return imgMap.get(key); }


    /**
     * 重载所有资源 - reload all the resources
     */
    public void reload()
    {
        imgMap.clear();

        imgPath.forEach((key, value) -> 
        {
            try
            {
                BufferedImage img = ImageIO.read(new File(value));
                imgMap.put(key, img);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        });
    }
}
