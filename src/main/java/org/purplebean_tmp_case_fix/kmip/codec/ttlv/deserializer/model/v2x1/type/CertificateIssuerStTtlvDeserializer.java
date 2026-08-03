package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.CertificateIssuerSt;

public class CertificateIssuerStTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CertificateIssuerSt,
        CertificateIssuerSt.CertificateIssuerStBuilder> {

  public CertificateIssuerStTtlvDeserializer() {
    super(CertificateIssuerSt.kmipTag, CertificateIssuerSt.encodingType);
  }

  @Override
  protected CertificateIssuerSt.CertificateIssuerStBuilder createBuilder() {
    return CertificateIssuerSt.builder();
  }

  @Override
  protected void setValue(CertificateIssuerSt.CertificateIssuerStBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected CertificateIssuerSt build(CertificateIssuerSt.CertificateIssuerStBuilder builder) {
    return builder.build();
  }
}