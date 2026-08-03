package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.CertificateIssuerOu;

public class CertificateIssuerOuTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CertificateIssuerOu,
        CertificateIssuerOu.CertificateIssuerOuBuilder> {

  public CertificateIssuerOuTtlvDeserializer() {
    super(CertificateIssuerOu.kmipTag, CertificateIssuerOu.encodingType);
  }

  @Override
  protected CertificateIssuerOu.CertificateIssuerOuBuilder createBuilder() {
    return CertificateIssuerOu.builder();
  }

  @Override
  protected void setValue(CertificateIssuerOu.CertificateIssuerOuBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected CertificateIssuerOu build(CertificateIssuerOu.CertificateIssuerOuBuilder builder) {
    return builder.build();
  }
}