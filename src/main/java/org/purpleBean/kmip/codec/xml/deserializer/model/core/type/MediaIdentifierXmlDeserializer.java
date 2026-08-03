package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.MediaIdentifier;

public class MediaIdentifierXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<MediaIdentifier, MediaIdentifier.MediaIdentifierBuilder> {

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