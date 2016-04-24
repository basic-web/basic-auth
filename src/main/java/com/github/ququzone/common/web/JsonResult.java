package com.github.ququzone.common.web;

import com.google.gson.JsonObject;

/**
 * json result.
 *
 * @author Yang XuePing
 */
public class JsonResult {
    private JsonObject json;

    private JsonResult() {
        this.json = new JsonObject();
    }

    public static JsonResult newJson() {
        return new JsonResult();
    }

    public static JsonResult error(String error) {
        return newJson().add("error", error);
    }

    public JsonResult add(String property, String value) {
        json.addProperty(property, value);
        return this;
    }

    public String toString() {
        return this.json.toString();
    }
}
