package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.SignatureData;

public class SignatureDataXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<SignatureData, SignatureData.SignatureDataBuilder> {

  public SignatureDataXmlDeserializer() {
    super(SignatureData.kmipTag, SignatureData.encodingType);
  }

  @Override
  protected SignatureData.SignatureDataBuilder createBuilder() {
    return SignatureData.builder();
  }

  @Override
  protected void setValue(SignatureData.SignatureDataBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected SignatureData build(SignatureData.SignatureDataBuilder builder) {
    return builder.build();
  }
}