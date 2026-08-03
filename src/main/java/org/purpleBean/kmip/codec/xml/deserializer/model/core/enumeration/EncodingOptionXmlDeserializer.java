package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.EncodingOption;

public class EncodingOptionXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<EncodingOption, EncodingOption.EncodingOptionBuilder> {

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