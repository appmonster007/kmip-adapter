package org.purpleBean.kmip.codec.xml.deserializer.model.v2x1.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2x1.enumeration.ItemType;

public class ItemTypeXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<ItemType, ItemType.ItemTypeBuilder> {

  public ItemTypeXmlDeserializer() {
    super(ItemType.kmipTag, ItemType.encodingType);
  }

  @Override
  protected ItemType.ItemTypeBuilder createBuilder() {
    return ItemType.builder();
  }

  @Override
  protected void setValue(ItemType.ItemTypeBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ItemType.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected ItemType build(ItemType.ItemTypeBuilder builder) {
    return builder.build();
  }
}