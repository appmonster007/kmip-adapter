package org.purpleBean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2x1.type.ServerUri;

public class ServerUriJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<ServerUri, ServerUri.ServerUriBuilder> {

  public ServerUriJsonDeserializer() {
    super(ServerUri.kmipTag, ServerUri.encodingType);
  }

  @Override
  protected ServerUri.ServerUriBuilder createBuilder() {
    return ServerUri.builder();
  }

  @Override
  protected void setValue(ServerUri.ServerUriBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected ServerUri build(ServerUri.ServerUriBuilder builder) {
    return builder.build();
  }
}