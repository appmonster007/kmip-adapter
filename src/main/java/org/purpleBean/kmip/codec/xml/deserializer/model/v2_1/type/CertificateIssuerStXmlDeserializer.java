package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.type.CertificateIssuerSt;

public class CertificateIssuerStXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CertificateIssuerSt,
        CertificateIssuerSt.CertificateIssuerStBuilder> {

  public CertificateIssuerStXmlDeserializer() {
    super(CertificateIssuerSt.kmipTag, CertificateIssuerSt.encodingType);
  }

  @Override
  protected CertificateIssuerSt.CertificateIssuerStBuilder createBuilder() {
    return CertificateIssuerSt.builder();
  }

  @Override
  protected void setValue(CertificateIssuerSt.CertificateIssuerStBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected CertificateIssuerSt build(CertificateIssuerSt.CertificateIssuerStBuilder builder) {
    return builder.build();
  }
}