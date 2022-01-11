package com.hcl.demo.helper;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	private static final Logger log = LogManager.getLogger(JsonUtil.class);

	private final static ObjectMapper mapper = new ObjectMapper();

	public static String convertObjectToJson(Object object) {
		String json = null;
		try {
			json = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			log.error("Error while converting object to json string :" + e.getMessage(), e);
		}
		return json;
	}

	public static <T> T convertJsonToObject(String jsonString, Class<T> object) {
		try {
			return mapper.readValue(jsonString, object);
		} catch (JsonParseException e) {
			log.error("Error while converting json to object :" + e.getMessage(), e);
		} catch (JsonMappingException e) {
			log.error("Error while converting json to object :" + e.getMessage(), e);
		} catch (IOException e) {
			log.error("Error while converting json to object :" + e.getMessage(), e);
		}
		return null;
	}
}