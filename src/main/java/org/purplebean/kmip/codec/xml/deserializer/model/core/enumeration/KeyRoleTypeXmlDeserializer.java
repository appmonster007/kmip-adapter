package org.purplebean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.KeyRoleType;

/**
 * XML deserializer for {@link KeyRoleType}.
 */
public class KeyRoleTypeXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<KeyRoleType, KeyRoleType.KeyRoleTypeBuilder> {

  /**
   * Constructs a new {@link KeyRoleTypeXmlDeserializer}.
   */
  public KeyRoleTypeXmlDeserializer() {
    super(KeyRoleType.kmipTag, KeyRoleType.encodingType);
  }

  @Override
  protected KeyRoleType.KeyRoleTypeBuilder createBuilder() {
    return KeyRoleType.builder();
  }

  @Override
  protected void setValue(KeyRoleType.KeyRoleTypeBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(KeyRoleType.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected KeyRoleType build(KeyRoleType.KeyRoleTypeBuilder builder) {
    return builder.build();
  }
}