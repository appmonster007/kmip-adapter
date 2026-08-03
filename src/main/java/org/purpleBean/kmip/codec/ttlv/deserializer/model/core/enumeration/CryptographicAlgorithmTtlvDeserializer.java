package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;

public class CryptographicAlgorithmTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CryptographicAlgorithm,
        CryptographicAlgorithm.CryptographicAlgorithmBuilder> {

  public CryptographicAlgorithmTtlvDeserializer() {
    super(CryptographicAlgorithm.kmipTag, CryptographicAlgorithm.encodingType);
  }

  @Override
  protected CryptographicAlgorithm.CryptographicAlgorithmBuilder createBuilder() {
    return CryptographicAlgorithm.builder();
  }

  @Override
  protected void setValue(CryptographicAlgorithm.CryptographicAlgorithmBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(CryptographicAlgorithm.fromValue(value));
  }

  @Override
  protected CryptographicAlgorithm build(
      CryptographicAlgorithm.CryptographicAlgorithmBuilder builder) {
    return builder.build();
  }
}
