package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.MaximumItems;

/**
 * XML deserializer for {@link MaximumItems}.
 */
public class MaximumItemsXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<MaximumItems, MaximumItems.MaximumItemsBuilder> {

  /**
   * Constructs a new {@link MaximumItemsXmlDeserializer}.
   */
  public MaximumItemsXmlDeserializer() {
    super(MaximumItems.kmipTag, MaximumItems.encodingType);
  }

  @Override
  protected MaximumItems.MaximumItemsBuilder createBuilder() {
    return MaximumItems.builder();
  }

  @Override
  protected void setValue(MaximumItems.MaximumItemsBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected MaximumItems build(MaximumItems.MaximumItemsBuilder builder) {
    return builder.build();
  }
}