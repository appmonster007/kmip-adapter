package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.CertificateType;

/**
 * TTLV deserializer for {@link CertificateType}.
 */
public class CertificateTypeTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CertificateType, CertificateType.CertificateTypeBuilder> {

  /**
   * Constructs a new {@link CertificateTypeTtlvDeserializer}.
   */
  public CertificateTypeTtlvDeserializer() {
    super(CertificateType.kmipTag, CertificateType.encodingType);
  }

  @Override
  protected CertificateType.CertificateTypeBuilder createBuilder() {
    return CertificateType.builder();
  }

  @Override
  protected void setValue(CertificateType.CertificateTypeBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(CertificateType.fromValue(value));
  }

  @Override
  protected CertificateType build(CertificateType.CertificateTypeBuilder builder) {
    return builder.build();
  }
}
