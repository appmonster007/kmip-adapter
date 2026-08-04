package org.purplebean.kmip.codec.json.deserializer.model.v3x0.structure.link;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v3x0.structure.link.NextLink;

/**
 * JSON deserializer for {@link NextLink}.
 */
public class NextLinkJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<NextLink, NextLink.NextLinkBuilder> {

  /**
   * Constructs a new {@link NextLinkJsonDeserializer}.
   */
  public NextLinkJsonDeserializer() {
    super(NextLink.kmipTag, NextLink.encodingType);
  }

  @Override
  protected NextLink.NextLinkBuilder createBuilder() {
    return NextLink.builder();
  }

  @Override
  protected void setValue(NextLink.NextLinkBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected NextLink build(NextLink.NextLinkBuilder builder) {
    return builder.build();
  }
}
