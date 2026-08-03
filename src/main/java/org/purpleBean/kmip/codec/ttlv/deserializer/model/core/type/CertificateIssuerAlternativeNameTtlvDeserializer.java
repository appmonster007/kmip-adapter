package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.CertificateIssuerAlternativeName;

public class CertificateIssuerAlternativeNameTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CertificateIssuerAlternativeName,
        CertificateIssuerAlternativeName.CertificateIssuerAlternativeNameBuilder> {

  public CertificateIssuerAlternativeNameTtlvDeserializer() {
    super(CertificateIssuerAlternativeName.kmipTag, CertificateIssuerAlternativeName.encodingType);
  }

  @Override
  protected CertificateIssuerAlternativeName.CertificateIssuerAlternativeNameBuilder createBuilder() {
    return CertificateIssuerAlternativeName.builder();
  }

  @Override
  protected void setValue(
      CertificateIssuerAlternativeName.CertificateIssuerAlternativeNameBuilder builder, byte[] tag,
      byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected CertificateIssuerAlternativeName build(
      CertificateIssuerAlternativeName.CertificateIssuerAlternativeNameBuilder builder) {
    return builder.build();
  }
}
