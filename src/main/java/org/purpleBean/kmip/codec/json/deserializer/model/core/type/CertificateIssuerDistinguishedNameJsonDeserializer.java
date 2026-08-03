package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.CertificateIssuerDistinguishedName;

public class CertificateIssuerDistinguishedNameJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CertificateIssuerDistinguishedName,
        CertificateIssuerDistinguishedName.CertificateIssuerDistinguishedNameBuilder> {

  public CertificateIssuerDistinguishedNameJsonDeserializer() {
    super(CertificateIssuerDistinguishedName.kmipTag,
        CertificateIssuerDistinguishedName.encodingType);
  }

  @Override
  protected CertificateIssuerDistinguishedName.CertificateIssuerDistinguishedNameBuilder createBuilder() {
    return CertificateIssuerDistinguishedName.builder();
  }

  @Override
  protected void setValue(
      CertificateIssuerDistinguishedName.CertificateIssuerDistinguishedNameBuilder builder,
      String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected CertificateIssuerDistinguishedName build(
      CertificateIssuerDistinguishedName.CertificateIssuerDistinguishedNameBuilder builder) {
    return builder.build();
  }
}
