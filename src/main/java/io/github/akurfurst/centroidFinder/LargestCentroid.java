package io.github.akurfurst.centroidFinder;
/**
 * Represents the largest centroid at a particular timestamp in a video
 * 
 */
public record LargestCentroid(Coordinate centroid, long time ) {

    /**
     * Returns a string representing this centroid in comma-separated values (CSV) format.
     * The format is "time,x,y"
     * 
     * @return a CSV row string representing the time and centroid coordinates
     */
    public String toCsvRow() {
        return String.format("%d,%d,%d", this.time(), this.centroid().x(), this.centroid().y());
    }
}
