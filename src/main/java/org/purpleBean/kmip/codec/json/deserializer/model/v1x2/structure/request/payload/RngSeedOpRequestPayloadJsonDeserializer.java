package org.purpleBean.kmip.codec.json.deserializer.model.v1x2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.RngSeedOpRequestPayload;

public class RngSeedOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<RngSeedOpRequestPayload,
        RngSeedOpRequestPayload.RngSeedOpRequestPayloadBuilder> {

  public RngSeedOpRequestPayloadJsonDeserializer() {
    super(RngSeedOpRequestPayload.kmipTag, RngSeedOpRequestPayload.encodingType);
  }

  @Override
  protected RngSeedOpRequestPayload.RngSeedOpRequestPayloadBuilder createBuilder() {
    return RngSeedOpRequestPayload.builder();
  }

  @Override
  protected void setValue(RngSeedOpRequestPayload.RngSeedOpRequestPayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    if (nodeTag.equals(KmipTag.Standard.DATA)) {
      builder.data(ctxt.readValue(p, DataByteString.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected RngSeedOpRequestPayload build(
      RngSeedOpRequestPayload.RngSeedOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
