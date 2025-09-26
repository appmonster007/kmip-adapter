package org.purpleBean.kmip.benchmark.util;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.purpleBean.kmip.codec.KmipCodecManager;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;

/**
 * Factory for creating configured mappers used in benchmarks.
 * Centralizes mapper configuration to avoid duplication across BenchmarkSubjects.
 */
public final class MapperFactory {

    private static final JsonMapper JSON_MAPPER = KmipCodecManager.getJsonMapper();
    private static final XmlMapper XML_MAPPER = KmipCodecManager.getXmlMapper();
    private static final TtlvMapper TTLV_MAPPER = KmipCodecManager.getTtlvMapper();

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
}
