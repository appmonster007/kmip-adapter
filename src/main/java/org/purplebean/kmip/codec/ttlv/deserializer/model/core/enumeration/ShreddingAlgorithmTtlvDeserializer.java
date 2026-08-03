package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.ShreddingAlgorithm;

public class ShreddingAlgorithmTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ShreddingAlgorithm,
        ShreddingAlgorithm.ShreddingAlgorithmBuilder> {

  public ShreddingAlgorithmTtlvDeserializer() {
    super(ShreddingAlgorithm.kmipTag, ShreddingAlgorithm.encodingType);
  }

  @Override
  protected ShreddingAlgorithm.ShreddingAlgorithmBuilder createBuilder() {
    return ShreddingAlgorithm.builder();
  }

  @Override
  protected void setValue(ShreddingAlgorithm.ShreddingAlgorithmBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(ShreddingAlgorithm.fromValue(value));
  }

  @Override
  protected ShreddingAlgorithm build(ShreddingAlgorithm.ShreddingAlgorithmBuilder builder) {
    return builder.build();
  }
}
