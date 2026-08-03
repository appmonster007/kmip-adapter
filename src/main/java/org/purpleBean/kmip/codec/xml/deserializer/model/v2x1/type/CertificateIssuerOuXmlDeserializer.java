package org.purpleBean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2x1.type.CertificateIssuerOu;

public class CertificateIssuerOuXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CertificateIssuerOu,
        CertificateIssuerOu.CertificateIssuerOuBuilder> {

  public CertificateIssuerOuXmlDeserializer() {
    super(CertificateIssuerOu.kmipTag, CertificateIssuerOu.encodingType);
  }

  @Override
  protected CertificateIssuerOu.CertificateIssuerOuBuilder createBuilder() {
    return CertificateIssuerOu.builder();
  }

  @Override
  protected void setValue(CertificateIssuerOu.CertificateIssuerOuBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected CertificateIssuerOu build(CertificateIssuerOu.CertificateIssuerOuBuilder builder) {
    return builder.build();
  }
}