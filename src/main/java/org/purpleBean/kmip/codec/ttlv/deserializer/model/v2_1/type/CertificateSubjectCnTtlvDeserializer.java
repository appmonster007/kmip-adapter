package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.type.CertificateSubjectCn;

public class CertificateSubjectCnTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CertificateSubjectCn,
        CertificateSubjectCn.CertificateSubjectCnBuilder> {

  public CertificateSubjectCnTtlvDeserializer() {
    super(CertificateSubjectCn.kmipTag, CertificateSubjectCn.encodingType);
  }

  @Override
  protected CertificateSubjectCn.CertificateSubjectCnBuilder createBuilder() {
    return CertificateSubjectCn.builder();
  }

  @Override
  protected void setValue(CertificateSubjectCn.CertificateSubjectCnBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected CertificateSubjectCn build(CertificateSubjectCn.CertificateSubjectCnBuilder builder) {
    return builder.build();
  }
}