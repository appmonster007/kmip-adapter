package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.structure.ProfileVersion;
import org.purplebean.kmip.model.v2x1.type.ProfileVersionMajor;
import org.purplebean.kmip.model.v2x1.type.ProfileVersionMinor;

/**
 * TTLV deserializer for {@link ProfileVersion}.
 */
public class ProfileVersionTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ProfileVersion, ProfileVersion.ProfileVersionBuilder> {

  /**
   * Constructs a new {@link ProfileVersionTtlvDeserializer}.
   */
  public ProfileVersionTtlvDeserializer() {
    super(ProfileVersion.kmipTag, ProfileVersion.encodingType);
  }

  @Override
  protected ProfileVersion.ProfileVersionBuilder createBuilder() {
    return ProfileVersion.builder();
  }

  @Override
  protected void setValue(ProfileVersion.ProfileVersionBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.PROFILE_VERSION_MAJOR ->
          builder.profileVersionMajor(mapper.readValue(p, ProfileVersionMajor.class));
      case KmipTag.Standard.PROFILE_VERSION_MINOR ->
          builder.profileVersionMinor(mapper.readValue(p, ProfileVersionMinor.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ProfileVersion build(ProfileVersion.ProfileVersionBuilder builder) {
    return builder.build();
  }
}