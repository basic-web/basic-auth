package com.github.ququzone.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.MediaType;

/**
 * Gson utils.
 *
 * @author Yang XuePing
 */
public final class GsonUtil {
    public static final MediaType GSON_TYPE = MediaType.valueOf("application/json; charset=utf-8");
    public static final Gson DEFAULT_GSON = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss").excludeFieldsWithoutExposeAnnotation().create();
}
