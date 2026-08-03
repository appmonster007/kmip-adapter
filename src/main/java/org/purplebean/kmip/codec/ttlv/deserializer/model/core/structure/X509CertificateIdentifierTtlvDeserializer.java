package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.X509CertificateIdentifier;
import org.purplebean.kmip.model.core.type.CertificateSerialNumber;
import org.purplebean.kmip.model.core.type.IssuerDistinguishedName;

/**
 * TTLV deserializer for {@link X509CertificateIdentifier}.
 */
public class X509CertificateIdentifierTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<X509CertificateIdentifier,
        X509CertificateIdentifier.X509CertificateIdentifierBuilder> {

  /**
   * Constructs a new {@link X509CertificateIdentifierTtlvDeserializer}.
   */
  public X509CertificateIdentifierTtlvDeserializer() {
    super(X509CertificateIdentifier.kmipTag, X509CertificateIdentifier.encodingType);
  }

  @Override
  protected X509CertificateIdentifier.X509CertificateIdentifierBuilder createBuilder() {
    return X509CertificateIdentifier.builder();
  }

  @Override
  protected void setValue(X509CertificateIdentifier.X509CertificateIdentifierBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ISSUER_DISTINGUISHED_NAME ->
          builder.issuerDistinguishedName(mapper.readValue(p, IssuerDistinguishedName.class));
      case KmipTag.Standard.CERTIFICATE_SERIAL_NUMBER ->
          builder.certificateSerialNumber(mapper.readValue(p, CertificateSerialNumber.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected X509CertificateIdentifier build(
      X509CertificateIdentifier.X509CertificateIdentifierBuilder builder) {
    return builder.build();
  }
}