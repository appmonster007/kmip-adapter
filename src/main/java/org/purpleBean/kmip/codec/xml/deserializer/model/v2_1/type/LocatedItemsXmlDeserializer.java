package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.type.LocatedItems;

public class LocatedItemsXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<LocatedItems, LocatedItems.LocatedItemsBuilder> {

  public LocatedItemsXmlDeserializer() {
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