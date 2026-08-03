package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.CertificateSubjectAlternativeName;

public class CertificateSubjectAlternativeNameTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CertificateSubjectAlternativeName,
        CertificateSubjectAlternativeName.CertificateSubjectAlternativeNameBuilder> {

  public CertificateSubjectAlternativeNameTtlvDeserializer() {
    super(CertificateSubjectAlternativeName.kmipTag,
        CertificateSubjectAlternativeName.encodingType);
  }

  @Override
  protected CertificateSubjectAlternativeName.CertificateSubjectAlternativeNameBuilder createBuilder() {
    return CertificateSubjectAlternativeName.builder();
  }

  @Override
  protected void setValue(
      CertificateSubjectAlternativeName.CertificateSubjectAlternativeNameBuilder builder,
      byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected CertificateSubjectAlternativeName build(
      CertificateSubjectAlternativeName.CertificateSubjectAlternativeNameBuilder builder) {
    return builder.build();
  }
}
