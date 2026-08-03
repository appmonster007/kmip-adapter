package org.purpleBean.kmip.codec.xml.deserializer.model.v2x1.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2x1.enumeration.EndpointRole;

public class EndpointRoleXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<EndpointRole, EndpointRole.EndpointRoleBuilder> {

  public EndpointRoleXmlDeserializer() {
    super(EndpointRole.kmipTag, EndpointRole.encodingType);
  }

  @Override
  protected EndpointRole.EndpointRoleBuilder createBuilder() {
    return EndpointRole.builder();
  }

  @Override
  protected void setValue(EndpointRole.EndpointRoleBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(EndpointRole.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected EndpointRole build(EndpointRole.EndpointRoleBuilder builder) {
    return builder.build();
  }
}