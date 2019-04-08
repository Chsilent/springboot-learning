package com.walker.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * JSON Util
 */
public class XJsonTools {

    private static final Logger logger = LoggerFactory.getLogger(XJsonTools.class);

    private static ObjectMapper mapper;

    static {
        JsonFactory jf = new JsonFactory();
        jf.enable(Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER);
        jf.enable(Feature.ALLOW_COMMENTS);
        jf.enable(Feature.ALLOW_NON_NUMERIC_NUMBERS);
        jf.enable(Feature.ALLOW_SINGLE_QUOTES);
        jf.enable(Feature.ALLOW_UNQUOTED_CONTROL_CHARS);
        jf.enable(Feature.ALLOW_UNQUOTED_FIELD_NAMES);
        mapper = new ObjectMapper(jf);

        mapper.setSerializationInclusion(Include.NON_NULL);
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public static String toJsonString(Object value) {

        if (null == value) {
            return null;
        }

        try {
            return mapper.writeValueAsString(value);
        } catch (Exception e) {
            logger.error("Json write bean to string exception, ", e);
        }

        return null;
    }

    public static <T> T toBean(String content, TypeReference<T> clazz) {

        if (null == content || "".equals(content)) {
            return null;
        }
        try {
            return mapper.readValue(content, clazz);
        } catch (Exception e) {
            logger.error("Json string write to bean exception, ", e);
        }

        return null;
    }

    public static <T> T toBean(String content, Class<T> clazz) {

        if (null == content || "".equals(content)) {
            return null;
        }
        try {
            return mapper.readValue(content, clazz);
        } catch (Exception e) {
            logger.error("Json string write to bean exception, ", e);
        }

        return null;
    }

    public static <T> List<T> toList(String content, Class<T> clazz) {
        if (null == content || "".equals(content)) {
            return null;
        }
        try {
            return mapper.readValue(content, new TypeReference<List<T>>() {
            });
        } catch (Exception e) {
            logger.error("Json string write to object list exception, ", e);
        }

        return null;
    }
}
