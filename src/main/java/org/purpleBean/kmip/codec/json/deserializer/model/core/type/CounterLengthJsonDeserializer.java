package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.CounterLength;

public class CounterLengthJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CounterLength, CounterLength.CounterLengthBuilder> {

  public CounterLengthJsonDeserializer() {
    super(CounterLength.kmipTag, CounterLength.encodingType);
  }

  @Override
  protected CounterLength.CounterLengthBuilder createBuilder() {
    return CounterLength.builder();
  }

  @Override
  protected void setValue(CounterLength.CounterLengthBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected CounterLength build(CounterLength.CounterLengthBuilder builder) {
    return builder.build();
  }
}
