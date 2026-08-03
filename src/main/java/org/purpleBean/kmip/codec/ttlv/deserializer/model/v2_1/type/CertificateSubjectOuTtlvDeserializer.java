package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.type.CertificateSubjectOu;

public class CertificateSubjectOuTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CertificateSubjectOu,
        CertificateSubjectOu.CertificateSubjectOuBuilder> {

  public CertificateSubjectOuTtlvDeserializer() {
    super(CertificateSubjectOu.kmipTag, CertificateSubjectOu.encodingType);
  }

  @Override
  protected CertificateSubjectOu.CertificateSubjectOuBuilder createBuilder() {
    return CertificateSubjectOu.builder();
  }

  @Override
  protected void setValue(CertificateSubjectOu.CertificateSubjectOuBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected CertificateSubjectOu build(CertificateSubjectOu.CertificateSubjectOuBuilder builder) {
    return builder.build();
  }
}