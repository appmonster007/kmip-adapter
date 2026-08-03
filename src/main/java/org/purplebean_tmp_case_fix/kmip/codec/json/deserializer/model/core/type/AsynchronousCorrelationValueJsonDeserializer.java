package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.AsynchronousCorrelationValue;

public class AsynchronousCorrelationValueJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<AsynchronousCorrelationValue,
        AsynchronousCorrelationValue.AsynchronousCorrelationValueBuilder> {

  public AsynchronousCorrelationValueJsonDeserializer() {
    super(AsynchronousCorrelationValue.kmipTag, AsynchronousCorrelationValue.encodingType);
  }

  @Override
  protected AsynchronousCorrelationValue.AsynchronousCorrelationValueBuilder createBuilder() {
    return AsynchronousCorrelationValue.builder();
  }

  @Override
  protected void setValue(AsynchronousCorrelationValue.AsynchronousCorrelationValueBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected AsynchronousCorrelationValue build(
      AsynchronousCorrelationValue.AsynchronousCorrelationValueBuilder builder) {
    return builder.build();
  }
}
