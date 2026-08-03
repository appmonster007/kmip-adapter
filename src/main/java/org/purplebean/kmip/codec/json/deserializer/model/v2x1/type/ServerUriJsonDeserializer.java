package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.ServerUri;

/**
 * JSON deserializer for {@link ServerUri}.
 */
public class ServerUriJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<ServerUri, ServerUri.ServerUriBuilder> {

  /**
   * Constructs a new {@link ServerUriJsonDeserializer}.
   */
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