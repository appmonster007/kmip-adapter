package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.CertificateIssuerO;

public class CertificateIssuerOTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CertificateIssuerO,
        CertificateIssuerO.CertificateIssuerOBuilder> {

  public CertificateIssuerOTtlvDeserializer() {
    super(CertificateIssuerO.kmipTag, CertificateIssuerO.encodingType);
  }

  @Override
  protected CertificateIssuerO.CertificateIssuerOBuilder createBuilder() {
    return CertificateIssuerO.builder();
  }

  @Override
  protected void setValue(CertificateIssuerO.CertificateIssuerOBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected CertificateIssuerO build(CertificateIssuerO.CertificateIssuerOBuilder builder) {
    return builder.build();
  }
}