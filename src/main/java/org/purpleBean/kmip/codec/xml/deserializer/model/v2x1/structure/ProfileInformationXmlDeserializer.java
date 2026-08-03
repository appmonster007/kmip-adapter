package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.ProfileName;
import org.purplebean.kmip.model.v2x1.structure.ProfileInformation;
import org.purplebean.kmip.model.v2x1.structure.ProfileVersion;
import org.purplebean.kmip.model.v2x1.type.ServerPort;
import org.purplebean.kmip.model.v2x1.type.ServerUri;

public class ProfileInformationXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ProfileInformation,
        ProfileInformation.ProfileInformationBuilder> {

  public ProfileInformationXmlDeserializer() {
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