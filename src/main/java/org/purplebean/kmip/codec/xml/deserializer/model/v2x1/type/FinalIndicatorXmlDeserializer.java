package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.FinalIndicator;

/**
 * XML deserializer for {@link FinalIndicator}.
 */
public class FinalIndicatorXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<FinalIndicator, FinalIndicator.FinalIndicatorBuilder> {

  /**
   * Constructs a new {@link FinalIndicatorXmlDeserializer}.
   */
  public FinalIndicatorXmlDeserializer() {
    super(FinalIndicator.kmipTag, FinalIndicator.encodingType);
  }

  @Override
  protected FinalIndicator.FinalIndicatorBuilder createBuilder() {
    return FinalIndicator.builder();
  }

  @Override
  protected void setValue(FinalIndicator.FinalIndicatorBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected FinalIndicator build(FinalIndicator.FinalIndicatorBuilder builder) {
    return builder.build();
  }
}