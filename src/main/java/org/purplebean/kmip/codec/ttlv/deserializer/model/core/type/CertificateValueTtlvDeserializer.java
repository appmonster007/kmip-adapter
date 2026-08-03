package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.CertificateValue;

/**
 * TTLV deserializer for {@link CertificateValue}.
 */
public class CertificateValueTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CertificateValue,
        CertificateValue.CertificateValueBuilder> {

  /**
   * Constructs a new {@link CertificateValueTtlvDeserializer}.
   */
  public CertificateValueTtlvDeserializer() {
    super(CertificateValue.kmipTag, CertificateValue.encodingType);
  }

  @Override
  protected CertificateValue.CertificateValueBuilder createBuilder() {
    return CertificateValue.builder();
  }

  @Override
  protected void setValue(CertificateValue.CertificateValueBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
  }

  @Override
  protected CertificateValue build(CertificateValue.CertificateValueBuilder builder) {
    return builder.build();
  }
}
