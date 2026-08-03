package org.purplebean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.CertificateIssuer;
import org.purplebean.kmip.model.core.type.CertificateIssuerAlternativeName;
import org.purplebean.kmip.model.core.type.CertificateIssuerDistinguishedName;

public class CertificateIssuerJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CertificateIssuer,
        CertificateIssuer.CertificateIssuerBuilder> {

  public CertificateIssuerJsonDeserializer() {
    super(CertificateIssuer.kmipTag, CertificateIssuer.encodingType);
  }

  @Override
  protected CertificateIssuer.CertificateIssuerBuilder createBuilder() {
    return CertificateIssuer.builder();
  }

  @Override
  protected void setValue(CertificateIssuer.CertificateIssuerBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.CERTIFICATE_ISSUER_DISTINGUISHED_NAME ->
          builder.certificateIssuerDistinguishedName(
              ctxt.readValue(p, CertificateIssuerDistinguishedName.class));
      case KmipTag.Standard.CERTIFICATE_ISSUER_ALTERNATIVE_NAME ->
          builder.certificateIssuerAlternativeName(
              ctxt.readValue(p, CertificateIssuerAlternativeName.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CertificateIssuer build(CertificateIssuer.CertificateIssuerBuilder builder) {
    return builder.build();
  }
}
