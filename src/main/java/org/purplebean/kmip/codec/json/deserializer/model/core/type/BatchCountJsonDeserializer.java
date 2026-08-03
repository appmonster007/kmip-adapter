package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.BatchCount;

/**
 * JSON deserializer for {@link BatchCount}.
 */
public class BatchCountJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<BatchCount, BatchCount.BatchCountBuilder> {

  /**
   * Constructs a new {@link BatchCountJsonDeserializer}.
   */
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
