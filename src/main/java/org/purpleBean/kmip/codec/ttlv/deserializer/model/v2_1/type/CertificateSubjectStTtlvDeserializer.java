package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.type.CertificateSubjectSt;

public class CertificateSubjectStTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CertificateSubjectSt,
        CertificateSubjectSt.CertificateSubjectStBuilder> {

  public CertificateSubjectStTtlvDeserializer() {
    super(CertificateSubjectSt.kmipTag, CertificateSubjectSt.encodingType);
  }

  @Override
  protected CertificateSubjectSt.CertificateSubjectStBuilder createBuilder() {
    return CertificateSubjectSt.builder();
  }

  @Override
  protected void setValue(CertificateSubjectSt.CertificateSubjectStBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected CertificateSubjectSt build(CertificateSubjectSt.CertificateSubjectStBuilder builder) {
    return builder.build();
  }
}