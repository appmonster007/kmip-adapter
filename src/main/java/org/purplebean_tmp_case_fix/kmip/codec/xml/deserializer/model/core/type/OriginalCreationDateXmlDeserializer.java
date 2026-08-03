package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.time.OffsetDateTime;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.OriginalCreationDate;

public class OriginalCreationDateXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<OriginalCreationDate,
        OriginalCreationDate.OriginalCreationDateBuilder> {

  public OriginalCreationDateXmlDeserializer() {
    super(OriginalCreationDate.kmipTag, OriginalCreationDate.encodingType);
  }

  @Override
  protected OriginalCreationDate.OriginalCreationDateBuilder createBuilder() {
    return OriginalCreationDate.builder();
  }

  @Override
  protected void setValue(OriginalCreationDate.OriginalCreationDateBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, OffsetDateTime.class));
  }

  @Override
  protected OriginalCreationDate build(OriginalCreationDate.OriginalCreationDateBuilder builder) {
    return builder.build();
  }
}