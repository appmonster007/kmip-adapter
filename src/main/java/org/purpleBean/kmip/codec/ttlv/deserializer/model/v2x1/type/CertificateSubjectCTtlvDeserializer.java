package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2x1.type.CertificateSubjectC;

public class CertificateSubjectCTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CertificateSubjectC,
        CertificateSubjectC.CertificateSubjectCBuilder> {

  public CertificateSubjectCTtlvDeserializer() {
    super(CertificateSubjectC.kmipTag, CertificateSubjectC.encodingType);
  }

  @Override
  protected CertificateSubjectC.CertificateSubjectCBuilder createBuilder() {
    return CertificateSubjectC.builder();
  }

  @Override
  protected void setValue(CertificateSubjectC.CertificateSubjectCBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected CertificateSubjectC build(CertificateSubjectC.CertificateSubjectCBuilder builder) {
    return builder.build();
  }
}