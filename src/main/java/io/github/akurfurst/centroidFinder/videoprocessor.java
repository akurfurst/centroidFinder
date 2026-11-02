package io.github.akurfurst.centroidFinder;

/**
 * The Video Processor Application.
 * 
 * This application takes three command-line arguments:
 * 1. The path to an input video file (for example, "video.mp4").
 * 2. A target hex color in the format RRGGBB (for example, "FF0000" for red).
 * 3. An integer threshold for binarization.
 * 
 * The application performs the following steps:
 * 
 * 1. Loads the input video
 * 2. Parses the target color from the hex string into a 24-bit integer.
 * 3. Binarizes each frame by comparing each pixel's Euclidean color distance to the target color.
 *    A pixel is marked white (1) if its distance is less than the threshold; otherwise, it is marked black (0).
 * 4. Finds the largest connected group of white pixels in each frame.
 *    Pixels are connected vertically and horizontally (not diagonally).
 *    For each group, the size (number of pixels) and the centroid (calculated using integer division) are computed.
 * 5. Writes a CSV file named "movement.csv" containing one row per largest group in the format "time,x,y".
 *    Coordinates follow the convention: (x:0, y:0) is the top-left, with x increasing to the right and y increasing downward.
 *    Time is tracked in seconds.
 */
public class videoprocessor {
    public static void main(String[] args){

    }
}
