package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.type.CertificateIssuerCn;

public class CertificateIssuerCnTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CertificateIssuerCn,
        CertificateIssuerCn.CertificateIssuerCnBuilder> {

  public CertificateIssuerCnTtlvDeserializer() {
    super(CertificateIssuerCn.kmipTag, CertificateIssuerCn.encodingType);
  }

  @Override
  protected CertificateIssuerCn.CertificateIssuerCnBuilder createBuilder() {
    return CertificateIssuerCn.builder();
  }

  @Override
  protected void setValue(CertificateIssuerCn.CertificateIssuerCnBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected CertificateIssuerCn build(CertificateIssuerCn.CertificateIssuerCnBuilder builder) {
    return builder.build();
  }
}