package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.Extractable;

public class ExtractableXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<Extractable, Extractable.ExtractableBuilder> {

  public ExtractableXmlDeserializer() {
    super(Extractable.kmipTag, Extractable.encodingType);
  }

  @Override
  protected Extractable.ExtractableBuilder createBuilder() {
    return Extractable.builder();
  }

  @Override
  protected void setValue(Extractable.ExtractableBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected Extractable build(Extractable.ExtractableBuilder builder) {
    return builder.build();
  }
}