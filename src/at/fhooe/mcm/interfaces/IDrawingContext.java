package at.fhooe.mcm.interfaces;

import at.fhooe.mcm.components.gis.GeoObject;
import at.fhooe.mcm.components.gis.Matrix;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface IDrawingContext {

    int POI_TYPE = 999;

    // Landuse types
    int LANDUSE_RESIDENTIAL = 5001;
    int LANDUSE_INDUSTRIAL = 5002;
    int LANDUSE_COMMERCIAL = 5003;
    int LANDUSE_FOREST = 5004;
    int LANDUSE_MEADOW = 5006;

    // Natural types
    int NATURAL_GRASSLAND = 6001;
    int NATURAL_WOOD = 6002;
    int NATURAL_WATER = 6005;

    void drawObject(GeoObject _obj, Graphics _g, Matrix _matrix);

    void drawBackground(BufferedImage img);
}
