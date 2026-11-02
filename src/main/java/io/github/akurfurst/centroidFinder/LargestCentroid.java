package io.github.akurfurst.centroidFinder;
/**
 * Represents the largest centroid at a particular timestamp in a video
 * 
 */
public record LargestCentroid(Coordinate centroid, long time ) {

}
