package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v3x0.type.PasswordSaltAlgorithm;

/**
 * TTLV deserializer for {@link PasswordSaltAlgorithm}.
 */
public class PasswordSaltAlgorithmTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PasswordSaltAlgorithm,
        PasswordSaltAlgorithm.PasswordSaltAlgorithmBuilder> {

  /**
   * Constructs a new {@link PasswordSaltAlgorithmTtlvDeserializer}.
   */
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