package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.KeyRoleType;

public class KeyRoleTypeXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<KeyRoleType, KeyRoleType.KeyRoleTypeBuilder> {

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