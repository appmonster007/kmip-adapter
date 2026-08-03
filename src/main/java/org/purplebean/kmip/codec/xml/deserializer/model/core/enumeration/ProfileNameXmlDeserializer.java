package org.purplebean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.ProfileName;

/**
 * XML deserializer for {@link ProfileName}.
 */
public class ProfileNameXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<ProfileName, ProfileName.ProfileNameBuilder> {

  /**
   * Constructs a new {@link ProfileNameXmlDeserializer}.
   */
  public ProfileNameXmlDeserializer() {
    super(ProfileName.kmipTag, ProfileName.encodingType);
  }

  @Override
  protected ProfileName.ProfileNameBuilder createBuilder() {
    return ProfileName.builder();
  }

  @Override
  protected void setValue(ProfileName.ProfileNameBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ProfileName.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected ProfileName build(ProfileName.ProfileNameBuilder builder) {
    return builder.build();
  }
}