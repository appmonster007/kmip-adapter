package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.NetworkIdentifier;

/**
 * JSON deserializer for {@link NetworkIdentifier}.
 */
public class NetworkIdentifierJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<NetworkIdentifier,
        NetworkIdentifier.NetworkIdentifierBuilder> {

  /**
   * Constructs a new {@link NetworkIdentifierJsonDeserializer}.
   */
  public NetworkIdentifierJsonDeserializer() {
    super(NetworkIdentifier.kmipTag, NetworkIdentifier.encodingType);
  }

  @Override
  protected NetworkIdentifier.NetworkIdentifierBuilder createBuilder() {
    return NetworkIdentifier.builder();
  }

  @Override
  protected void setValue(NetworkIdentifier.NetworkIdentifierBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected NetworkIdentifier build(NetworkIdentifier.NetworkIdentifierBuilder builder) {
    return builder.build();
  }
}
