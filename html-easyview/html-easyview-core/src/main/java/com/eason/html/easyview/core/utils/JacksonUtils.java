package com.eason.html.easyview.core.utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtils {

	public static class ListMapString extends TypeReference<List<Map<String, String>>> {
	};

	public static class MapObject extends TypeReference<Map<String, Object>> {
	};

	private static ObjectMapper objectMapper = new ObjectMapper();

	static {
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
	}

	private JacksonUtils() {
		super();
	}

	public static byte[] toJsonBytes(Object value) {
		try {
			return objectMapper.writeValueAsBytes(value);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static String toJsonString(Object value) {
		try {
			return objectMapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static <T> T parseObject(byte[] jsonBytes, Class<T> clazz) {
		try {
			return objectMapper.readValue(jsonBytes, clazz);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static <T> T parseObject(String jsonValue, Class<T> clazz) {
		try {
			return objectMapper.readValue(jsonValue, clazz);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static Map<String, Object> parseObject(String jsonValue) {
		return parseObject(jsonValue, new MapObject());
	}

	public static <T> T map2Bean(Map<String, Object> fromValue, Class<T> toValueType) {
		return objectMapper.convertValue(fromValue, toValueType);
	}

	public static <T> T parseObject(String jsonValue, TypeReference<T> ref) {
		try {
			return objectMapper.readValue(jsonValue, ref);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static JsonNode readTree(byte[] jsonBytes) {
		try {
			return objectMapper.readTree(jsonBytes);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static JsonNode readTree(String content) {
		try {
			return objectMapper.readTree(content);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static Map<String, Object> parseObject(byte[] jsonBytes) {
		return parseObject(jsonBytes, new MapObject());
	}

	public static <T> T parseObject(byte[] jsonBytes, TypeReference<T> ref) {
		try {
			return objectMapper.readValue(jsonBytes, ref);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static ObjectMapper getMapper() {
		return objectMapper;
	}

}
