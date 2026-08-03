package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.MACSignature;

public class MACSignatureXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<MACSignature, MACSignature.MACSignatureBuilder> {

  public MACSignatureXmlDeserializer() {
    super(MACSignature.kmipTag, MACSignature.encodingType);
  }

  @Override
  protected MACSignature.MACSignatureBuilder createBuilder() {
    return MACSignature.builder();
  }

  @Override
  protected void setValue(MACSignature.MACSignatureBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected MACSignature build(MACSignature.MACSignatureBuilder builder) {
    return builder.build();
  }
}