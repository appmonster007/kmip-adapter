package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.structure.Ticket;
import org.purplebean.kmip.model.v2x1.structure.response.payload.LoginOpResponsePayload;

/**
 * XML deserializer for {@link LoginOpResponsePayload}.
 */
public class LoginOpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<LoginOpResponsePayload,
        LoginOpResponsePayload.LoginOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link LoginOpResponsePayloadXmlDeserializer}.
   */
  public LoginOpResponsePayloadXmlDeserializer() {
    super(LoginOpResponsePayload.kmipTag, LoginOpResponsePayload.encodingType);
  }

  @Override
  protected LoginOpResponsePayload.LoginOpResponsePayloadBuilder createBuilder() {
    return LoginOpResponsePayload.builder();
  }

  @Override
  protected void setValue(LoginOpResponsePayload.LoginOpResponsePayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.TICKET -> builder.ticket(ctxt.readValue(p, Ticket.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected LoginOpResponsePayload build(
      LoginOpResponsePayload.LoginOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}