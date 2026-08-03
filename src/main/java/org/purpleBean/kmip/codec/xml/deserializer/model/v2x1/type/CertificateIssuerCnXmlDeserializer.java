package org.purpleBean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2x1.type.CertificateIssuerCn;

public class CertificateIssuerCnXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CertificateIssuerCn,
        CertificateIssuerCn.CertificateIssuerCnBuilder> {

  public CertificateIssuerCnXmlDeserializer() {
    super(CertificateIssuerCn.kmipTag, CertificateIssuerCn.encodingType);
  }

  @Override
  protected CertificateIssuerCn.CertificateIssuerCnBuilder createBuilder() {
    return CertificateIssuerCn.builder();
  }

  @Override
  protected void setValue(CertificateIssuerCn.CertificateIssuerCnBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected CertificateIssuerCn build(CertificateIssuerCn.CertificateIssuerCnBuilder builder) {
    return builder.build();
  }
}