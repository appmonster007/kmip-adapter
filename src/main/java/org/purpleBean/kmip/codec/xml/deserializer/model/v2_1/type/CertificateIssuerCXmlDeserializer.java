package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.type.CertificateIssuerC;

public class CertificateIssuerCXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CertificateIssuerC,
        CertificateIssuerC.CertificateIssuerCBuilder> {

  public CertificateIssuerCXmlDeserializer() {
    super(CertificateIssuerC.kmipTag, CertificateIssuerC.encodingType);
  }

  @Override
  protected CertificateIssuerC.CertificateIssuerCBuilder createBuilder() {
    return CertificateIssuerC.builder();
  }

  @Override
  protected void setValue(CertificateIssuerC.CertificateIssuerCBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected CertificateIssuerC build(CertificateIssuerC.CertificateIssuerCBuilder builder) {
    return builder.build();
  }
}