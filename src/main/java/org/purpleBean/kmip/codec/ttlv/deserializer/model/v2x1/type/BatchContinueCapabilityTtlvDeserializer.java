package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2x1.type.BatchContinueCapability;

public class BatchContinueCapabilityTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<BatchContinueCapability,
        BatchContinueCapability.BatchContinueCapabilityBuilder> {

  public BatchContinueCapabilityTtlvDeserializer() {
    super(BatchContinueCapability.kmipTag, BatchContinueCapability.encodingType);
  }

  @Override
  protected BatchContinueCapability.BatchContinueCapabilityBuilder createBuilder() {
    return BatchContinueCapability.builder();
  }

  @Override
  protected void setValue(BatchContinueCapability.BatchContinueCapabilityBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    builder.value(mapper.readValue(p, Boolean.class));
  }

  @Override
  protected BatchContinueCapability build(
      BatchContinueCapability.BatchContinueCapabilityBuilder builder) {
    return builder.build();
  }
}