package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.MacData;

public class MacDataXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<MacData, MacData.MacDataBuilder> {

  public MacDataXmlDeserializer() {
    super(MacData.kmipTag, MacData.encodingType);
  }

  @Override
  protected MacData.MacDataBuilder createBuilder() {
    return MacData.builder();
  }

  @Override
  protected void setValue(MacData.MacDataBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected MacData build(MacData.MacDataBuilder builder) {
    return builder.build();
  }
}