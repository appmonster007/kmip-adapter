package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v3_0.type.PasswordSaltAlgorithm;

public class PasswordSaltAlgorithmTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PasswordSaltAlgorithm,
        PasswordSaltAlgorithm.PasswordSaltAlgorithmBuilder> {

  public PasswordSaltAlgorithmTtlvDeserializer() {
    super(PasswordSaltAlgorithm.kmipTag, PasswordSaltAlgorithm.encodingType);
  }

  @Override
  protected PasswordSaltAlgorithm.PasswordSaltAlgorithmBuilder createBuilder() {
    return PasswordSaltAlgorithm.builder();
  }

  @Override
  protected void setValue(PasswordSaltAlgorithm.PasswordSaltAlgorithmBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(PasswordSaltAlgorithm.fromValue(mapper.readValue(p, Integer.class)));
  }

  @Override
  protected PasswordSaltAlgorithm build(
      PasswordSaltAlgorithm.PasswordSaltAlgorithmBuilder builder) {
    return builder.build();
  }
}