package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.Fresh;

public class FreshXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<Fresh, Fresh.FreshBuilder> {

  public FreshXmlDeserializer() {
    super(Fresh.kmipTag, Fresh.encodingType);
  }

  @Override
  protected Fresh.FreshBuilder createBuilder() {
    return Fresh.builder();
  }

  @Override
  protected void setValue(Fresh.FreshBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected Fresh build(Fresh.FreshBuilder builder) {
    return builder.build();
  }
}