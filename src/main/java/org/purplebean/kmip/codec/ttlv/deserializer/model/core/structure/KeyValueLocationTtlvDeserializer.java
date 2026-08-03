package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.KeyValueLocationType;
import org.purplebean.kmip.model.core.structure.KeyValueLocation;
import org.purplebean.kmip.model.core.type.KeyValueLocationValue;

/**
 * TTLV deserializer for {@link KeyValueLocation}.
 */
public class KeyValueLocationTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<KeyValueLocation,
        KeyValueLocation.KeyValueLocationBuilder> {

  /**
   * Constructs a new {@link KeyValueLocationTtlvDeserializer}.
   */
  public KeyValueLocationTtlvDeserializer() {
    super(KeyValueLocation.kmipTag, KeyValueLocation.encodingType);
  }

  @Override
  protected KeyValueLocation.KeyValueLocationBuilder createBuilder() {
    return KeyValueLocation.builder();
  }

  @Override
  protected void setValue(KeyValueLocation.KeyValueLocationBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.KEY_VALUE_LOCATION_TYPE ->
          builder.keyValueLocationType(mapper.readValue(p, KeyValueLocationType.class));
      case KmipTag.Standard.KEY_VALUE_LOCATION_VALUE ->
          builder.keyValueLocationValue(mapper.readValue(p, KeyValueLocationValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected KeyValueLocation build(KeyValueLocation.KeyValueLocationBuilder builder) {
    return builder.build();
  }
}