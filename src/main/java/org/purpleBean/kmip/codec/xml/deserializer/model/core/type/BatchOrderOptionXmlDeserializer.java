package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.BatchOrderOption;

public class BatchOrderOptionXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<BatchOrderOption,
        BatchOrderOption.BatchOrderOptionBuilder> {

  public BatchOrderOptionXmlDeserializer() {
    super(BatchOrderOption.kmipTag, BatchOrderOption.encodingType);
  }

  @Override
  protected BatchOrderOption.BatchOrderOptionBuilder createBuilder() {
    return BatchOrderOption.builder();
  }

  @Override
  protected void setValue(BatchOrderOption.BatchOrderOptionBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected BatchOrderOption build(BatchOrderOption.BatchOrderOptionBuilder builder) {
    return builder.build();
  }
}