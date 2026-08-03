package org.purpleBean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2x1.type.BatchUndoCapability;

public class BatchUndoCapabilityJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<BatchUndoCapability,
        BatchUndoCapability.BatchUndoCapabilityBuilder> {

  public BatchUndoCapabilityJsonDeserializer() {
    super(BatchUndoCapability.kmipTag, BatchUndoCapability.encodingType);
  }

  @Override
  protected BatchUndoCapability.BatchUndoCapabilityBuilder createBuilder() {
    return BatchUndoCapability.builder();
  }

  @Override
  protected void setValue(BatchUndoCapability.BatchUndoCapabilityBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected BatchUndoCapability build(BatchUndoCapability.BatchUndoCapabilityBuilder builder) {
    return builder.build();
  }
}