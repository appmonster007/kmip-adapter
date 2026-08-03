package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.structure.ProfileVersion;
import org.purpleBean.kmip.model.v2_1.type.ProfileVersionMajor;
import org.purpleBean.kmip.model.v2_1.type.ProfileVersionMinor;

public class ProfileVersionXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ProfileVersion, ProfileVersion.ProfileVersionBuilder> {

  public ProfileVersionXmlDeserializer() {
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