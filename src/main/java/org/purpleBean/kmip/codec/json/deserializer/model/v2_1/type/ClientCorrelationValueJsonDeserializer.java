package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.type.ClientCorrelationValue;

public class ClientCorrelationValueJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ClientCorrelationValue,
        ClientCorrelationValue.ClientCorrelationValueBuilder> {

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