package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.X509CertificateIssuer;
import org.purplebean.kmip.model.core.type.IssuerAlternativeName;
import org.purplebean.kmip.model.core.type.IssuerDistinguishedName;

/**
 * TTLV deserializer for {@link X509CertificateIssuer}.
 */
public class X509CertificateIssuerTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<X509CertificateIssuer,
        X509CertificateIssuer.X509CertificateIssuerBuilder> {

  /**
   * Constructs a new {@link X509CertificateIssuerTtlvDeserializer}.
   */
  public X509CertificateIssuerTtlvDeserializer() {
    super(X509CertificateIssuer.kmipTag, X509CertificateIssuer.encodingType);
  }

  @Override
  protected X509CertificateIssuer.X509CertificateIssuerBuilder createBuilder() {
    return X509CertificateIssuer.builder();
  }

  @Override
  protected void setValue(X509CertificateIssuer.X509CertificateIssuerBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ISSUER_DISTINGUISHED_NAME ->
          builder.issuerDistinguishedName(mapper.readValue(p, IssuerDistinguishedName.class));
      case KmipTag.Standard.ISSUER_ALTERNATIVE_NAME ->
          builder.issuerAlternativeName(mapper.readValue(p, IssuerAlternativeName.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected X509CertificateIssuer build(
      X509CertificateIssuer.X509CertificateIssuerBuilder builder) {
    return builder.build();
  }
}