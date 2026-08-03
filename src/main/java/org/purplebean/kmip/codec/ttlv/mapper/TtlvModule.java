package org.purplebean.kmip.codec.ttlv.mapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Registry of custom TTLV serializers and deserializers keyed by type.
 */
public class TtlvModule {

  private final Map<Class<?>, TtlvSerializer<?>> serializers = new HashMap<>();
  private final Map<Class<?>, TtlvDeserializer<?>> deserializers = new HashMap<>();

  /**
   * Registers a TTLV serializer for the given type.
   */
  public <T> TtlvModule addSerializer(Class<T> type, TtlvSerializer<T> serializer) {
    serializers.put(type, serializer);
    return this;
  }

  /**
   * Registers a TTLV deserializer for the given type.
   */
  public <T> TtlvModule addDeserializer(Class<T> type, TtlvDeserializer<T> deserializer) {
    deserializers.put(type, deserializer);
    return this;
  }

  Map<Class<?>, TtlvSerializer<?>> getSerializers() {
    return serializers;
  }

  Map<Class<?>, TtlvDeserializer<?>> getDeserializers() {
    return deserializers;
  }
}
