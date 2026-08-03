package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.type.OffsetItems;

public class OffsetItemsJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<OffsetItems, OffsetItems.OffsetItemsBuilder> {

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
