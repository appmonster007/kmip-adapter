package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.CertificateSubjectAlternativeName;

/**
 * JSON deserializer for {@link CertificateSubjectAlternativeName}.
 */
public class CertificateSubjectAlternativeNameJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CertificateSubjectAlternativeName,
        CertificateSubjectAlternativeName.CertificateSubjectAlternativeNameBuilder> {

  /**
   * Constructs a new {@link CertificateSubjectAlternativeNameJsonDeserializer}.
   */
  public CertificateSubjectAlternativeNameJsonDeserializer() {
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
      String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected CertificateSubjectAlternativeName build(
      CertificateSubjectAlternativeName.CertificateSubjectAlternativeNameBuilder builder) {
    return builder.build();
  }
}
