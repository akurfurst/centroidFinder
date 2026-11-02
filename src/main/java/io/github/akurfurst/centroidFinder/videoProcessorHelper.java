package io.github.akurfurst.centroidFinder;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

public class videoProcessorHelper {
    ImageGroupFinder groupFinder;

    public videoProcessorHelper(ImageGroupFinder groupFinder) {
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
    public List<LargestCentroid> processVideo(FFmpegFrameGrabber video){
        List<LargestCentroid> result = new ArrayList<>();
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
            result.add(new LargestCentroid(currentCentroids.get(0), video.getTimestamp()));
        }
        video.close();
        return result;
    }
}
