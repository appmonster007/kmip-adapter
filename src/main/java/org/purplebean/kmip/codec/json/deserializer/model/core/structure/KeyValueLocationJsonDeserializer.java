package org.purplebean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.KeyValueLocationType;
import org.purplebean.kmip.model.core.structure.KeyValueLocation;
import org.purplebean.kmip.model.core.type.KeyValueLocationValue;

public class KeyValueLocationJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<KeyValueLocation,
        KeyValueLocation.KeyValueLocationBuilder> {

  public KeyValueLocationJsonDeserializer() {
    super(KeyValueLocation.kmipTag, KeyValueLocation.encodingType);
  }

  @Override
  protected KeyValueLocation.KeyValueLocationBuilder createBuilder() {
    return KeyValueLocation.builder();
  }

  @Override
  protected void setValue(KeyValueLocation.KeyValueLocationBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.KEY_VALUE_LOCATION_TYPE ->
          builder.keyValueLocationType(ctxt.readValue(p, KeyValueLocationType.class));
      case KmipTag.Standard.KEY_VALUE_LOCATION_VALUE ->
          builder.keyValueLocationValue(ctxt.readValue(p, KeyValueLocationValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected KeyValueLocation build(KeyValueLocation.KeyValueLocationBuilder builder) {
    return builder.build();
  }
}
