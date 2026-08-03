package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.FixedFieldLength;

/**
 * TTLV deserializer for {@link FixedFieldLength}.
 */
public class FixedFieldLengthTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<FixedFieldLength,
        FixedFieldLength.FixedFieldLengthBuilder> {

  /**
   * Constructs a new {@link FixedFieldLengthTtlvDeserializer}.
   */
  public FixedFieldLengthTtlvDeserializer() {
    super(FixedFieldLength.kmipTag, FixedFieldLength.encodingType);
  }

  @Override
  protected FixedFieldLength.FixedFieldLengthBuilder createBuilder() {
    return FixedFieldLength.builder();
  }

  @Override
  protected void setValue(FixedFieldLength.FixedFieldLengthBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Integer.class));
  }

  @Override
  protected FixedFieldLength build(FixedFieldLength.FixedFieldLengthBuilder builder) {
    return builder.build();
  }
}
