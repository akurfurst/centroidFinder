package io.github.akurfurst.centroidFinder;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

public class VideoProcessorHelper {
    private final ImageGroupFinder groupFinder;

    public VideoProcessorHelper(ImageGroupFinder groupFinder) {
        this.groupFinder = groupFinder;
    }

    /**
     * 
     * Finds the centroid of the largest group at each frame in a video.
     * 
     * Returned in ASCENDING order
     * @param video 
     * @return a list of centroids in a video
     */
    public List<CentroidAtTime> processVideo(FFmpegFrameGrabber video){
        List<CentroidAtTime> result = new ArrayList<>();
        Java2DFrameConverter converter = new Java2DFrameConverter();
        Frame frame;
        video.start();
        while((frame = video.grab())!= null) {
            BufferedImage current = converter.convert(frame);
            List<Group> currentCentroids = groupFinder.findConnectedGroups(current);
            if(currentCentroids.isEmpty()){
                result.add(new Coordinate(-1, -1, 0), video.getTimestamp());
                continue;
            }
            result.add(new CentroidAtTime(currentCentroids.get(0), video.getTimestamp()));
        }
        video.close();
        return result;
    }
}
