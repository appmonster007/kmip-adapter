package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.IterationCount;

public class IterationCountJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<IterationCount, IterationCount.IterationCountBuilder> {

  public IterationCountJsonDeserializer() {
    super(IterationCount.kmipTag, IterationCount.encodingType);
  }

  @Override
  protected IterationCount.IterationCountBuilder createBuilder() {
    return IterationCount.builder();
  }

  @Override
  protected void setValue(IterationCount.IterationCountBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected IterationCount build(IterationCount.IterationCountBuilder builder) {
    return builder.build();
  }
}
