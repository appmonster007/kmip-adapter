package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.type.CertificateIssuerL;

public class CertificateIssuerLJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CertificateIssuerL,
        CertificateIssuerL.CertificateIssuerLBuilder> {

  public CertificateIssuerLJsonDeserializer() {
    super(CertificateIssuerL.kmipTag, CertificateIssuerL.encodingType);
  }

  @Override
  protected CertificateIssuerL.CertificateIssuerLBuilder createBuilder() {
    return CertificateIssuerL.builder();
  }

  @Override
  protected void setValue(CertificateIssuerL.CertificateIssuerLBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected CertificateIssuerL build(CertificateIssuerL.CertificateIssuerLBuilder builder) {
    return builder.build();
  }
}