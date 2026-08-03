package org.purplebean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.CertificateSubject;
import org.purplebean.kmip.model.core.type.CertificateSubjectAlternativeName;
import org.purplebean.kmip.model.core.type.CertificateSubjectDistinguishedName;

/**
 * JSON deserializer for {@link CertificateSubject}.
 */
public class CertificateSubjectJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CertificateSubject,
        CertificateSubject.CertificateSubjectBuilder> {

  /**
   * Constructs a new {@link CertificateSubjectJsonDeserializer}.
   */
  public CertificateSubjectJsonDeserializer() {
    super(CertificateSubject.kmipTag, CertificateSubject.encodingType);
  }

  @Override
  protected CertificateSubject.CertificateSubjectBuilder createBuilder() {
    return CertificateSubject.builder();
  }

  @Override
  protected void setValue(CertificateSubject.CertificateSubjectBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.CERTIFICATE_SUBJECT_DISTINGUISHED_NAME ->
          builder.certificateSubjectDistinguishedName(
              ctxt.readValue(p, CertificateSubjectDistinguishedName.class));
      case KmipTag.Standard.CERTIFICATE_SUBJECT_ALTERNATIVE_NAME ->
          builder.certificateSubjectAlternativeName(
              ctxt.readValue(p, CertificateSubjectAlternativeName.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CertificateSubject build(CertificateSubject.CertificateSubjectBuilder builder) {
    return builder.build();
  }
}
