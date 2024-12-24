package com.control.backend.auth.util;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public class JsonUtil {

	public static JSONObject jsonReplaceValue(String json, String field, String value) {
		try {
			final var jsonArray = json;
			final var jsonObject  = new JSONObject(jsonArray);
			jsonObject.put(field, value);
			return jsonObject;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}