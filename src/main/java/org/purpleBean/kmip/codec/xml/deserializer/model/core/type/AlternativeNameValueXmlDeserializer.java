package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AlternativeNameValue;

public class AlternativeNameValueXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<AlternativeNameValue,
        AlternativeNameValue.AlternativeNameValueBuilder> {

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