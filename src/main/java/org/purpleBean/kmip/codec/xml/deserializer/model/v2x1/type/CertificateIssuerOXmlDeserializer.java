package org.purpleBean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2x1.type.CertificateIssuerO;

public class CertificateIssuerOXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CertificateIssuerO,
        CertificateIssuerO.CertificateIssuerOBuilder> {

  public CertificateIssuerOXmlDeserializer() {
    super(CertificateIssuerO.kmipTag, CertificateIssuerO.encodingType);
  }

  @Override
  protected CertificateIssuerO.CertificateIssuerOBuilder createBuilder() {
    return CertificateIssuerO.builder();
  }

  @Override
  protected void setValue(CertificateIssuerO.CertificateIssuerOBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected CertificateIssuerO build(CertificateIssuerO.CertificateIssuerOBuilder builder) {
    return builder.build();
  }
}