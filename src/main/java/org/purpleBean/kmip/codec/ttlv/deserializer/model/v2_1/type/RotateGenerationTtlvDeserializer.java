package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.type.RotateGeneration;

public class RotateGenerationTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<RotateGeneration,
        RotateGeneration.RotateGenerationBuilder> {

  public RotateGenerationTtlvDeserializer() {
    super(RotateGeneration.kmipTag, RotateGeneration.encodingType);
  }

  @Override
  protected RotateGeneration.RotateGenerationBuilder createBuilder() {
    return RotateGeneration.builder();
  }

  @Override
  protected void setValue(RotateGeneration.RotateGenerationBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Integer.class));
  }

  @Override
  protected RotateGeneration build(RotateGeneration.RotateGenerationBuilder builder) {
    return builder.build();
  }
}