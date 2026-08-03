package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.type.ProfileVersionMajor;

public class ProfileVersionMajorJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ProfileVersionMajor,
        ProfileVersionMajor.ProfileVersionMajorBuilder> {

  public ProfileVersionMajorJsonDeserializer() {
    super(ProfileVersionMajor.kmipTag, ProfileVersionMajor.encodingType);
  }

  @Override
  protected ProfileVersionMajor.ProfileVersionMajorBuilder createBuilder() {
    return ProfileVersionMajor.builder();
  }

  @Override
  protected void setValue(ProfileVersionMajor.ProfileVersionMajorBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected ProfileVersionMajor build(ProfileVersionMajor.ProfileVersionMajorBuilder builder) {
    return builder.build();
  }
}