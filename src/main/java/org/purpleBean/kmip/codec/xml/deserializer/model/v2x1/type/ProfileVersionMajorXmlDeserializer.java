package org.purpleBean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2x1.type.ProfileVersionMajor;

public class ProfileVersionMajorXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ProfileVersionMajor,
        ProfileVersionMajor.ProfileVersionMajorBuilder> {

  public ProfileVersionMajorXmlDeserializer() {
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