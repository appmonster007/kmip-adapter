package org.purplebean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.ValidityIndicator;

/**
 * XML deserializer for {@link ValidityIndicator}.
 */
public class ValidityIndicatorXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ValidityIndicator,
        ValidityIndicator.ValidityIndicatorBuilder> {

  /**
   * Constructs a new {@link ValidityIndicatorXmlDeserializer}.
   */
  public ValidityIndicatorXmlDeserializer() {
    super(ValidityIndicator.kmipTag, ValidityIndicator.encodingType);
  }

  @Override
  protected ValidityIndicator.ValidityIndicatorBuilder createBuilder() {
    return ValidityIndicator.builder();
  }

  @Override
  protected void setValue(ValidityIndicator.ValidityIndicatorBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ValidityIndicator.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected ValidityIndicator build(ValidityIndicator.ValidityIndicatorBuilder builder) {
    return builder.build();
  }
}