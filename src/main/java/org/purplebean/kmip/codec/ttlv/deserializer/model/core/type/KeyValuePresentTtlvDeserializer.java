package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.KeyValuePresent;

/**
 * TTLV deserializer for {@link KeyValuePresent}.
 */
public class KeyValuePresentTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<KeyValuePresent, KeyValuePresent.KeyValuePresentBuilder> {

  /**
   * Constructs a new {@link KeyValuePresentTtlvDeserializer}.
   */
  public KeyValuePresentTtlvDeserializer() {
    super(KeyValuePresent.kmipTag, KeyValuePresent.encodingType);
  }

  @Override
  protected KeyValuePresent.KeyValuePresentBuilder createBuilder() {
    return KeyValuePresent.builder();
  }

  @Override
  protected void setValue(KeyValuePresent.KeyValuePresentBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Boolean.class));
  }

  @Override
  protected KeyValuePresent build(KeyValuePresent.KeyValuePresentBuilder builder) {
    return builder.build();
  }
}
