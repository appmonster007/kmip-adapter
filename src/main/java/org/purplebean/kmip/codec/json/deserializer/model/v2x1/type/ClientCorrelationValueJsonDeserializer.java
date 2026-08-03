package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.ClientCorrelationValue;

/**
 * JSON deserializer for {@link ClientCorrelationValue}.
 */
public class ClientCorrelationValueJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ClientCorrelationValue,
        ClientCorrelationValue.ClientCorrelationValueBuilder> {

  /**
   * Constructs a new {@link ClientCorrelationValueJsonDeserializer}.
   */
  public ClientCorrelationValueJsonDeserializer() {
    super(ClientCorrelationValue.kmipTag, ClientCorrelationValue.encodingType);
  }

  @Override
  protected ClientCorrelationValue.ClientCorrelationValueBuilder createBuilder() {
    return ClientCorrelationValue.builder();
  }

  @Override
  protected void setValue(ClientCorrelationValue.ClientCorrelationValueBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected ClientCorrelationValue build(
      ClientCorrelationValue.ClientCorrelationValueBuilder builder) {
    return builder.build();
  }
}