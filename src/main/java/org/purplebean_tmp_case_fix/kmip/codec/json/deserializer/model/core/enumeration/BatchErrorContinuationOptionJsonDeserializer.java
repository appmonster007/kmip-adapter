package org.purplebean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.BatchErrorContinuationOption;

public class BatchErrorContinuationOptionJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<BatchErrorContinuationOption,
        BatchErrorContinuationOption.BatchErrorContinuationOptionBuilder> {

  public BatchErrorContinuationOptionJsonDeserializer() {
    super(BatchErrorContinuationOption.kmipTag, BatchErrorContinuationOption.encodingType);
  }

  @Override
  protected BatchErrorContinuationOption.BatchErrorContinuationOptionBuilder createBuilder() {
    return BatchErrorContinuationOption.builder();
  }

  @Override
  protected void setValue(BatchErrorContinuationOption.BatchErrorContinuationOptionBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(BatchErrorContinuationOption.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected BatchErrorContinuationOption build(
      BatchErrorContinuationOption.BatchErrorContinuationOptionBuilder builder) {
    return builder.build();
  }
}
