package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.CertificateRequest;

public class CertificateRequestTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CertificateRequest,
        CertificateRequest.CertificateRequestBuilder> {

  public CertificateRequestTtlvDeserializer() {
    super(CertificateRequest.kmipTag, CertificateRequest.encodingType);
  }

  @Override
  protected CertificateRequest.CertificateRequestBuilder createBuilder() {
    return CertificateRequest.builder();
  }

  @Override
  protected void setValue(CertificateRequest.CertificateRequestBuilder builder, byte[] tag,
                          byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
  }

  @Override
  protected CertificateRequest build(CertificateRequest.CertificateRequestBuilder builder) {
    return builder.build();
  }
}
