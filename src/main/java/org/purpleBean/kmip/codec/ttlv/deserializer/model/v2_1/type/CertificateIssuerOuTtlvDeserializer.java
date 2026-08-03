package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.type.CertificateIssuerOu;

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