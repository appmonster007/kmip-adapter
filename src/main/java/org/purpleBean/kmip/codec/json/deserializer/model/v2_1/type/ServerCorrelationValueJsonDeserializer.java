package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.type.ServerCorrelationValue;

public class ServerCorrelationValueJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ServerCorrelationValue,
        ServerCorrelationValue.ServerCorrelationValueBuilder> {

  public ServerCorrelationValueJsonDeserializer() {
    super(ServerCorrelationValue.kmipTag, ServerCorrelationValue.encodingType);
  }

  @Override
  protected ServerCorrelationValue.ServerCorrelationValueBuilder createBuilder() {
    return ServerCorrelationValue.builder();
  }

  @Override
  protected void setValue(ServerCorrelationValue.ServerCorrelationValueBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected ServerCorrelationValue build(
      ServerCorrelationValue.ServerCorrelationValueBuilder builder) {
    return builder.build();
  }
}