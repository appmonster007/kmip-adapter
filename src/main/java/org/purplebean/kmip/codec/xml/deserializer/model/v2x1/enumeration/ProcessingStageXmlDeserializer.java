package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.enumeration.ProcessingStage;

/**
 * XML deserializer for {@link ProcessingStage}.
 */
public class ProcessingStageXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ProcessingStage, ProcessingStage.ProcessingStageBuilder> {

  /**
   * Constructs a new {@link ProcessingStageXmlDeserializer}.
   */
  public ProcessingStageXmlDeserializer() {
    super(ProcessingStage.kmipTag, ProcessingStage.encodingType);
  }

  @Override
  protected ProcessingStage.ProcessingStageBuilder createBuilder() {
    return ProcessingStage.builder();
  }

  @Override
  protected void setValue(ProcessingStage.ProcessingStageBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ProcessingStage.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected ProcessingStage build(ProcessingStage.ProcessingStageBuilder builder) {
    return builder.build();
  }
}