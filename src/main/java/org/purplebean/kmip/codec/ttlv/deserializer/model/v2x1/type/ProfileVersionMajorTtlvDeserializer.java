package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.ProfileVersionMajor;

/**
 * TTLV deserializer for {@link ProfileVersionMajor}.
 */
public class ProfileVersionMajorTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ProfileVersionMajor,
        ProfileVersionMajor.ProfileVersionMajorBuilder> {

  /**
   * Constructs a new {@link ProfileVersionMajorTtlvDeserializer}.
   */
  public ProfileVersionMajorTtlvDeserializer() {
    super(ProfileVersionMajor.kmipTag, ProfileVersionMajor.encodingType);
  }

  @Override
  protected ProfileVersionMajor.ProfileVersionMajorBuilder createBuilder() {
    return ProfileVersionMajor.builder();
  }

  @Override
  protected void setValue(ProfileVersionMajor.ProfileVersionMajorBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Integer.class));
  }

  @Override
  protected ProfileVersionMajor build(ProfileVersionMajor.ProfileVersionMajorBuilder builder) {
    return builder.build();
  }
}