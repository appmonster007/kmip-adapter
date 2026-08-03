package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.CertificateRequestType;

public class CertificateRequestTypeTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CertificateRequestType,
        CertificateRequestType.CertificateRequestTypeBuilder> {

  public CertificateRequestTypeTtlvDeserializer() {
    super(CertificateRequestType.kmipTag, CertificateRequestType.encodingType);
  }

  @Override
  protected CertificateRequestType.CertificateRequestTypeBuilder createBuilder() {
    return CertificateRequestType.builder();
  }

  @Override
  protected void setValue(CertificateRequestType.CertificateRequestTypeBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(CertificateRequestType.fromValue(value));
  }

  @Override
  protected CertificateRequestType build(
      CertificateRequestType.CertificateRequestTypeBuilder builder) {
    return builder.build();
  }
}
