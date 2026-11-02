package io.github.akurfurst.centroidFinder;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber;

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
public class VideoProcessor {
    public static void main(String[] args){

        FrameGrabber inputVideo = null;
        int targetColor = 0;
        int threshold = 0;

        String inputVideoPath = args[0];
        String hexTargetColor = args[1];

        // Input video
        try {
            inputVideo = new FFmpegFrameGrabber(new File(inputVideoPath));
        } catch (Exception e) {
            System.err.println("Error loading video: " + inputVideoPath);
            e.printStackTrace();
            return;
        }

        // Target Color
        try {
            targetColor = Integer.parseInt(hexTargetColor, 16);
        } catch (NumberFormatException e) {
            System.err.println("Invalid hex target color. Please provide a color in RRGGBB format.");
            return;
        }

        // Threshold
        try {
            threshold = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.err.println("Threshold must be an integer.");
            return;
        }

        // Create the binarizer
        ColorDistanceFinder distanceFinder = new EuclideanColorDistance();
        ImageBinarizer binarizer = new DistanceImageBinarizer(distanceFinder, targetColor, threshold);

        // Create the group finder
        ImageGroupFinder groupFinder = new BinarizingImageGroupFinder(binarizer, new DfsBinaryGroupFinder());

        // Create the video processor
        VideoProcessorHelper helper = new VideoProcessorHelper(groupFinder);

        // Get the largest centroids
        List<CentroidAtTime> CentroidAtTimes = helper.processVideo(inputVideo);

        // Create CSV named "movement.csv"
        try (PrintWriter writer = new PrintWriter("movement.csv")) {
            for (CentroidAtTime centroid : CentroidAtTimes) {
                writer.println(centroid.toCsvRow());
            }
            System.out.println("Video summary saved as movement.csv");
        } catch (Exception e) {
            System.err.println("Error writing movement.csv");
            e.printStackTrace();
        }
    }
}
