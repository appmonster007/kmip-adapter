package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.ResultMessage;

public class ResultMessageJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ResultMessage, ResultMessage.ResultMessageBuilder> {

  public ResultMessageJsonDeserializer() {
    super(ResultMessage.kmipTag, ResultMessage.encodingType);
  }

  @Override
  protected ResultMessage.ResultMessageBuilder createBuilder() {
    return ResultMessage.builder();
  }

  @Override
  protected void setValue(ResultMessage.ResultMessageBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected ResultMessage build(ResultMessage.ResultMessageBuilder builder) {
    return builder.build();
  }
}
