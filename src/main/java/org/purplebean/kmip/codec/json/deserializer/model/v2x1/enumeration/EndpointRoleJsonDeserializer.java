package org.purplebean.kmip.codec.json.deserializer.model.v2x1.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.enumeration.EndpointRole;

/**
 * JSON deserializer for {@link EndpointRole}.
 */
public class EndpointRoleJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<EndpointRole, EndpointRole.EndpointRoleBuilder> {

  /**
   * Constructs a new {@link EndpointRoleJsonDeserializer}.
   */
  public EndpointRoleJsonDeserializer() {
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
