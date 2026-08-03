package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.DigitalSignatureAlgorithm;

/**
 * TTLV deserializer for {@link DigitalSignatureAlgorithm}.
 */
public class DigitalSignatureAlgorithmTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DigitalSignatureAlgorithm,
        DigitalSignatureAlgorithm.DigitalSignatureAlgorithmBuilder> {

  /**
   * Constructs a new {@link DigitalSignatureAlgorithmTtlvDeserializer}.
   */
  public DigitalSignatureAlgorithmTtlvDeserializer() {
    super(DigitalSignatureAlgorithm.kmipTag, DigitalSignatureAlgorithm.encodingType);
  }

  @Override
  protected DigitalSignatureAlgorithm.DigitalSignatureAlgorithmBuilder createBuilder() {
    return DigitalSignatureAlgorithm.builder();
  }

  @Override
  protected void setValue(DigitalSignatureAlgorithm.DigitalSignatureAlgorithmBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(DigitalSignatureAlgorithm.fromValue(value));
  }

  @Override
  protected DigitalSignatureAlgorithm build(
      DigitalSignatureAlgorithm.DigitalSignatureAlgorithmBuilder builder) {
    return builder.build();
  }
}
