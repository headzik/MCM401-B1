package at.fhooe.mcm.components.gis.drawingcontext;

import at.fhooe.mcm.components.gis.GeoObject;
import at.fhooe.mcm.components.gis.Matrix;
import at.fhooe.mcm.components.poi.POIObject;
import at.fhooe.mcm.interfaces.IDrawingContext;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DarkDrawingContext implements IDrawingContext {
    @Override
    public void drawObject(GeoObject _obj, Graphics _g
            , Matrix _matrix) {
        Polygon p = _matrix.multiply(_obj.getPoly());

        switch (_obj.getType()) {
            case 233:
                _g.setColor(Color.WHITE);
                _g.fillPolygon(p);
                _g.setColor(Color.WHITE);
                _g.drawPolygon(p);
                break;
            case 931:
                _g.setColor(Color.WHITE);
                _g.fillPolygon(p);
                _g.setColor(Color.WHITE);
                _g.drawPolygon(p);
                break;
            case 932:
                _g.setColor(Color.WHITE);
                _g.fillPolygon(p);
                _g.setColor(Color.WHITE);
                _g.drawPolygon(p);
                break;
            case 1101:
                _g.setColor(Color.WHITE);
                _g.fillPolygon(p);
                _g.setColor(Color.WHITE);
                _g.drawPolygon(p);
                break;
            case POI_TYPE:
                // POIObject
                Image img = ((POIObject) _obj).getImage();
                _g.drawImage(img, p.getBounds().x - img.getWidth(null), p.getBounds().y - img.getHeight(null), null, null);
                break;

            case LANDUSE_COMMERCIAL:
                _g.setColor(Color.WHITE);
                _g.fillPolygon(p);
                _g.setColor(Color.WHITE);
                _g.drawPolygon(p);
                break;
            case LANDUSE_FOREST:
                _g.setColor(Color.WHITE);
                _g.fillPolygon(p);
                _g.setColor(Color.WHITE);
                _g.drawPolygon(p);
                break;
            case LANDUSE_INDUSTRIAL:
                _g.setColor(Color.WHITE);
                _g.fillPolygon(p);
                _g.setColor(Color.WHITE);
                _g.drawPolygon(p);
                break;
            case LANDUSE_MEADOW:
                _g.setColor(Color.WHITE);
                _g.fillPolygon(p);
                _g.setColor(Color.WHITE);
                _g.drawPolygon(p);
                break;
            case LANDUSE_RESIDENTIAL:
                _g.setColor(Color.WHITE);
                _g.fillPolygon(p);
                _g.setColor(Color.WHITE);
                _g.drawPolygon(p);
                break;
            case NATURAL_GRASSLAND:
                _g.setColor(Color.WHITE);
                _g.fillPolygon(p);
                _g.setColor(Color.WHITE);
                _g.drawPolygon(p);
                break;
            case NATURAL_WATER:
                _g.setColor(Color.WHITE);
                _g.fillPolygon(p);
                _g.setColor(Color.WHITE);
                _g.drawPolygon(p);
                break;
            case NATURAL_WOOD:
                _g.setColor(Color.WHITE);
                _g.fillPolygon(p);
                _g.setColor(Color.WHITE);
                _g.drawPolygon(p);
                break;
            default:
                _g.setColor(Color.WHITE);
                _g.drawPolygon(p);
        }
        _g.dispose();
    }

    @Override
    public void drawBackground(BufferedImage img) {
        Graphics2D _g = img.createGraphics();
        _g.setColor(Color.DARK_GRAY);
        _g.fillRect(0, 0, img.getWidth(), img.getHeight());
        _g.dispose();
    }
}
