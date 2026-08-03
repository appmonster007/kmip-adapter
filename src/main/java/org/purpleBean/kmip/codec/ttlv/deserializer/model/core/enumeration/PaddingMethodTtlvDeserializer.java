package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.PaddingMethod;

public class PaddingMethodTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PaddingMethod, PaddingMethod.PaddingMethodBuilder> {

  public PaddingMethodTtlvDeserializer() {
    super(PaddingMethod.kmipTag, PaddingMethod.encodingType);
  }

  @Override
  protected PaddingMethod.PaddingMethodBuilder createBuilder() {
    return PaddingMethod.builder();
  }

  @Override
  protected void setValue(PaddingMethod.PaddingMethodBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(PaddingMethod.fromValue(value));
  }

  @Override
  protected PaddingMethod build(PaddingMethod.PaddingMethodBuilder builder) {
    return builder.build();
  }
}
