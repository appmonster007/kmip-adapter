package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.AlternativeNameValue;

/**
 * XML deserializer for {@link AlternativeNameValue}.
 */
public class AlternativeNameValueXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<AlternativeNameValue,
        AlternativeNameValue.AlternativeNameValueBuilder> {

  /**
   * Constructs a new {@link AlternativeNameValueXmlDeserializer}.
   */
  public AlternativeNameValueXmlDeserializer() {
    super(AlternativeNameValue.kmipTag, AlternativeNameValue.encodingType);
  }

  @Override
  protected AlternativeNameValue.AlternativeNameValueBuilder createBuilder() {
    return AlternativeNameValue.builder();
  }

  @Override
  protected void setValue(AlternativeNameValue.AlternativeNameValueBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected AlternativeNameValue build(AlternativeNameValue.AlternativeNameValueBuilder builder) {
    return builder.build();
  }
}