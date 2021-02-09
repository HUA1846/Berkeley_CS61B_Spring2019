package bearmaps.proj2c.server.handler.impl;

import bearmaps.proj2c.AugmentedStreetMapGraph;
import bearmaps.proj2c.server.handler.APIRouteHandler;
import spark.Request;
import spark.Response;
import bearmaps.proj2c.utils.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static bearmaps.proj2c.utils.Constants.*;

/**
 * Handles requests from the web browser for map images. These images
 * will be rastered into one large image to be displayed to the user.
 * @author rahul, Josh Hug, _________
 */
public class RasterAPIHandler extends APIRouteHandler<Map<String, Double>, Map<String, Object>> {

    private double d0 = 0.00034332275390625;
    private double d1 = 0.000171661376953125;
    private double d2 = 0.0000858306884765625;
    private double d3 = 0.00004291534423828125;
    private double d4 = 0.000021457672119140625;
    private double d5 = 0.000010728836059570312;
    private double d6 = 0.000005364418029785156;
    private double d7 = 0.000002682209014892578;

    /**
     * Each raster request to the server will have the following parameters
     * as keys in the params map accessible by,
     * i.e., params.get("ullat") inside RasterAPIHandler.processRequest(). <br>
     * ullat : upper left corner latitude, <br> ullon : upper left corner longitude, <br>
     * lrlat : lower right corner latitude,<br> lrlon : lower right corner longitude <br>
     * w : user viewport window width in pixels,<br> h : user viewport height in pixels.
     **/
    private static final String[] REQUIRED_RASTER_REQUEST_PARAMS = {"ullat", "ullon", "lrlat",
            "lrlon", "w", "h"};

    /**
     * The result of rastering must be a map containing all of the
     * fields listed in the comments for RasterAPIHandler.processRequest.
     **/
    private static final String[] REQUIRED_RASTER_RESULT_PARAMS = {"render_grid", "raster_ul_lon",
            "raster_ul_lat", "raster_lr_lon", "raster_lr_lat", "depth", "query_success"};


    @Override
    protected Map<String, Double> parseRequestParams(Request request) {
        return getRequestParams(request, REQUIRED_RASTER_REQUEST_PARAMS);
    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     *
     *     The grid of images must obey the following properties, where image in the
     *     grid is referred to as a "tile".
     *     <ul>
     *         <li>The tiles collected must cover the most longitudinal distance per pixel
     *         (LonDPP) possible, while still covering less than or equal to the amount of
     *         longitudinal distance per pixel in the query box for the user viewport size. </li>
     *         <li>Contains all tiles that intersect the query bounding box that fulfill the
     *         above condition.</li>
     *         <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     *     </ul>
     *
     * @param requestParams Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     *
     * @param response : Not used by this function. You may ignore.
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image;
     *                    can also be interpreted as the length of the numbers in the image
     *                    string. <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     *                    forget to set this to true on success! <br>
     */

    // requestParams looks like this
    //{ lrlon=-122.22873634103394, ullon=-122.29143565896607, w=1461.0, h=698.0,
    // ullat=37.88365129135115, lrlat=37.86000070864885}

    /*
    The expected result from getMapRaster is:
    {raster_ul_lon=-122.24212646484375, depth=7, raster_lr_lon=-122.24006652832031, raster_lr_lat=37.87538940251607,
    render_grid=[[d7_x84_y28.png, d7_x85_y28.png, d7_x86_y28.png], [d7_x84_y29.png, d7_x85_y29.png, d7_x86_y29.png],
    [d7_x84_y30.png, d7_x85_y30.png, d7_x86_y30.png]], raster_ul_lat=37.87701580361881, query_success=true}
     */
    @Override
    public Map<String, Object> processRequest(Map<String, Double> requestParams, Response response) {
        double lrlon = requestParams.get("lrlon");
        double ullon = requestParams.get("ullon");
        double lrlat = requestParams.get("lrlat");
        double ullat = requestParams.get("ullat");
        double width = requestParams.get("w");
        int depth = calcDepth(lrlon, ullon, width);
        Map<String, Object> rasters = calcImages(depth, lrlon, ullon, ullat, lrlat);

        Map<String, Object> results = new HashMap<>();
        results.put("depth", depth);
        results.put("render_grid", rasters.get("returnImgs"));
        results.put("raster_ul_lon", rasters.get("raster_ul_lon"));
        results.put("raster_ul_lat", rasters.get("raster_ul_lat"));
        results.put("raster_lr_lon", rasters.get("raster_lr_lon"));
        results.put("raster_lr_lat", rasters.get("raster_lr_lat"));
        results.put("query_success", true);

        return results;
    }

    private Map<String, Object> calcImages(int depth, double lrlon, double ullon, double ullat, double lrlat) {
        double blockWidth = (ROOT_LRLON - ROOT_ULLON) / (Math.pow(2, depth));
        double blockHeight = (ROOT_ULLAT - ROOT_LRLAT) / (Math.pow(2, depth));
        int xStart = (int) Math.abs(Math.floor((ullon - ROOT_ULLON)/blockWidth));
        int yStart = (int) Math.abs(Math.floor((ROOT_ULLAT - ullat)/blockHeight));
        int xEnd = (int) Math.abs(Math.ceil((lrlon - ROOT_ULLON)/blockWidth));
        int yEnd = (int) Math.abs(Math.ceil((ROOT_ULLAT - lrlat)/blockHeight));
        double raster_ul_lon = ROOT_ULLON + xStart * blockWidth;
        double raster_ul_lat = ROOT_ULLAT - yStart * blockHeight;
        double raster_lr_lon = ROOT_ULLON + xEnd * blockWidth;
        double raster_lr_lat = ROOT_ULLAT - yEnd * blockHeight;
        Map<String, Object> rasters = new HashMap<>();
        String[][] returnImgs = new String[yEnd - yStart][xEnd - xStart];

        int x = xStart;
        int y = yStart;

        for(int row = 0; row < yEnd - yStart; row += 1) {
            for(int col = 0; col < xEnd - xStart; col += 1) {
                returnImgs[row][col] = "d" + depth + "_x" + x + "_y" + y + ".png";

                x += 1;
            }
            x = xStart;
            y += 1;
        }
        rasters.put("returnImgs", returnImgs);
        rasters.put("raster_ul_lon", raster_ul_lon);
        rasters.put("raster_ul_lat", raster_ul_lat);
        rasters.put("raster_lr_lon", raster_lr_lon);
        rasters.put("raster_lr_lat", raster_lr_lat);
        return rasters;
    }

    private int calcDepth(double lrlon, double ullon, double width) {
        double LonDPP = (lrlon - ullon) / width;
        if(d0 <= LonDPP) return 0;
        if(d1 <= LonDPP) return 1;
        if(d2 <= LonDPP) return 2;
        if(d3 <= LonDPP) return 3;
        if(d4 <= LonDPP) return 4;
        if(d5 <= LonDPP) return 5;
        if(d6 <= LonDPP) return 6;
        if(d7 <= LonDPP) return 7;
        if(LonDPP < d7) return 7;
        else return 0;

    }

    @Override
    protected Object buildJsonResponse(Map<String, Object> result) {
        boolean rasterSuccess = validateRasteredImgParams(result);

        if (rasterSuccess) {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            writeImagesToOutputStream(result, os);
            String encodedImage = Base64.getEncoder().encodeToString(os.toByteArray());
            result.put("b64_encoded_image_data", encodedImage);
        }
        return super.buildJsonResponse(result);
    }

    private Map<String, Object> queryFail() {
        Map<String, Object> results = new HashMap<>();
        results.put("render_grid", null);
        results.put("raster_ul_lon", 0);
        results.put("raster_ul_lat", 0);
        results.put("raster_lr_lon", 0);
        results.put("raster_lr_lat", 0);
        results.put("depth", 0);
        results.put("query_success", false);
        return results;
    }

    /**
     * Validates that Rasterer has returned a result that can be rendered.
     * @param rip : Parameters provided by the rasterer
     */
    private boolean validateRasteredImgParams(Map<String, Object> rip) {
        for (String p : REQUIRED_RASTER_RESULT_PARAMS) {
            if (!rip.containsKey(p)) {
                System.out.println("Your rastering result is missing the " + p + " field.");
                return false;
            }
        }
        if (rip.containsKey("query_success")) {
            boolean success = (boolean) rip.get("query_success");
            if (!success) {
                System.out.println("query_success was reported as a failure");
                return false;
            }
        }
        return true;
    }

    /**
     * Writes the images corresponding to rasteredImgParams to the output stream.
     * In Spring 2016, students had to do this on their own, but in 2017,
     * we made this into provided code since it was just a bit too low level.
     */
    private  void writeImagesToOutputStream(Map<String, Object> rasteredImageParams,
                                                  ByteArrayOutputStream os) {
        String[][] renderGrid = (String[][]) rasteredImageParams.get("render_grid");
        int numVertTiles = renderGrid.length;
        int numHorizTiles = renderGrid[0].length;

        BufferedImage img = new BufferedImage(numHorizTiles * Constants.TILE_SIZE,
                numVertTiles * Constants.TILE_SIZE, BufferedImage.TYPE_INT_RGB);
        Graphics graphic = img.getGraphics();
        int x = 0, y = 0;

        for (int r = 0; r < numVertTiles; r += 1) {
            for (int c = 0; c < numHorizTiles; c += 1) {
                graphic.drawImage(getImage(Constants.IMG_ROOT + renderGrid[r][c]), x, y, null);
                x += Constants.TILE_SIZE;
                if (x >= img.getWidth()) {
                    x = 0;
                    y += Constants.TILE_SIZE;
                }
            }
        }

        /* If there is a route, draw it. */
        double ullon = (double) rasteredImageParams.get("raster_ul_lon"); //tiles.get(0).ulp;
        double ullat = (double) rasteredImageParams.get("raster_ul_lat"); //tiles.get(0).ulp;
        double lrlon = (double) rasteredImageParams.get("raster_lr_lon"); //tiles.get(0).ulp;
        double lrlat = (double) rasteredImageParams.get("raster_lr_lat"); //tiles.get(0).ulp;

        final double wdpp = (lrlon - ullon) / img.getWidth();
        final double hdpp = (ullat - lrlat) / img.getHeight();
        AugmentedStreetMapGraph graph = SEMANTIC_STREET_GRAPH;
        List<Long> route = ROUTE_LIST;

        if (route != null && !route.isEmpty()) {
            Graphics2D g2d = (Graphics2D) graphic;
            g2d.setColor(Constants.ROUTE_STROKE_COLOR);
            g2d.setStroke(new BasicStroke(Constants.ROUTE_STROKE_WIDTH_PX,
                    BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            route.stream().reduce((v, w) -> {
                g2d.drawLine((int) ((graph.lon(v) - ullon) * (1 / wdpp)),
                        (int) ((ullat - graph.lat(v)) * (1 / hdpp)),
                        (int) ((graph.lon(w) - ullon) * (1 / wdpp)),
                        (int) ((ullat - graph.lat(w)) * (1 / hdpp)));
                return w;
            });
        }

        rasteredImageParams.put("raster_width", img.getWidth());
        rasteredImageParams.put("raster_height", img.getHeight());

        try {
            ImageIO.write(img, "png", os);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private BufferedImage getImage(String imgPath) {
        BufferedImage tileImg = null;
        if (tileImg == null) {
            try {
                File in = new File(imgPath);
                tileImg = ImageIO.read(in);
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return tileImg;
    }
}

/*
System.out.println("xStart: " + x);
        System.out.println("xEnd: " + xEnd);
        System.out.println((ullon - ROOT_ULLON)/blockWidth);
        System.out.println("yStart: " + y);
        System.out.println("yEnd: " + yEnd);
        System.out.println("depth: " + depth);
 */