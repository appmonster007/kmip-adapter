package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2x1.type.AlwaysSensitive;

public class AlwaysSensitiveTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AlwaysSensitive, AlwaysSensitive.AlwaysSensitiveBuilder> {

  public AlwaysSensitiveTtlvDeserializer() {
    super(AlwaysSensitive.kmipTag, AlwaysSensitive.encodingType);
  }

  @Override
  protected AlwaysSensitive.AlwaysSensitiveBuilder createBuilder() {
    return AlwaysSensitive.builder();
  }

  @Override
  protected void setValue(AlwaysSensitive.AlwaysSensitiveBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Boolean.class));
  }

  @Override
  protected AlwaysSensitive build(AlwaysSensitive.AlwaysSensitiveBuilder builder) {
    return builder.build();
  }
}