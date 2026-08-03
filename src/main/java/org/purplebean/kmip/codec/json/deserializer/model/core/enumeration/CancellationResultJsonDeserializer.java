package org.purplebean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.CancellationResult;

public class CancellationResultJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CancellationResult,
        CancellationResult.CancellationResultBuilder> {

  public CancellationResultJsonDeserializer() {
    super(CancellationResult.kmipTag, CancellationResult.encodingType);
  }

  @Override
  protected CancellationResult.CancellationResultBuilder createBuilder() {
    return CancellationResult.builder();
  }

  @Override
  protected void setValue(CancellationResult.CancellationResultBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(CancellationResult.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected CancellationResult build(CancellationResult.CancellationResultBuilder builder) {
    return builder.build();
  }
}
