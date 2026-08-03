package org.purplebean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.structure.X509CertificateSubject;
import org.purplebean.kmip.model.core.type.SubjectAlternativeName;
import org.purplebean.kmip.model.core.type.SubjectDistinguishedName;

/**
 * XML deserializer for {@link X509CertificateSubject}.
 */
public class X509CertificateSubjectXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<X509CertificateSubject,
        X509CertificateSubject.X509CertificateSubjectBuilder> {

  /**
   * Constructs a new {@link X509CertificateSubjectXmlDeserializer}.
   */
  public X509CertificateSubjectXmlDeserializer() {
    super(X509CertificateSubject.kmipTag, X509CertificateSubject.encodingType);
  }

  @Override
  protected X509CertificateSubject.X509CertificateSubjectBuilder createBuilder() {
    return X509CertificateSubject.builder();
  }

  @Override
  protected void setValue(X509CertificateSubject.X509CertificateSubjectBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.SUBJECT_DISTINGUISHED_NAME ->
          builder.subjectDistinguishedName(ctxt.readValue(p, SubjectDistinguishedName.class));
      case KmipTag.Standard.SUBJECT_ALTERNATIVE_NAME ->
          builder.subjectAlternativeName(ctxt.readValue(p, SubjectAlternativeName.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected X509CertificateSubject build(
      X509CertificateSubject.X509CertificateSubjectBuilder builder) {
    return builder.build();
  }
}