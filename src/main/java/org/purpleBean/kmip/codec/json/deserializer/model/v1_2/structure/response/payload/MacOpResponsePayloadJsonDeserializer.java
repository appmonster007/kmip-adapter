package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.MacData;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.MacOpResponsePayload;

public class MacOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<MacOpResponsePayload,
        MacOpResponsePayload.MacOpResponsePayloadBuilder> {

  public MacOpResponsePayloadJsonDeserializer() {
    super(MacOpResponsePayload.kmipTag, MacOpResponsePayload.encodingType);
  }

  @Override
  protected MacOpResponsePayload.MacOpResponsePayloadBuilder createBuilder() {
    return MacOpResponsePayload.builder();
  }

  @Override
  protected void setValue(MacOpResponsePayload.MacOpResponsePayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.MAC_DATA -> builder.macData(ctxt.readValue(p, MacData.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected MacOpResponsePayload build(MacOpResponsePayload.MacOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
