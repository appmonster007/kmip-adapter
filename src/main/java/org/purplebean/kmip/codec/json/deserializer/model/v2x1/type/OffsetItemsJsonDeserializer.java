package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.OffsetItems;

/**
 * JSON deserializer for {@link OffsetItems}.
 */
public class OffsetItemsJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<OffsetItems, OffsetItems.OffsetItemsBuilder> {

  /**
   * Constructs a new {@link OffsetItemsJsonDeserializer}.
   */
  public OffsetItemsJsonDeserializer() {
    super(OffsetItems.kmipTag, OffsetItems.encodingType);
  }

  @Override
  protected OffsetItems.OffsetItemsBuilder createBuilder() {
    return OffsetItems.builder();
  }

  @Override
  protected void setValue(OffsetItems.OffsetItemsBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected OffsetItems build(OffsetItems.OffsetItemsBuilder builder) {
    return builder.build();
  }
}
