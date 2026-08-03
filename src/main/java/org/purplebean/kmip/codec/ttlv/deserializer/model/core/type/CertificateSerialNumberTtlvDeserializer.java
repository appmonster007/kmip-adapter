package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.CertificateSerialNumber;

/**
 * TTLV deserializer for {@link CertificateSerialNumber}.
 */
public class CertificateSerialNumberTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CertificateSerialNumber,
        CertificateSerialNumber.CertificateSerialNumberBuilder> {

  /**
   * Constructs a new {@link CertificateSerialNumberTtlvDeserializer}.
   */
  public CertificateSerialNumberTtlvDeserializer() {
    super(CertificateSerialNumber.kmipTag, CertificateSerialNumber.encodingType);
  }

  @Override
  protected CertificateSerialNumber.CertificateSerialNumberBuilder createBuilder() {
    return CertificateSerialNumber.builder();
  }

  @Override
  protected void setValue(CertificateSerialNumber.CertificateSerialNumberBuilder builder,
                          byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper)
      throws IOException {
    builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
  }

  @Override
  protected CertificateSerialNumber build(
      CertificateSerialNumber.CertificateSerialNumberBuilder builder) {
    return builder.build();
  }
}
