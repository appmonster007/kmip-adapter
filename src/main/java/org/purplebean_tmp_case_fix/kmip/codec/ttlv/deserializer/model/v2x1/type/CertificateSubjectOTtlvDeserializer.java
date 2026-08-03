package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.CertificateSubjectO;

public class CertificateSubjectOTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CertificateSubjectO,
        CertificateSubjectO.CertificateSubjectOBuilder> {

  public CertificateSubjectOTtlvDeserializer() {
    super(CertificateSubjectO.kmipTag, CertificateSubjectO.encodingType);
  }

  @Override
  protected CertificateSubjectO.CertificateSubjectOBuilder createBuilder() {
    return CertificateSubjectO.builder();
  }

  @Override
  protected void setValue(CertificateSubjectO.CertificateSubjectOBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected CertificateSubjectO build(CertificateSubjectO.CertificateSubjectOBuilder builder) {
    return builder.build();
  }
}