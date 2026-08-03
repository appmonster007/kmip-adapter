package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.CorrelationValue;

public class CorrelationValueXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CorrelationValue,
        CorrelationValue.CorrelationValueBuilder> {

  public CorrelationValueXmlDeserializer() {
    super(CorrelationValue.kmipTag, CorrelationValue.encodingType);
  }

  @Override
  protected CorrelationValue.CorrelationValueBuilder createBuilder() {
    return CorrelationValue.builder();
  }

  @Override
  protected void setValue(CorrelationValue.CorrelationValueBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected CorrelationValue build(CorrelationValue.CorrelationValueBuilder builder) {
    return builder.build();
  }
}