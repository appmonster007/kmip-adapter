package org.purplebean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.X509CertificateIdentifier;
import org.purplebean.kmip.model.core.type.CertificateSerialNumber;
import org.purplebean.kmip.model.core.type.IssuerDistinguishedName;

/**
 * JSON deserializer for {@link X509CertificateIdentifier}.
 */
public class X509CertificateIdentifierJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<X509CertificateIdentifier,
        X509CertificateIdentifier.X509CertificateIdentifierBuilder> {

  /**
   * Constructs a new {@link X509CertificateIdentifierJsonDeserializer}.
   */
  public X509CertificateIdentifierJsonDeserializer() {
    super(X509CertificateIdentifier.kmipTag, X509CertificateIdentifier.encodingType);
  }

  @Override
  protected X509CertificateIdentifier.X509CertificateIdentifierBuilder createBuilder() {
    return X509CertificateIdentifier.builder();
  }

  @Override
  protected void setValue(X509CertificateIdentifier.X509CertificateIdentifierBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ISSUER_DISTINGUISHED_NAME ->
          builder.issuerDistinguishedName(ctxt.readValue(p, IssuerDistinguishedName.class));
      case KmipTag.Standard.CERTIFICATE_SERIAL_NUMBER ->
          builder.certificateSerialNumber(ctxt.readValue(p, CertificateSerialNumber.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected X509CertificateIdentifier build(
      X509CertificateIdentifier.X509CertificateIdentifierBuilder builder) {
    return builder.build();
  }
}