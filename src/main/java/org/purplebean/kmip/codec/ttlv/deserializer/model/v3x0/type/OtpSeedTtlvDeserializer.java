package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v3x0.type.OtpSeed;

/**
 * TTLV deserializer for {@link OtpSeed}.
 */
public class OtpSeedTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<OtpSeed, OtpSeed.OtpSeedBuilder> {

  /**
   * Constructs a new {@link OtpSeedTtlvDeserializer}.
   */
  public OtpSeedTtlvDeserializer() {
    super(OtpSeed.kmipTag, OtpSeed.encodingType);
  }

  @Override
  protected OtpSeed.OtpSeedBuilder createBuilder() {
    return OtpSeed.builder();
  }

  @Override
  protected void setValue(OtpSeed.OtpSeedBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, ByteBuffer.class));
  }

  @Override
  protected OtpSeed build(OtpSeed.OtpSeedBuilder builder) {
    return builder.build();
  }
}