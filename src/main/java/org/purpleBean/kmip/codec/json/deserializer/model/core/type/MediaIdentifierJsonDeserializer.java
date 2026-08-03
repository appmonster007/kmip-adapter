package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.MediaIdentifier;

public class MediaIdentifierJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<MediaIdentifier, MediaIdentifier.MediaIdentifierBuilder> {

  public MediaIdentifierJsonDeserializer() {
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
