package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.CertificateSubjectAlternativeName;

/**
 * XML deserializer for {@link CertificateSubjectAlternativeName}.
 */
public class CertificateSubjectAlternativeNameXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CertificateSubjectAlternativeName,
        CertificateSubjectAlternativeName.CertificateSubjectAlternativeNameBuilder> {

  /**
   * Constructs a new {@link CertificateSubjectAlternativeNameXmlDeserializer}.
   */
  public CertificateSubjectAlternativeNameXmlDeserializer() {
    super(CertificateSubjectAlternativeName.kmipTag,
        CertificateSubjectAlternativeName.encodingType);
  }

  @Override
  protected CertificateSubjectAlternativeName.CertificateSubjectAlternativeNameBuilder
      createBuilder() {
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