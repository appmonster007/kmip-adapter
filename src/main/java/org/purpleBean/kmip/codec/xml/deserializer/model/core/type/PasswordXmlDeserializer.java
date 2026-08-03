package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.Password;

public class PasswordXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<Password, Password.PasswordBuilder> {

  public PasswordXmlDeserializer() {
    super(Password.kmipTag, Password.encodingType);
  }

  @Override
  protected Password.PasswordBuilder createBuilder() {
    return Password.builder();
  }

  @Override
  protected void setValue(Password.PasswordBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected Password build(Password.PasswordBuilder builder) {
    return builder.build();
  }
}