package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.type.StreamingCapability;

public class StreamingCapabilityTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<StreamingCapability,
        StreamingCapability.StreamingCapabilityBuilder> {

  public StreamingCapabilityTtlvDeserializer() {
    super(StreamingCapability.kmipTag, StreamingCapability.encodingType);
  }

  @Override
  protected StreamingCapability.StreamingCapabilityBuilder createBuilder() {
    return StreamingCapability.builder();
  }

  @Override
  protected void setValue(StreamingCapability.StreamingCapabilityBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Boolean.class));
  }

  @Override
  protected StreamingCapability build(StreamingCapability.StreamingCapabilityBuilder builder) {
    return builder.build();
  }
}