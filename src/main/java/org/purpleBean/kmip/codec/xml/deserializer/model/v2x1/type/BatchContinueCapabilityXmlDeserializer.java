package org.purpleBean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2x1.type.BatchContinueCapability;

public class BatchContinueCapabilityXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<BatchContinueCapability,
        BatchContinueCapability.BatchContinueCapabilityBuilder> {

  public BatchContinueCapabilityXmlDeserializer() {
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