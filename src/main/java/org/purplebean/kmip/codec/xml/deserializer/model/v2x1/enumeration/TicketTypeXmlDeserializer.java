package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.enumeration.TicketType;

public class TicketTypeXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<TicketType, TicketType.TicketTypeBuilder> {

  public TicketTypeXmlDeserializer() {
    super(TicketType.kmipTag, TicketType.encodingType);
  }

  @Override
  protected TicketType.TicketTypeBuilder createBuilder() {
    return TicketType.builder();
  }

  @Override
  protected void setValue(TicketType.TicketTypeBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(TicketType.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected TicketType build(TicketType.TicketTypeBuilder builder) {
    return builder.build();
  }
}