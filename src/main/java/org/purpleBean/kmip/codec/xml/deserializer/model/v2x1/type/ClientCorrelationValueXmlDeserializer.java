package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.ClientCorrelationValue;

public class ClientCorrelationValueXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ClientCorrelationValue,
        ClientCorrelationValue.ClientCorrelationValueBuilder> {

  public ClientCorrelationValueXmlDeserializer() {
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