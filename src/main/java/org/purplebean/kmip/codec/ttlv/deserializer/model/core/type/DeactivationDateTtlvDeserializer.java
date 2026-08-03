package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.DeactivationDate;

/**
 * TTLV deserializer for {@link DeactivationDate}.
 */
public class DeactivationDateTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DeactivationDate,
        DeactivationDate.DeactivationDateBuilder> {

  /**
   * Constructs a new {@link DeactivationDateTtlvDeserializer}.
   */
  public DeactivationDateTtlvDeserializer() {
    super(DeactivationDate.kmipTag, DeactivationDate.encodingType);
  }

  @Override
  protected DeactivationDate.DeactivationDateBuilder createBuilder() {
    return DeactivationDate.builder();
  }

  @Override
  protected void setValue(DeactivationDate.DeactivationDateBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, OffsetDateTime.class));
  }

  @Override
  protected DeactivationDate build(DeactivationDate.DeactivationDateBuilder builder) {
    return builder.build();
  }
}
