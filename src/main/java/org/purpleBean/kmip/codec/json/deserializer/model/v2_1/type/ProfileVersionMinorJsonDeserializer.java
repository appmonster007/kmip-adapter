package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.type.ProfileVersionMinor;

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