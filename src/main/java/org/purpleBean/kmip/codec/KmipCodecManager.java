package org.purpleBean.kmip.codec;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.text.StringEscapeUtils;
import org.purpleBean.kmip.codec.json.KmipJsonModule;
import org.purpleBean.kmip.codec.ttlv.KmipTtlvModule;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.xml.KmipXmlModule;

import java.io.IOException;


public final class KmipCodecManager {
    @Getter
    private static final TtlvMapper ttlvMapper = createTtlvMapper();
    @Getter
    private static final XmlMapper xmlMapper = createXmlMapper();
    @Getter
    private static final JsonMapper jsonMapper = createJsonMapper();

    @Getter
    @Setter
    private static MapperType defaultType = MapperType.XML;

    // Convenience method to serialize using default mapper
    public static <T> Object serialize(T obj) throws IOException {

        return switch (defaultType) {
//            TODO: Add TTLV support
//            case TTLV -> {}
            case XML -> StringEscapeUtils.escapeXml11(xmlMapper.writeValueAsString(obj));
            case JSON -> StringEscapeUtils.escapeJson(jsonMapper.writeValueAsString(obj));
            default -> throw new IllegalArgumentException("Unsupported mapper type: " + defaultType);
        };
    }

    // Convenience method to serialize using default mapper
    public static <T> T deserialize(Object value, Class<T> type) throws IOException {

        return switch (defaultType) {
//            TODO: Add TTLV support
//            case TTLV -> {}
            case XML -> xmlMapper.readValue(StringEscapeUtils.unescapeXml((String) value), type);
            case JSON -> jsonMapper.readValue(StringEscapeUtils.unescapeJson((String) value), type);
            default -> throw new IllegalArgumentException("Unsupported mapper type: " + defaultType);
        };
    }

    public static JsonMapper createJsonMapper() {
        JsonMapper jsonMapper = new JsonMapper();
        jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        jsonMapper.registerModule(new KmipJsonModule());
        jsonMapper.registerModule(new JavaTimeModule());
        return jsonMapper;
    }

    public static XmlMapper createXmlMapper() {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        xmlMapper.registerModule(new KmipXmlModule());
        xmlMapper.registerModule(new JavaTimeModule());
        return xmlMapper;
    }

    public static TtlvMapper createTtlvMapper() {
        TtlvMapper ttlvMapper = new TtlvMapper();
        ttlvMapper.registerModule(new KmipTtlvModule());
        return ttlvMapper;
    }

    public enum MapperType {
        TTLV,
        XML,
        JSON
    }
}
