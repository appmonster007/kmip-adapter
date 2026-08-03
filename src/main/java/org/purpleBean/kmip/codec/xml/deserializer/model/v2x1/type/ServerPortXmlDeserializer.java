package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.ServerPort;

public class ServerPortXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<ServerPort, ServerPort.ServerPortBuilder> {

  public ServerPortXmlDeserializer() {
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