package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.LocatedItems;

/**
 * JSON deserializer for {@link LocatedItems}.
 */
public class LocatedItemsJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<LocatedItems, LocatedItems.LocatedItemsBuilder> {

  /**
   * Constructs a new {@link LocatedItemsJsonDeserializer}.
   */
  public LocatedItemsJsonDeserializer() {
    super(LocatedItems.kmipTag, LocatedItems.encodingType);
  }

  @Override
  protected LocatedItems.LocatedItemsBuilder createBuilder() {
    return LocatedItems.builder();
  }

  @Override
  protected void setValue(LocatedItems.LocatedItemsBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected LocatedItems build(LocatedItems.LocatedItemsBuilder builder) {
    return builder.build();
  }
}