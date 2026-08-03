package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.CertificateSubjectL;

/**
 * TTLV deserializer for {@link CertificateSubjectL}.
 */
public class CertificateSubjectLTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CertificateSubjectL,
        CertificateSubjectL.CertificateSubjectLBuilder> {

  /**
   * Constructs a new {@link CertificateSubjectLTtlvDeserializer}.
   */
  public CertificateSubjectLTtlvDeserializer() {
    super(CertificateSubjectL.kmipTag, CertificateSubjectL.encodingType);
  }

  @Override
  protected CertificateSubjectL.CertificateSubjectLBuilder createBuilder() {
    return CertificateSubjectL.builder();
  }

  @Override
  protected void setValue(CertificateSubjectL.CertificateSubjectLBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected CertificateSubjectL build(CertificateSubjectL.CertificateSubjectLBuilder builder) {
    return builder.build();
  }
}