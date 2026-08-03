package org.purpleBean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2x1.type.CertificateIssuerO;

public class CertificateIssuerOJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CertificateIssuerO,
        CertificateIssuerO.CertificateIssuerOBuilder> {

  public CertificateIssuerOJsonDeserializer() {
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