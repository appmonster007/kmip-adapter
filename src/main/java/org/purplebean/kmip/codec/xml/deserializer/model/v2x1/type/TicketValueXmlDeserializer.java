package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.TicketValue;

/**
 * XML deserializer for {@link TicketValue}.
 */
public class TicketValueXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<TicketValue, TicketValue.TicketValueBuilder> {

  /**
   * Constructs a new {@link TicketValueXmlDeserializer}.
   */
  public TicketValueXmlDeserializer() {
    super(TicketValue.kmipTag, TicketValue.encodingType);
  }

  @Override
  protected TicketValue.TicketValueBuilder createBuilder() {
    return TicketValue.builder();
  }

  @Override
  protected void setValue(TicketValue.TicketValueBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected TicketValue build(TicketValue.TicketValueBuilder builder) {
    return builder.build();
  }
}
