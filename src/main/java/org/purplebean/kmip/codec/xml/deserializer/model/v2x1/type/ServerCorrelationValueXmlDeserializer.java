package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.ServerCorrelationValue;

/**
 * XML deserializer for {@link ServerCorrelationValue}.
 */
public class ServerCorrelationValueXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ServerCorrelationValue,
        ServerCorrelationValue.ServerCorrelationValueBuilder> {

  /**
   * Constructs a new {@link ServerCorrelationValueXmlDeserializer}.
   */
  public ServerCorrelationValueXmlDeserializer() {
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