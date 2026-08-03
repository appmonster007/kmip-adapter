package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.type.CertificateIssuerL;

public class CertificateIssuerLTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CertificateIssuerL,
        CertificateIssuerL.CertificateIssuerLBuilder> {

  public CertificateIssuerLTtlvDeserializer() {
    super(CertificateIssuerL.kmipTag, CertificateIssuerL.encodingType);
  }

  @Override
  protected CertificateIssuerL.CertificateIssuerLBuilder createBuilder() {
    return CertificateIssuerL.builder();
  }

  @Override
  protected void setValue(CertificateIssuerL.CertificateIssuerLBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected CertificateIssuerL build(CertificateIssuerL.CertificateIssuerLBuilder builder) {
    return builder.build();
  }
}