package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.structure.Objects;

public class ObjectsJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<Objects, Objects.ObjectsBuilder> {

  public ObjectsJsonDeserializer() {
    super(Objects.kmipTag, Objects.encodingType);
  }

  @Override
  protected Objects.ObjectsBuilder createBuilder() {
    return Objects.builder();
  }

  @Override
  protected void setValue(Objects.ObjectsBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(
          ctxt.readValue(p, org.purplebean.kmip.model.core.type.UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Objects build(Objects.ObjectsBuilder builder) {
    return builder.build();
  }
}