package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.KeyValueLocationValue;

/**
 * TTLV deserializer for {@link KeyValueLocationValue}.
 */
public class KeyValueLocationValueTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<KeyValueLocationValue,
        KeyValueLocationValue.KeyValueLocationValueBuilder> {

  /**
   * Constructs a new {@link KeyValueLocationValueTtlvDeserializer}.
   */
  public KeyValueLocationValueTtlvDeserializer() {
    super(KeyValueLocationValue.kmipTag, KeyValueLocationValue.encodingType);
  }

  @Override
  protected KeyValueLocationValue.KeyValueLocationValueBuilder createBuilder() {
    return KeyValueLocationValue.builder();
  }

  @Override
  protected void setValue(KeyValueLocationValue.KeyValueLocationValueBuilder builder, byte[] tag,
                          byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected KeyValueLocationValue build(
      KeyValueLocationValue.KeyValueLocationValueBuilder builder) {
    return builder.build();
  }
}
