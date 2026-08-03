package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.DerivationData;

public class DerivationDataJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<DerivationData, DerivationData.DerivationDataBuilder> {

  public DerivationDataJsonDeserializer() {
    super(DerivationData.kmipTag, DerivationData.encodingType);
  }

  @Override
  protected DerivationData.DerivationDataBuilder createBuilder() {
    return DerivationData.builder();
  }

  @Override
  protected void setValue(DerivationData.DerivationDataBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected DerivationData build(DerivationData.DerivationDataBuilder builder) {
    return builder.build();
  }
}
