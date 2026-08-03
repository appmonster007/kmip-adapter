package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.CertificateSubject;
import org.purplebean.kmip.model.core.type.CertificateSubjectAlternativeName;
import org.purplebean.kmip.model.core.type.CertificateSubjectDistinguishedName;

public class CertificateSubjectTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CertificateSubject,
        CertificateSubject.CertificateSubjectBuilder> {

  public CertificateSubjectTtlvDeserializer() {
    super(CertificateSubject.kmipTag, CertificateSubject.encodingType);
  }

  @Override
  protected CertificateSubject.CertificateSubjectBuilder createBuilder() {
    return CertificateSubject.builder();
  }

  @Override
  protected void setValue(CertificateSubject.CertificateSubjectBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.CERTIFICATE_SUBJECT_DISTINGUISHED_NAME ->
          builder.certificateSubjectDistinguishedName(
              mapper.readValue(p, CertificateSubjectDistinguishedName.class));
      case KmipTag.Standard.CERTIFICATE_SUBJECT_ALTERNATIVE_NAME ->
          builder.certificateSubjectAlternativeName(
              mapper.readValue(p, CertificateSubjectAlternativeName.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CertificateSubject build(CertificateSubject.CertificateSubjectBuilder builder) {
    return builder.build();
  }
}