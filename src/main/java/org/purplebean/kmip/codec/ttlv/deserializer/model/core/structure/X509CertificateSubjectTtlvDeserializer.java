package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.X509CertificateSubject;
import org.purplebean.kmip.model.core.type.SubjectAlternativeName;
import org.purplebean.kmip.model.core.type.SubjectDistinguishedName;

/**
 * TTLV deserializer for {@link X509CertificateSubject}.
 */
public class X509CertificateSubjectTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<X509CertificateSubject,
        X509CertificateSubject.X509CertificateSubjectBuilder> {

  /**
   * Constructs a new {@link X509CertificateSubjectTtlvDeserializer}.
   */
  public X509CertificateSubjectTtlvDeserializer() {
    super(X509CertificateSubject.kmipTag, X509CertificateSubject.encodingType);
  }

  @Override
  protected X509CertificateSubject.X509CertificateSubjectBuilder createBuilder() {
    return X509CertificateSubject.builder();
  }

  @Override
  protected void setValue(X509CertificateSubject.X509CertificateSubjectBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.SUBJECT_DISTINGUISHED_NAME ->
          builder.subjectDistinguishedName(mapper.readValue(p, SubjectDistinguishedName.class));
      case KmipTag.Standard.SUBJECT_ALTERNATIVE_NAME ->
          builder.subjectAlternativeName(mapper.readValue(p, SubjectAlternativeName.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected X509CertificateSubject build(
      X509CertificateSubject.X509CertificateSubjectBuilder builder) {
    return builder.build();
  }
}