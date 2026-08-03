package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.ServerUri;

/**
 * XML deserializer for {@link ServerUri}.
 */
public class ServerUriXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<ServerUri, ServerUri.ServerUriBuilder> {

  /**
   * Constructs a new {@link ServerUriXmlDeserializer}.
   */
  public ServerUriXmlDeserializer() {
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