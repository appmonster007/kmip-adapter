package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ProfileName;
import org.purpleBean.kmip.model.v2_1.structure.ProfileInformation;
import org.purpleBean.kmip.model.v2_1.structure.ProfileVersion;
import org.purpleBean.kmip.model.v2_1.type.ServerPort;
import org.purpleBean.kmip.model.v2_1.type.ServerUri;

public class ProfileInformationJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ProfileInformation,
        ProfileInformation.ProfileInformationBuilder> {

  public ProfileInformationJsonDeserializer() {
    super(ProfileInformation.kmipTag, ProfileInformation.encodingType);
  }

  @Override
  protected ProfileInformation.ProfileInformationBuilder createBuilder() {
    return ProfileInformation.builder();
  }

  @Override
  protected void setValue(ProfileInformation.ProfileInformationBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.PROFILE_NAME ->
          builder.profileName(ctxt.readValue(p, ProfileName.class));
      case KmipTag.Standard.PROFILE_VERSION ->
          builder.profileVersion(ctxt.readValue(p, ProfileVersion.class));
      case KmipTag.Standard.SERVER_URI -> builder.serverUri(ctxt.readValue(p, ServerUri.class));
      case KmipTag.Standard.SERVER_PORT -> builder.serverPort(ctxt.readValue(p, ServerPort.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ProfileInformation build(ProfileInformation.ProfileInformationBuilder builder) {
    return builder.build();
  }
}