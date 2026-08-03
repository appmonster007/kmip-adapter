package org.purplebean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.PaddingMethod;

/**
 * XML deserializer for {@link PaddingMethod}.
 */
public class PaddingMethodXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<PaddingMethod, PaddingMethod.PaddingMethodBuilder> {

  /**
   * Constructs a new {@link PaddingMethodXmlDeserializer}.
   */
  public PaddingMethodXmlDeserializer() {
    super(PaddingMethod.kmipTag, PaddingMethod.encodingType);
  }

  @Override
  protected PaddingMethod.PaddingMethodBuilder createBuilder() {
    return PaddingMethod.builder();
  }

  @Override
  protected void setValue(PaddingMethod.PaddingMethodBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(PaddingMethod.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected PaddingMethod build(PaddingMethod.PaddingMethodBuilder builder) {
    return builder.build();
  }
}