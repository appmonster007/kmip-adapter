package org.purplebean.kmip.codec.json.deserializer.model.v2x1.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.enumeration.TicketType;

/**
 * JSON deserializer for {@link TicketType}.
 */
public class TicketTypeJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<TicketType, TicketType.TicketTypeBuilder> {

  /**
   * Constructs a new {@link TicketTypeJsonDeserializer}.
   */
  public TicketTypeJsonDeserializer() {
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
