package org.purplebean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.State;

public class StateXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<State, State.StateBuilder> {

  public StateXmlDeserializer() {
    super(State.kmipTag, State.encodingType);
  }

  @Override
  protected State.StateBuilder createBuilder() {
    return State.builder();
  }

  @Override
  protected void setValue(State.StateBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(State.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected State build(State.StateBuilder builder) {
    return builder.build();
  }
}