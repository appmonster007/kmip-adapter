package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.ProfileVersionMajor;

/**
 * JSON deserializer for {@link ProfileVersionMajor}.
 */
public class ProfileVersionMajorJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ProfileVersionMajor,
        ProfileVersionMajor.ProfileVersionMajorBuilder> {

  /**
   * Constructs a new {@link ProfileVersionMajorJsonDeserializer}.
   */
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