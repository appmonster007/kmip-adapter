package org.purpleBean.kmip.benchmark.util;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.purpleBean.kmip.codec.json.KmipJsonModule;
import org.purpleBean.kmip.codec.ttlv.KmipTtlvModule;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.xml.KmipXmlModule;

/**
 * Factory for creating configured mappers used in benchmarks.
 * Centralizes mapper configuration to avoid duplication across BenchmarkSubjects.
 */
public final class MapperFactory {

    private static final JsonMapper JSON_MAPPER = createJsonMapper();
    private static final XmlMapper XML_MAPPER = createXmlMapper();
    private static final TtlvMapper TTLV_MAPPER = createTtlvMapper();

    private MapperFactory() {
        // Utility class
    }

    /**
     * Get a configured JSON ObjectMapper for benchmarks.
     * Thread-safe as ObjectMapper instances are immutable after configuration.
     */
    public static JsonMapper getJsonMapper() {
        return JSON_MAPPER;
    }

    /**
     * Get a configured XML XmlMapper for benchmarks.
     * Thread-safe as XmlMapper instances are immutable after configuration.
     */
    public static XmlMapper getXmlMapper() {
        return XML_MAPPER;
    }

    /**
     * Get a configured TTLV TtlvMapper for benchmarks.
     * Thread-safe as TtlvMapper instances are immutable after configuration.
     */
    public static TtlvMapper getTtlvMapper() {
        return TTLV_MAPPER;
    }

    private static JsonMapper createJsonMapper() {
        JsonMapper mapper = new JsonMapper();
        mapper.findAndRegisterModules();
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new KmipJsonModule());
        return mapper;
    }

    private static XmlMapper createXmlMapper() {
        XmlMapper mapper = new XmlMapper();
        mapper.findAndRegisterModules();
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new KmipXmlModule());
        return mapper;
    }

    private static TtlvMapper createTtlvMapper() {
        TtlvMapper mapper = new TtlvMapper();
        mapper.registerModule(new KmipTtlvModule());
        return mapper;
    }
}
