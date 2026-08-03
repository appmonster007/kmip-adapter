package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.ReplaceExisting;

public class ReplaceExistingXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ReplaceExisting, ReplaceExisting.ReplaceExistingBuilder> {

  public ReplaceExistingXmlDeserializer() {
    super(ReplaceExisting.kmipTag, ReplaceExisting.encodingType);
  }

  @Override
  protected ReplaceExisting.ReplaceExistingBuilder createBuilder() {
    return ReplaceExisting.builder();
  }

  @Override
  protected void setValue(ReplaceExisting.ReplaceExistingBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected ReplaceExisting build(ReplaceExisting.ReplaceExistingBuilder builder) {
    return builder.build();
  }
}