package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.SplitKeyThreshold;

/**
 * XML deserializer for {@link SplitKeyThreshold}.
 */
public class SplitKeyThresholdXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<SplitKeyThreshold,
        SplitKeyThreshold.SplitKeyThresholdBuilder> {

  /**
   * Constructs a new {@link SplitKeyThresholdXmlDeserializer}.
   */
  public SplitKeyThresholdXmlDeserializer() {
    super(SplitKeyThreshold.kmipTag, SplitKeyThreshold.encodingType);
  }

  @Override
  protected SplitKeyThreshold.SplitKeyThresholdBuilder createBuilder() {
    return SplitKeyThreshold.builder();
  }

  @Override
  protected void setValue(SplitKeyThreshold.SplitKeyThresholdBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected SplitKeyThreshold build(SplitKeyThreshold.SplitKeyThresholdBuilder builder) {
    return builder.build();
  }
}