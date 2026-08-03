package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.MediaIdentifier;

/**
 * XML deserializer for {@link MediaIdentifier}.
 */
public class MediaIdentifierXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<MediaIdentifier, MediaIdentifier.MediaIdentifierBuilder> {

  /**
   * Constructs a new {@link MediaIdentifierXmlDeserializer}.
   */
  public MediaIdentifierXmlDeserializer() {
    super(MediaIdentifier.kmipTag, MediaIdentifier.encodingType);
  }

  @Override
  protected MediaIdentifier.MediaIdentifierBuilder createBuilder() {
    return MediaIdentifier.builder();
  }

  @Override
  protected void setValue(MediaIdentifier.MediaIdentifierBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected MediaIdentifier build(MediaIdentifier.MediaIdentifierBuilder builder) {
    return builder.build();
  }
}