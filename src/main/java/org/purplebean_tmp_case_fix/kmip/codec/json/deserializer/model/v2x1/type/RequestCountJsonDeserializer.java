package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.RequestCount;

public class RequestCountJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<RequestCount, RequestCount.RequestCountBuilder> {

  public RequestCountJsonDeserializer() {
    super(RequestCount.kmipTag, RequestCount.encodingType);
  }

  @Override
  protected RequestCount.RequestCountBuilder createBuilder() {
    return RequestCount.builder();
  }

  @Override
  protected void setValue(RequestCount.RequestCountBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected RequestCount build(RequestCount.RequestCountBuilder builder) {
    return builder.build();
  }
}
