package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.CertificateIssuerEmail;

public class CertificateIssuerEmailTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CertificateIssuerEmail,
        CertificateIssuerEmail.CertificateIssuerEmailBuilder> {

  public CertificateIssuerEmailTtlvDeserializer() {
    super(CertificateIssuerEmail.kmipTag, CertificateIssuerEmail.encodingType);
  }

  @Override
  protected CertificateIssuerEmail.CertificateIssuerEmailBuilder createBuilder() {
    return CertificateIssuerEmail.builder();
  }

  @Override
  protected void setValue(CertificateIssuerEmail.CertificateIssuerEmailBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected CertificateIssuerEmail build(
      CertificateIssuerEmail.CertificateIssuerEmailBuilder builder) {
    return builder.build();
  }
}