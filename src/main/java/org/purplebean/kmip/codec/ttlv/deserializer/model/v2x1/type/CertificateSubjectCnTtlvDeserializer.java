package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.CertificateSubjectCn;

/**
 * TTLV deserializer for {@link CertificateSubjectCn}.
 */
public class CertificateSubjectCnTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CertificateSubjectCn,
        CertificateSubjectCn.CertificateSubjectCnBuilder> {

  /**
   * Constructs a new {@link CertificateSubjectCnTtlvDeserializer}.
   */
  public CertificateSubjectCnTtlvDeserializer() {
    super(CertificateSubjectCn.kmipTag, CertificateSubjectCn.encodingType);
  }

  @Override
  protected CertificateSubjectCn.CertificateSubjectCnBuilder createBuilder() {
    return CertificateSubjectCn.builder();
  }

  @Override
  protected void setValue(CertificateSubjectCn.CertificateSubjectCnBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected CertificateSubjectCn build(CertificateSubjectCn.CertificateSubjectCnBuilder builder) {
    return builder.build();
  }
}