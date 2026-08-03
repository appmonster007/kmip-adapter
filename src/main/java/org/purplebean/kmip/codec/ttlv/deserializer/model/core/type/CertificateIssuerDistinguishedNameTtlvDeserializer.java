package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.CertificateIssuerDistinguishedName;

/**
 * TTLV deserializer for {@link CertificateIssuerDistinguishedName}.
 */
public class CertificateIssuerDistinguishedNameTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CertificateIssuerDistinguishedName,
        CertificateIssuerDistinguishedName.CertificateIssuerDistinguishedNameBuilder> {

  /**
   * Constructs a new {@link CertificateIssuerDistinguishedNameTtlvDeserializer}.
   */
  public CertificateIssuerDistinguishedNameTtlvDeserializer() {
    super(CertificateIssuerDistinguishedName.kmipTag,
        CertificateIssuerDistinguishedName.encodingType);
  }

  @Override
  protected CertificateIssuerDistinguishedName.CertificateIssuerDistinguishedNameBuilder
      createBuilder() {
    return CertificateIssuerDistinguishedName.builder();
  }

  @Override
  protected void setValue(
      CertificateIssuerDistinguishedName.CertificateIssuerDistinguishedNameBuilder builder,
      byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected CertificateIssuerDistinguishedName build(
      CertificateIssuerDistinguishedName.CertificateIssuerDistinguishedNameBuilder builder) {
    return builder.build();
  }
}
