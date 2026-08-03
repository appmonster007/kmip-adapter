package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.BatchCount;

public class BatchCountJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<BatchCount, BatchCount.BatchCountBuilder> {

  public BatchCountJsonDeserializer() {
    super(BatchCount.kmipTag, BatchCount.encodingType);
  }

  @Override
  protected BatchCount.BatchCountBuilder createBuilder() {
    return BatchCount.builder();
  }

  @Override
  protected void setValue(BatchCount.BatchCountBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected BatchCount build(BatchCount.BatchCountBuilder builder) {
    return builder.build();
  }
}
