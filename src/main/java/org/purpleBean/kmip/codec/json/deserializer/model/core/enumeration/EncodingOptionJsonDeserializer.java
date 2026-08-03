package org.purplebean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.EncodingOption;

public class EncodingOptionJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<EncodingOption, EncodingOption.EncodingOptionBuilder> {

  public EncodingOptionJsonDeserializer() {
    super(EncodingOption.kmipTag, EncodingOption.encodingType);
  }

  @Override
  protected EncodingOption.EncodingOptionBuilder createBuilder() {
    return EncodingOption.builder();
  }

  @Override
  protected void setValue(EncodingOption.EncodingOptionBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(EncodingOption.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected EncodingOption build(EncodingOption.EncodingOptionBuilder builder) {
    return builder.build();
  }
}
