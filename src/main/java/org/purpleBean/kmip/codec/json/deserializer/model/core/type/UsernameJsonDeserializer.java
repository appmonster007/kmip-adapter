package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.Username;

public class UsernameJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<Username, Username.UsernameBuilder> {

  public UsernameJsonDeserializer() {
    super(Username.kmipTag, Username.encodingType);
  }

  @Override
  protected Username.UsernameBuilder createBuilder() {
    return Username.builder();
  }

  @Override
  protected void setValue(Username.UsernameBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected Username build(Username.UsernameBuilder builder) {
    return builder.build();
  }
}
