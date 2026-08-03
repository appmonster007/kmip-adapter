package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.KeyValueByteString;

/**
 * TTLV deserializer for {@link KeyValueByteString}.
 */
public class KeyValueByteStringTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<KeyValueByteString,
        KeyValueByteString.KeyValueByteStringBuilder> {

  /**
   * Constructs a new {@link KeyValueByteStringTtlvDeserializer}.
   */
  public KeyValueByteStringTtlvDeserializer() {
    super(KeyValueByteString.kmipTag, KeyValueByteString.encodingType);
  }

  @Override
  protected KeyValueByteString.KeyValueByteStringBuilder createBuilder() {
    return KeyValueByteString.builder();
  }

  @Override
  protected void setValue(KeyValueByteString.KeyValueByteStringBuilder builder, byte[] tag,
                          byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
  }

  @Override
  protected KeyValueByteString build(KeyValueByteString.KeyValueByteStringBuilder builder) {
    return builder.build();
  }
}
