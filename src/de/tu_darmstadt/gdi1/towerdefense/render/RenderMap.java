package de.tu_darmstadt.gdi1.towerdefense.render;

import java.awt.Point;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

/**
 * <h1>RenderMap</h1><br>
 * <li>looks up the piece in the HashMap and get its Image</li>
 *
 * @author Michael Schlittenbauer
 */
public class RenderMap {

    private Image image; //the image of the piece
    private int x;    //the x Position
    private int y; //the y Position

    /**
     * Constructor of the Class
     *
     * @param image the Image to be rendered
     * @param x     the x Position
     * @param y     the y Position
     */
    private RenderMap(Image image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;

    }

    /**
     * Get the Image of the RenderMap Object
     *
     * @return the image of the RenderMap Object
     */
    public Image getImage() {
        return image;
    }

    /**
     * Get x of the RenderMap Object
     *
     * @return x of the RenderMap Object
     */
    public int getX() {
        return x;
    }

    /**
     * Get y of the RenderMap Object
     *
     * @return y of the RenderMap Object
     */
    public int getY() {
        return y;
    }

    /**
     * looks up the given piece in the HashMap, get its Image
     * and its Coordinates
     *
     * @param Map   the HashMap with all Pieces and Positions
     * @param sheet the SpriteSheet with all subImages
     * @param iName the Name searching for
     * @param scale the scale of the field
     * @return RenderMap with Image and Coordinates
     */
    public static RenderMap render(Map<String, Point> Map, SpriteSheet sheet, String iName, float scale) {
        int x = (int) Map.get(iName).getX();
        int y = (int) Map.get(iName).getY();

        if (iName.contains("border")) {
            if (iName.contains("border_ne")) {
                return new RenderMap(sheet.getSubImage(0, 6), (int) (x * scale), (int) (y * scale));
            } else if (iName.contains("border_se")) {
                return new RenderMap(sheet.getSubImage(1, 5), (int) (x * scale), (int) (y * scale));
            } else if (iName.contains("border_sw")) {
                return new RenderMap(sheet.getSubImage(1, 5).getFlippedCopy(true, false), (int) (x * scale), (int) (y * scale));
            } else if (iName.contains("border_nw")) {
                return new RenderMap(sheet.getSubImage(0, 6).getFlippedCopy(true, false), (int) (x * scale), (int) (y * scale));
            } else if (iName.contains("border_e") || iName.contains("border_w")) {
                return new RenderMap(sheet.getSubImage(2, 5), (int) (x * scale), (int) (y * scale));
            } else
                return new RenderMap(sheet.getSubImage(0, 5), (int) (x * scale), (int) (y * scale));

        } else if (iName.contains("tower")) {
            return new RenderMap(sheet.getSubImage(0, 0), (int) (x * scale), (int) (y * scale));

        } else if (iName.contains("field")) {
            return new RenderMap(sheet.getSubImage(0, 0), (int) (x * scale), (int) (y * scale));

        } else if (iName.contains("flower")) {
            return new RenderMap(sheet.getSubImage(1, 0), (int) (x * scale), (int) (y * scale));

        } else if (iName.contains("tree")) {
            return new RenderMap(sheet.getSubImage(3, 5), (int) (x * scale), (int) (y * scale));

        } else if (iName.contains("finish_north")) {
            return new RenderMap(sheet.getSubImage(2, 4), (int) (x * scale), (int) (y * scale));

        } else if (iName.contains("finish_south")) {
            return new RenderMap(sheet.getSubImage(3, 4), (int) (x * scale), (int) (y * scale));

        } else if (iName.contains("finish_east")) {
            return new RenderMap(sheet.getSubImage(0, 4), (int) (x * scale), (int) (y * scale));

        } else if (iName.contains("finish_west")) {
            return new RenderMap(sheet.getSubImage(1, 4), (int) (x * scale), (int) (y * scale));

        } else if (iName.contains("start_north")) {
            return new RenderMap(sheet.getSubImage(2, 2), (int) (x * scale), (int) (y * scale));

        } else if (iName.contains("start_south")) {
            return new RenderMap(sheet.getSubImage(3, 2), (int) (x * scale), (int) (y * scale));

        } else if (iName.contains("start_east")) {
            return new RenderMap(sheet.getSubImage(1, 2), (int) (x * scale), (int) (y * scale));

        } else if (iName.contains("start_west")) {
            return new RenderMap(sheet.getSubImage(0, 2), (int) (x * scale), (int) (y * scale));

        } else if (iName.contains("cws")) {
            return new RenderMap(sheet.getSubImage(3, 1), (int) (x * scale), (int) (y * scale));

        } else if (iName.contains("cwn")) {
            return new RenderMap(sheet.getSubImage(0, 1), (int) (x * scale), (int) (y * scale));

        } else if (iName.contains("cen")) {
            return new RenderMap(sheet.getSubImage(1, 1), (int) (x * scale), (int) (y * scale));

        } else if (iName.contains("ces")) {
            return new RenderMap(sheet.getSubImage(2, 1), (int) (x * scale), (int) (y * scale));

        } else if (iName.contains("swe")) {
            return new RenderMap(sheet.getSubImage(3, 0), (int) (x * scale), (int) (y * scale));

        } else if (iName.contains("sns")) {
            return new RenderMap(sheet.getSubImage(2, 0), (int) (x * scale), (int) (y * scale));

        } else if (iName.contains("tcs")) {
            return new RenderMap(sheet.getSubImage(3, 3), (int) (x * scale), (int) (y * scale));

        } else if (iName.contains("tce")) {
            return new RenderMap(sheet.getSubImage(2, 3), (int) (x * scale), (int) (y * scale));

        } else if (iName.contains("tcw")) {
            return new RenderMap(sheet.getSubImage(1, 3), (int) (x * scale), (int) (y * scale));

        } else if (iName.contains("tcn")) {
            return new RenderMap(sheet.getSubImage(0, 3), (int) (x * scale), (int) (y * scale));

        } else return null;
    }
}
