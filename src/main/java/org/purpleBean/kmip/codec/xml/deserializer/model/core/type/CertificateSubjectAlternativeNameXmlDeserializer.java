package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.CertificateSubjectAlternativeName;

public class CertificateSubjectAlternativeNameXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CertificateSubjectAlternativeName,
        CertificateSubjectAlternativeName.CertificateSubjectAlternativeNameBuilder> {

  public CertificateSubjectAlternativeNameXmlDeserializer() {
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