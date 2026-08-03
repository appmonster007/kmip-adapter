package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.ProfileName;
import org.purpleBean.kmip.model.v2x1.structure.ProfileInformation;
import org.purpleBean.kmip.model.v2x1.structure.ProfileVersion;
import org.purpleBean.kmip.model.v2x1.type.ServerPort;
import org.purpleBean.kmip.model.v2x1.type.ServerUri;

public class ProfileInformationTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ProfileInformation,
        ProfileInformation.ProfileInformationBuilder> {

  public ProfileInformationTtlvDeserializer() {
    super(ProfileInformation.kmipTag, ProfileInformation.encodingType);
  }

  @Override
  protected ProfileInformation.ProfileInformationBuilder createBuilder() {
    return ProfileInformation.builder();
  }

  @Override
  protected void setValue(ProfileInformation.ProfileInformationBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.PROFILE_NAME ->
          builder.profileName(mapper.readValue(p, ProfileName.class));
      case KmipTag.Standard.PROFILE_VERSION ->
          builder.profileVersion(mapper.readValue(p, ProfileVersion.class));
      case KmipTag.Standard.SERVER_URI -> builder.serverUri(mapper.readValue(p, ServerUri.class));
      case KmipTag.Standard.SERVER_PORT ->
          builder.serverPort(mapper.readValue(p, ServerPort.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ProfileInformation build(ProfileInformation.ProfileInformationBuilder builder) {
    return builder.build();
  }
}