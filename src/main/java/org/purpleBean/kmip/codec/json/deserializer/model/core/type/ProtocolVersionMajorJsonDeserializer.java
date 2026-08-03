package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMajor;

public class ProtocolVersionMajorJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ProtocolVersionMajor,
        ProtocolVersionMajor.ProtocolVersionMajorBuilder> {

  public ProtocolVersionMajorJsonDeserializer() {
    super(ProtocolVersionMajor.kmipTag, ProtocolVersionMajor.encodingType);
  }

  @Override
  protected ProtocolVersionMajor.ProtocolVersionMajorBuilder createBuilder() {
    return ProtocolVersionMajor.builder();
  }

  @Override
  protected void setValue(ProtocolVersionMajor.ProtocolVersionMajorBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected ProtocolVersionMajor build(ProtocolVersionMajor.ProtocolVersionMajorBuilder builder) {
    return builder.build();
  }
}
