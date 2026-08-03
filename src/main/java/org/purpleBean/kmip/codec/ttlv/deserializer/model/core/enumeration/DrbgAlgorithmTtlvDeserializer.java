package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.DrbgAlgorithm;

public class DrbgAlgorithmTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DrbgAlgorithm, DrbgAlgorithm.DrbgAlgorithmBuilder> {

  public DrbgAlgorithmTtlvDeserializer() {
    super(DrbgAlgorithm.kmipTag, DrbgAlgorithm.encodingType);
  }

  @Override
  protected DrbgAlgorithm.DrbgAlgorithmBuilder createBuilder() {
    return DrbgAlgorithm.builder();
  }

  @Override
  protected void setValue(DrbgAlgorithm.DrbgAlgorithmBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(DrbgAlgorithm.fromValue(value));
  }

  @Override
  protected DrbgAlgorithm build(DrbgAlgorithm.DrbgAlgorithmBuilder builder) {
    return builder.build();
  }
}
