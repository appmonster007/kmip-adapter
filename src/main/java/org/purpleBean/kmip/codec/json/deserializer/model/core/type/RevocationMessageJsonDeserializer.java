package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.RevocationMessage;

public class RevocationMessageJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<RevocationMessage,
        RevocationMessage.RevocationMessageBuilder> {

  public RevocationMessageJsonDeserializer() {
    super(RevocationMessage.kmipTag, RevocationMessage.encodingType);
  }

  @Override
  protected RevocationMessage.RevocationMessageBuilder createBuilder() {
    return RevocationMessage.builder();
  }

  @Override
  protected void setValue(RevocationMessage.RevocationMessageBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected RevocationMessage build(RevocationMessage.RevocationMessageBuilder builder) {
    return builder.build();
  }
}
