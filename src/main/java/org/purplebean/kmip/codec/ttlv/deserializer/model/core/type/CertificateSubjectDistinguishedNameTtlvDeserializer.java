package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.CertificateSubjectDistinguishedName;

/**
 * TTLV deserializer for {@link CertificateSubjectDistinguishedName}.
 */
public class CertificateSubjectDistinguishedNameTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CertificateSubjectDistinguishedName,
        CertificateSubjectDistinguishedName.CertificateSubjectDistinguishedNameBuilder> {

  /**
   * Constructs a new {@link CertificateSubjectDistinguishedNameTtlvDeserializer}.
   */
  public CertificateSubjectDistinguishedNameTtlvDeserializer() {
    super(CertificateSubjectDistinguishedName.kmipTag,
        CertificateSubjectDistinguishedName.encodingType);
  }

  @Override
  protected CertificateSubjectDistinguishedName.CertificateSubjectDistinguishedNameBuilder
      createBuilder() {
    return CertificateSubjectDistinguishedName.builder();
  }

  @Override
  protected void setValue(
      CertificateSubjectDistinguishedName.CertificateSubjectDistinguishedNameBuilder builder,
      byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected CertificateSubjectDistinguishedName build(
      CertificateSubjectDistinguishedName.CertificateSubjectDistinguishedNameBuilder builder) {
    return builder.build();
  }
}
