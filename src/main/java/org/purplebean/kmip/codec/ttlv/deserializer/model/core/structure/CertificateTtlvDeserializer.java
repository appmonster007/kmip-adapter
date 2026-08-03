package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.CertificateType;
import org.purplebean.kmip.model.core.structure.Certificate;
import org.purplebean.kmip.model.core.type.CertificateValue;

/**
 * TTLV deserializer for {@link Certificate}.
 */
public class CertificateTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<Certificate, Certificate.CertificateBuilder> {

  /**
   * Constructs a new {@link CertificateTtlvDeserializer}.
   */
  public CertificateTtlvDeserializer() {
    super(Certificate.kmipTag, Certificate.encodingType);
  }

  @Override
  protected Certificate.CertificateBuilder createBuilder() {
    return Certificate.builder();
  }

  @Override
  protected void setValue(Certificate.CertificateBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.CERTIFICATE_TYPE ->
          builder.certificateType(mapper.readValue(p, CertificateType.class));
      case KmipTag.Standard.CERTIFICATE_VALUE ->
          builder.certificateValue(mapper.readValue(p, CertificateValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Certificate build(Certificate.CertificateBuilder builder) {
    return builder.build();
  }
}