package org.purplebean.kmip.codec.xml.deserializer.model.v3x0.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v3x0.type.HashedPasswordUsername;

/**
 * XML deserializer for {@link HashedPasswordUsername}.
 */
public class HashedPasswordUsernameXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<HashedPasswordUsername,
        HashedPasswordUsername.HashedPasswordUsernameBuilder> {

  /**
   * Constructs a new {@link HashedPasswordUsernameXmlDeserializer}.
   */
  public HashedPasswordUsernameXmlDeserializer() {
    super(HashedPasswordUsername.kmipTag, HashedPasswordUsername.encodingType);
  }

  @Override
  protected HashedPasswordUsername.HashedPasswordUsernameBuilder createBuilder() {
    return HashedPasswordUsername.builder();
  }

  @Override
  protected void setValue(HashedPasswordUsername.HashedPasswordUsernameBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected HashedPasswordUsername build(
      HashedPasswordUsername.HashedPasswordUsernameBuilder builder) {
    return builder.build();
  }
}
