package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.CertificateValue;

public class CertificateValueTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CertificateValue,
        CertificateValue.CertificateValueBuilder> {

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
