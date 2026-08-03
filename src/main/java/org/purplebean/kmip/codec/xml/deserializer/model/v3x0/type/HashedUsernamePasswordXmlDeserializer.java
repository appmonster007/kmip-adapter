package org.purplebean.kmip.codec.xml.deserializer.model.v3x0.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v3x0.type.HashedUsernamePassword;

/**
 * XML deserializer for {@link HashedUsernamePassword}.
 */
public class HashedUsernamePasswordXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<HashedUsernamePassword,
        HashedUsernamePassword.HashedUsernamePasswordBuilder> {

  /**
   * Constructs a new {@link HashedUsernamePasswordXmlDeserializer}.
   */
  public HashedUsernamePasswordXmlDeserializer() {
    super(HashedUsernamePassword.kmipTag, HashedUsernamePassword.encodingType);
  }

  @Override
  protected HashedUsernamePassword.HashedUsernamePasswordBuilder createBuilder() {
    return HashedUsernamePassword.builder();
  }

  @Override
  protected void setValue(HashedUsernamePassword.HashedUsernamePasswordBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected HashedUsernamePassword build(
      HashedUsernamePassword.HashedUsernamePasswordBuilder builder) {
    return builder.build();
  }
}
