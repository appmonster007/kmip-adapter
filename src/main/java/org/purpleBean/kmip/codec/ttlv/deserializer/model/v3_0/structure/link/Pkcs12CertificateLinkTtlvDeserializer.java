package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.structure.link;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.link.Pkcs12CertificateLink;

public class Pkcs12CertificateLinkTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<Pkcs12CertificateLink,
        Pkcs12CertificateLink.Pkcs12CertificateLinkBuilder> {

  public Pkcs12CertificateLinkTtlvDeserializer() {
    super(Pkcs12CertificateLink.kmipTag, Pkcs12CertificateLink.encodingType);
  }

  @Override
  protected Pkcs12CertificateLink.Pkcs12CertificateLinkBuilder createBuilder() {
    return Pkcs12CertificateLink.builder();
  }

  @Override
  protected void setValue(Pkcs12CertificateLink.Pkcs12CertificateLinkBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Pkcs12CertificateLink build(
      Pkcs12CertificateLink.Pkcs12CertificateLinkBuilder builder) {
    return builder.build();
  }
}
