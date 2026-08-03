package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.CertificateIssuerC;

/**
 * TTLV deserializer for {@link CertificateIssuerC}.
 */
public class CertificateIssuerCTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CertificateIssuerC,
        CertificateIssuerC.CertificateIssuerCBuilder> {

  /**
   * Constructs a new {@link CertificateIssuerCTtlvDeserializer}.
   */
  public CertificateIssuerCTtlvDeserializer() {
    super(CertificateIssuerC.kmipTag, CertificateIssuerC.encodingType);
  }

  @Override
  protected CertificateIssuerC.CertificateIssuerCBuilder createBuilder() {
    return CertificateIssuerC.builder();
  }

  @Override
  protected void setValue(CertificateIssuerC.CertificateIssuerCBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected CertificateIssuerC build(CertificateIssuerC.CertificateIssuerCBuilder builder) {
    return builder.build();
  }
}