package org.purplebean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.ClientRegistrationMethod;

/**
 * XML deserializer for {@link ClientRegistrationMethod}.
 */
public class ClientRegistrationMethodXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ClientRegistrationMethod,
        ClientRegistrationMethod.ClientRegistrationMethodBuilder> {

  /**
   * Constructs a new {@link ClientRegistrationMethodXmlDeserializer}.
   */
  public ClientRegistrationMethodXmlDeserializer() {
    super(ClientRegistrationMethod.kmipTag, ClientRegistrationMethod.encodingType);
  }

  @Override
  protected ClientRegistrationMethod.ClientRegistrationMethodBuilder createBuilder() {
    return ClientRegistrationMethod.builder();
  }

  @Override
  protected void setValue(ClientRegistrationMethod.ClientRegistrationMethodBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ClientRegistrationMethod.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected ClientRegistrationMethod build(
      ClientRegistrationMethod.ClientRegistrationMethodBuilder builder) {
    return builder.build();
  }
}