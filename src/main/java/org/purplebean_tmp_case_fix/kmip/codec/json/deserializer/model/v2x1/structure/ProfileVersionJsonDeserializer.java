package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.structure.ProfileVersion;
import org.purplebean.kmip.model.v2x1.type.ProfileVersionMajor;
import org.purplebean.kmip.model.v2x1.type.ProfileVersionMinor;

public class ProfileVersionJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ProfileVersion, ProfileVersion.ProfileVersionBuilder> {

  public ProfileVersionJsonDeserializer() {
    super(ProfileVersion.kmipTag, ProfileVersion.encodingType);
  }

  @Override
  protected ProfileVersion.ProfileVersionBuilder createBuilder() {
    return ProfileVersion.builder();
  }

  @Override
  protected void setValue(ProfileVersion.ProfileVersionBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.PROFILE_VERSION_MAJOR ->
          builder.profileVersionMajor(ctxt.readValue(p, ProfileVersionMajor.class));
      case KmipTag.Standard.PROFILE_VERSION_MINOR ->
          builder.profileVersionMinor(ctxt.readValue(p, ProfileVersionMinor.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ProfileVersion build(ProfileVersion.ProfileVersionBuilder builder) {
    return builder.build();
  }
}