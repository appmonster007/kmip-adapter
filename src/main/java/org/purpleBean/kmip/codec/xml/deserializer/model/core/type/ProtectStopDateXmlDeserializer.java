package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.time.OffsetDateTime;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.ProtectStopDate;

public class ProtectStopDateXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ProtectStopDate, ProtectStopDate.ProtectStopDateBuilder> {

  public ProtectStopDateXmlDeserializer() {
    super(ProtectStopDate.kmipTag, ProtectStopDate.encodingType);
  }

  @Override
  protected ProtectStopDate.ProtectStopDateBuilder createBuilder() {
    return ProtectStopDate.builder();
  }

  @Override
  protected void setValue(ProtectStopDate.ProtectStopDateBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, OffsetDateTime.class));
  }

  @Override
  protected ProtectStopDate build(ProtectStopDate.ProtectStopDateBuilder builder) {
    return builder.build();
  }
}