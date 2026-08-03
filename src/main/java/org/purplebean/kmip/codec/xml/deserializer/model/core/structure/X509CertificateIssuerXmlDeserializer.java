package org.purplebean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.structure.X509CertificateIssuer;
import org.purplebean.kmip.model.core.type.IssuerAlternativeName;
import org.purplebean.kmip.model.core.type.IssuerDistinguishedName;

public class X509CertificateIssuerXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<X509CertificateIssuer,
        X509CertificateIssuer.X509CertificateIssuerBuilder> {

  public X509CertificateIssuerXmlDeserializer() {
    super(X509CertificateIssuer.kmipTag, X509CertificateIssuer.encodingType);
  }

  @Override
  protected X509CertificateIssuer.X509CertificateIssuerBuilder createBuilder() {
    return X509CertificateIssuer.builder();
  }

  @Override
  protected void setValue(X509CertificateIssuer.X509CertificateIssuerBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ISSUER_DISTINGUISHED_NAME ->
          builder.issuerDistinguishedName(ctxt.readValue(p, IssuerDistinguishedName.class));
      case KmipTag.Standard.ISSUER_ALTERNATIVE_NAME ->
          builder.issuerAlternativeName(ctxt.readValue(p, IssuerAlternativeName.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected X509CertificateIssuer build(
      X509CertificateIssuer.X509CertificateIssuerBuilder builder) {
    return builder.build();
  }
}