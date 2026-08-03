package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMinor;

public class ProtocolVersionMinorJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ProtocolVersionMinor,
        ProtocolVersionMinor.ProtocolVersionMinorBuilder> {

  public ProtocolVersionMinorJsonDeserializer() {
    super(ProtocolVersionMinor.kmipTag, ProtocolVersionMinor.encodingType);
  }

  @Override
  protected ProtocolVersionMinor.ProtocolVersionMinorBuilder createBuilder() {
    return ProtocolVersionMinor.builder();
  }

  @Override
  protected void setValue(ProtocolVersionMinor.ProtocolVersionMinorBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected ProtocolVersionMinor build(ProtocolVersionMinor.ProtocolVersionMinorBuilder builder) {
    return builder.build();
  }
}
