package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.CertificateIssuerAlternativeName;

public class CertificateIssuerAlternativeNameXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CertificateIssuerAlternativeName,
        CertificateIssuerAlternativeName.CertificateIssuerAlternativeNameBuilder> {

  public CertificateIssuerAlternativeNameXmlDeserializer() {
    super(CertificateIssuerAlternativeName.kmipTag, CertificateIssuerAlternativeName.encodingType);
  }

  @Override
  protected CertificateIssuerAlternativeName.CertificateIssuerAlternativeNameBuilder createBuilder() {
    return CertificateIssuerAlternativeName.builder();
  }

  @Override
  protected void setValue(
      CertificateIssuerAlternativeName.CertificateIssuerAlternativeNameBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected CertificateIssuerAlternativeName build(
      CertificateIssuerAlternativeName.CertificateIssuerAlternativeNameBuilder builder) {
    return builder.build();
  }
}