package org.purplebean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.EncodingOption;

/**
 * XML deserializer for {@link EncodingOption}.
 */
public class EncodingOptionXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<EncodingOption, EncodingOption.EncodingOptionBuilder> {

  /**
   * Constructs a new {@link EncodingOptionXmlDeserializer}.
   */
  public EncodingOptionXmlDeserializer() {
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