package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.type.ServerPort;

public class ServerPortJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<ServerPort, ServerPort.ServerPortBuilder> {

  public ServerPortJsonDeserializer() {
    super(ServerPort.kmipTag, ServerPort.encodingType);
  }

  @Override
  protected ServerPort.ServerPortBuilder createBuilder() {
    return ServerPort.builder();
  }

  @Override
  protected void setValue(ServerPort.ServerPortBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected ServerPort build(ServerPort.ServerPortBuilder builder) {
    return builder.build();
  }
}