package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.CertificateIssuerEmail;

public class CertificateIssuerEmailXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CertificateIssuerEmail,
        CertificateIssuerEmail.CertificateIssuerEmailBuilder> {

  public CertificateIssuerEmailXmlDeserializer() {
    super(CertificateIssuerEmail.kmipTag, CertificateIssuerEmail.encodingType);
  }

  @Override
  protected CertificateIssuerEmail.CertificateIssuerEmailBuilder createBuilder() {
    return CertificateIssuerEmail.builder();
  }

  @Override
  protected void setValue(CertificateIssuerEmail.CertificateIssuerEmailBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected CertificateIssuerEmail build(
      CertificateIssuerEmail.CertificateIssuerEmailBuilder builder) {
    return builder.build();
  }
}