package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.ProfileName;

/**
 * TTLV deserializer for {@link ProfileName}.
 */
public class ProfileNameTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<ProfileName, ProfileName.ProfileNameBuilder> {

  /**
   * Constructs a new {@link ProfileNameTtlvDeserializer}.
   */
  public ProfileNameTtlvDeserializer() {
    super(ProfileName.kmipTag, ProfileName.encodingType);
  }

  @Override
  protected ProfileName.ProfileNameBuilder createBuilder() {
    return ProfileName.builder();
  }

  @Override
  protected void setValue(ProfileName.ProfileNameBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(ProfileName.fromValue(value));
  }

  @Override
  protected ProfileName build(ProfileName.ProfileNameBuilder builder) {
    return builder.build();
  }
}
