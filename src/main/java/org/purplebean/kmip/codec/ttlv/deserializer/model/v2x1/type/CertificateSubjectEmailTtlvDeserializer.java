package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.CertificateSubjectEmail;

public class CertificateSubjectEmailTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CertificateSubjectEmail,
        CertificateSubjectEmail.CertificateSubjectEmailBuilder> {

  public CertificateSubjectEmailTtlvDeserializer() {
    super(CertificateSubjectEmail.kmipTag, CertificateSubjectEmail.encodingType);
  }

  @Override
  protected CertificateSubjectEmail.CertificateSubjectEmailBuilder createBuilder() {
    return CertificateSubjectEmail.builder();
  }

  @Override
  protected void setValue(CertificateSubjectEmail.CertificateSubjectEmailBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected CertificateSubjectEmail build(
      CertificateSubjectEmail.CertificateSubjectEmailBuilder builder) {
    return builder.build();
  }
}