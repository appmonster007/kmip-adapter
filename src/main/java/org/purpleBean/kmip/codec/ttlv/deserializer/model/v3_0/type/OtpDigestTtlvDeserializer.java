package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v3_0.type.OtpDigest;

public class OtpDigestTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<OtpDigest, OtpDigest.OtpDigestBuilder> {

  public OtpDigestTtlvDeserializer() {
    super(OtpDigest.kmipTag, OtpDigest.encodingType);
  }

  @Override
  protected OtpDigest.OtpDigestBuilder createBuilder() {
    return OtpDigest.builder();
  }

  @Override
  protected void setValue(OtpDigest.OtpDigestBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    builder.value(OtpDigest.fromValue(mapper.readValue(p, Integer.class)));
  }

  @Override
  protected OtpDigest build(OtpDigest.OtpDigestBuilder builder) {
    return builder.build();
  }
}