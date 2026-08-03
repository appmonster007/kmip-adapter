package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.type.BatchContinueCapability;

public class BatchContinueCapabilityJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<BatchContinueCapability,
        BatchContinueCapability.BatchContinueCapabilityBuilder> {

  public BatchContinueCapabilityJsonDeserializer() {
    super(BatchContinueCapability.kmipTag, BatchContinueCapability.encodingType);
  }

  @Override
  protected BatchContinueCapability.BatchContinueCapabilityBuilder createBuilder() {
    return BatchContinueCapability.builder();
  }

  @Override
  protected void setValue(BatchContinueCapability.BatchContinueCapabilityBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected BatchContinueCapability build(
      BatchContinueCapability.BatchContinueCapabilityBuilder builder) {
    return builder.build();
  }
}