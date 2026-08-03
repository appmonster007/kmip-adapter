package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v3x0.enumeration.OtpAlgorithm;

/**
 * TTLV deserializer for {@link OtpAlgorithm}.
 */
public class OtpAlgorithmTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<OtpAlgorithm, OtpAlgorithm.OtpAlgorithmBuilder> {

  /**
   * Constructs a new {@link OtpAlgorithmTtlvDeserializer}.
   */
  public OtpAlgorithmTtlvDeserializer() {
    super(OtpAlgorithm.kmipTag, OtpAlgorithm.encodingType);
  }

  @Override
  protected OtpAlgorithm.OtpAlgorithmBuilder createBuilder() {
    return OtpAlgorithm.builder();
  }

  @Override
  protected void setValue(OtpAlgorithm.OtpAlgorithmBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(OtpAlgorithm.fromValue(value));
  }

  @Override
  protected OtpAlgorithm build(OtpAlgorithm.OtpAlgorithmBuilder builder) {
    return builder.build();
  }
}
