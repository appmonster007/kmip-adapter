package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.BatchUndoCapability;

/**
 * TTLV deserializer for {@link BatchUndoCapability}.
 */
public class BatchUndoCapabilityTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<BatchUndoCapability,
        BatchUndoCapability.BatchUndoCapabilityBuilder> {

  /**
   * Constructs a new {@link BatchUndoCapabilityTtlvDeserializer}.
   */
  public BatchUndoCapabilityTtlvDeserializer() {
    super(BatchUndoCapability.kmipTag, BatchUndoCapability.encodingType);
  }

  @Override
  protected BatchUndoCapability.BatchUndoCapabilityBuilder createBuilder() {
    return BatchUndoCapability.builder();
  }

  @Override
  protected void setValue(BatchUndoCapability.BatchUndoCapabilityBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Boolean.class));
  }

  @Override
  protected BatchUndoCapability build(BatchUndoCapability.BatchUndoCapabilityBuilder builder) {
    return builder.build();
  }
}