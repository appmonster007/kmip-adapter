package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.CertificateLength;

public class CertificateLengthTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CertificateLength,
        CertificateLength.CertificateLengthBuilder> {

  public CertificateLengthTtlvDeserializer() {
    super(CertificateLength.kmipTag, CertificateLength.encodingType);
  }

  @Override
  protected CertificateLength.CertificateLengthBuilder createBuilder() {
    return CertificateLength.builder();
  }

  @Override
  protected void setValue(CertificateLength.CertificateLengthBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Integer.class));
  }

  @Override
  protected CertificateLength build(CertificateLength.CertificateLengthBuilder builder) {
    return builder.build();
  }
}
