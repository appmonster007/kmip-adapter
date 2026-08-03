package org.purplebean.kmip.codec;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.text.StringEscapeUtils;
import org.purplebean.kmip.api.KmipInitializer;
import org.purplebean.kmip.codec.json.KmipJsonModule;
import org.purplebean.kmip.codec.ttlv.KmipTtlvModule;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.codec.xml.KmipXmlModule;

/**
 * Manager class for KMIP codec.
 */
public final class KmipCodecManager {
  private static TtlvMapper ttlvMapper;
  private static XmlMapper xmlMapper;
  private static JsonMapper jsonMapper;

  @Getter
  @Setter
  private static MapperType defaultType = MapperType.XML;

  static {
    KmipInitializer.initialize();
  }

  /**
   * Returns the shared {@link TtlvMapper} instance used for TTLV encoding.
   */
  public static TtlvMapper getTtlvMapper() {
    if (ttlvMapper == null) {
      ttlvMapper = createTtlvMapper();
    }
    return ttlvMapper;
  }

  /**
   * Returns the shared {@link XmlMapper} instance used for KMIP XML encoding.
   */
  public static XmlMapper getXmlMapper() {
    if (xmlMapper == null) {
      xmlMapper = createXmlMapper();
    }
    return xmlMapper;
  }

  /**
   * Returns the shared {@link JsonMapper} instance used for KMIP JSON encoding.
   */
  public static JsonMapper getJsonMapper() {
    if (jsonMapper == null) {
      jsonMapper = createJsonMapper();
    }
    return jsonMapper;
  }

  // Convenience method to serialize using default mapper
  /**
   * Serializes the given object using the mapper appropriate for its registered KMIP encoding.
   */
  public static <T> Object serialize(T obj) throws IOException {

    return switch (defaultType) {
//            TODO: Add TTLV support
//            case TTLV -> {}
      case XML -> StringEscapeUtils.escapeXml11(getXmlMapper().writeValueAsString(obj));
      case JSON -> StringEscapeUtils.escapeJson(getJsonMapper().writeValueAsString(obj));
      default -> throw new IllegalArgumentException("Unsupported mapper type: " + defaultType);
    };
  }

  // Convenience method to serialize using default mapper
  /**
   * Deserializes the given value into an instance of the given type using the mapper
   * appropriate for its registered KMIP encoding.
   */
  public static <T> T deserialize(Object value, Class<T> type) throws IOException {

    return switch (defaultType) {
//            TODO: Add TTLV support
//            case TTLV -> {}
      case XML -> getXmlMapper().readValue(StringEscapeUtils.unescapeXml((String) value), type);
      case JSON -> getJsonMapper().readValue(StringEscapeUtils.unescapeJson((String) value), type);
      default -> throw new IllegalArgumentException("Unsupported mapper type: " + defaultType);
    };
  }

  /**
   * Creates a new {@link JsonMapper} configured with the KMIP JSON module.
   */
  public static JsonMapper createJsonMapper() {
    JsonMapper jsonMapper = new JsonMapper();
    jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    jsonMapper.registerModule(new JavaTimeModule());
    jsonMapper.registerModule(new KmipJsonModule());
    return jsonMapper;
  }

  /**
   * Creates a new {@link XmlMapper} configured with the KMIP XML module.
   */
  public static XmlMapper createXmlMapper() {
    XmlMapper xmlMapper = new XmlMapper();
    xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    xmlMapper.registerModule(new JavaTimeModule());
    xmlMapper.registerModule(new KmipXmlModule());
    return xmlMapper;
  }

  /**
   * Creates a new {@link TtlvMapper} configured with the KMIP TTLV module.
   */
  public static TtlvMapper createTtlvMapper() {
    TtlvMapper ttlvMapper = new TtlvMapper();
    ttlvMapper.registerModule(new KmipTtlvModule());
    return ttlvMapper;
  }

  /**
   * Identifies which KMIP wire-format mapper (TTLV, XML, or JSON) to use.
   */
  public enum MapperType {
    TTLV,
    XML,
    JSON
  }
}
