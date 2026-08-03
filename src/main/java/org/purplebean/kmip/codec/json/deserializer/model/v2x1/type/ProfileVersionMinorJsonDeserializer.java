package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.ProfileVersionMinor;

public class ProfileVersionMinorJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ProfileVersionMinor,
        ProfileVersionMinor.ProfileVersionMinorBuilder> {

  public ProfileVersionMinorJsonDeserializer() {
    super(ProfileVersionMinor.kmipTag, ProfileVersionMinor.encodingType);
  }

  @Override
  protected ProfileVersionMinor.ProfileVersionMinorBuilder createBuilder() {
    return ProfileVersionMinor.builder();
  }

  @Override
  protected void setValue(ProfileVersionMinor.ProfileVersionMinorBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected ProfileVersionMinor build(ProfileVersionMinor.ProfileVersionMinorBuilder builder) {
    return builder.build();
  }
}