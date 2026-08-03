package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.BatchCount;

/**
 * TTLV deserializer for {@link BatchCount}.
 */
public class BatchCountTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<BatchCount, BatchCount.BatchCountBuilder> {

  /**
   * Constructs a new {@link BatchCountTtlvDeserializer}.
   */
  public BatchCountTtlvDeserializer() {
    super(BatchCount.kmipTag, BatchCount.encodingType);
  }

  @Override
  protected BatchCount.BatchCountBuilder createBuilder() {
    return BatchCount.builder();
  }

  @Override
  protected void setValue(BatchCount.BatchCountBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Integer.class));
  }

  @Override
  protected BatchCount build(BatchCount.BatchCountBuilder builder) {
    return builder.build();
  }
}
