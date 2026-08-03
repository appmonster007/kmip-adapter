package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.CryptographicLength;

public class CryptographicLengthXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CryptographicLength,
        CryptographicLength.CryptographicLengthBuilder> {

  public CryptographicLengthXmlDeserializer() {
    super(CryptographicLength.kmipTag, CryptographicLength.encodingType);
  }

  @Override
  protected CryptographicLength.CryptographicLengthBuilder createBuilder() {
    return CryptographicLength.builder();
  }

  @Override
  protected void setValue(CryptographicLength.CryptographicLengthBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected CryptographicLength build(CryptographicLength.CryptographicLengthBuilder builder) {
    return builder.build();
  }
}