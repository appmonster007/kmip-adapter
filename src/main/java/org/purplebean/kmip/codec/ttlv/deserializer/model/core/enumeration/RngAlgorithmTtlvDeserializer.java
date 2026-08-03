package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.RngAlgorithm;

public class RngAlgorithmTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<RngAlgorithm, RngAlgorithm.RngAlgorithmBuilder> {

  public RngAlgorithmTtlvDeserializer() {
    super(RngAlgorithm.kmipTag, RngAlgorithm.encodingType);
  }

  @Override
  protected RngAlgorithm.RngAlgorithmBuilder createBuilder() {
    return RngAlgorithm.builder();
  }

  @Override
  protected void setValue(RngAlgorithm.RngAlgorithmBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(RngAlgorithm.fromValue(value));
  }

  @Override
  protected RngAlgorithm build(RngAlgorithm.RngAlgorithmBuilder builder) {
    return builder.build();
  }
}
