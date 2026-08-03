package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.MaskGenerator;

public class MaskGeneratorTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<MaskGenerator, MaskGenerator.MaskGeneratorBuilder> {

  public MaskGeneratorTtlvDeserializer() {
    super(MaskGenerator.kmipTag, MaskGenerator.encodingType);
  }

  @Override
  protected MaskGenerator.MaskGeneratorBuilder createBuilder() {
    return MaskGenerator.builder();
  }

  @Override
  protected void setValue(MaskGenerator.MaskGeneratorBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(MaskGenerator.fromValue(value));
  }

  @Override
  protected MaskGenerator build(MaskGenerator.MaskGeneratorBuilder builder) {
    return builder.build();
  }
}
