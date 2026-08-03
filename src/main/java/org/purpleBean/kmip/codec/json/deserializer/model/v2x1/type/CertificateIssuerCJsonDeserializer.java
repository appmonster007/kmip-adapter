package org.purpleBean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2x1.type.CertificateIssuerC;

public class CertificateIssuerCJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CertificateIssuerC,
        CertificateIssuerC.CertificateIssuerCBuilder> {

  public CertificateIssuerCJsonDeserializer() {
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