package com.borrowbuddy.app;

import com.cloudinary.android.MediaManager;

import java.util.HashMap;
import java.util.Map;

public class CloudinaryManager {

    private static boolean initialized = false;

    public static void init(android.content.Context context) {

        if (initialized) return;

        Map<String, String> config = new HashMap<>();

        config.put("cloud_name", "j0dlqioh");

        MediaManager.init(context, config);

        initialized = true;
    }
}