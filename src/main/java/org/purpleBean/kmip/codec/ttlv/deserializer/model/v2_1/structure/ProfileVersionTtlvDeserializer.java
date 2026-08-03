package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.structure.ProfileVersion;
import org.purpleBean.kmip.model.v2_1.type.ProfileVersionMajor;
import org.purpleBean.kmip.model.v2_1.type.ProfileVersionMinor;

public class ProfileVersionTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ProfileVersion, ProfileVersion.ProfileVersionBuilder> {

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