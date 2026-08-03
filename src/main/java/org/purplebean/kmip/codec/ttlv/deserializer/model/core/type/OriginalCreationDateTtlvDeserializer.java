package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.OriginalCreationDate;

/**
 * TTLV deserializer for {@link OriginalCreationDate}.
 */
public class OriginalCreationDateTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<OriginalCreationDate,
        OriginalCreationDate.OriginalCreationDateBuilder> {

  /**
   * Constructs a new {@link OriginalCreationDateTtlvDeserializer}.
   */
  public OriginalCreationDateTtlvDeserializer() {
    super(OriginalCreationDate.kmipTag, OriginalCreationDate.encodingType);
  }

  @Override
  protected OriginalCreationDate.OriginalCreationDateBuilder createBuilder() {
    return OriginalCreationDate.builder();
  }

  @Override
  protected void setValue(OriginalCreationDate.OriginalCreationDateBuilder builder, byte[] tag,
                          byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, OffsetDateTime.class));
  }

  @Override
  protected OriginalCreationDate build(OriginalCreationDate.OriginalCreationDateBuilder builder) {
    return builder.build();
  }
}