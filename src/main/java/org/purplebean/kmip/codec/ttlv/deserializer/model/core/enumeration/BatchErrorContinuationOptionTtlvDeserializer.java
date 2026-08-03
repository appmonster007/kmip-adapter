package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.BatchErrorContinuationOption;

/**
 * TTLV deserializer for {@link BatchErrorContinuationOption}.
 */
public class BatchErrorContinuationOptionTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<BatchErrorContinuationOption,
        BatchErrorContinuationOption.BatchErrorContinuationOptionBuilder> {

  /**
   * Constructs a new {@link BatchErrorContinuationOptionTtlvDeserializer}.
   */
  public BatchErrorContinuationOptionTtlvDeserializer() {
    super(BatchErrorContinuationOption.kmipTag, BatchErrorContinuationOption.encodingType);
  }

  @Override
  protected BatchErrorContinuationOption.BatchErrorContinuationOptionBuilder createBuilder() {
    return BatchErrorContinuationOption.builder();
  }

  @Override
  protected void setValue(BatchErrorContinuationOption.BatchErrorContinuationOptionBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(BatchErrorContinuationOption.fromValue(value));
  }

  @Override
  protected BatchErrorContinuationOption build(
      BatchErrorContinuationOption.BatchErrorContinuationOptionBuilder builder) {
    return builder.build();
  }
}
