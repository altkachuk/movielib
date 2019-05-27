package com.atproj.movielib.utils;

import com.atproj.movielib.model.Video;

import java.util.List;

public class VideoUtil {

    public static Video getYoutubeVideo(List<Video> videos) {
        if (videos == null) {
            return null;
        }

        for (Video video : videos) {
            if (video.getSite().equals("YouTube")) {
                return video;
            }
        }

        return null;
    }

    public static String buildYoutubePreview(String id) {
        return "http://img.youtube.com/vi/" + id + "/0.jpg";
    }

    public static String buildYoutubeUrl(String id) {
        return "http://www.youtube.com/watch?v=" + id;
    }

}
