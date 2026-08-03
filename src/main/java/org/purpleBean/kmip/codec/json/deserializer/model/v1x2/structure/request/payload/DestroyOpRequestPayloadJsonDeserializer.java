package org.purplebean.kmip.codec.json.deserializer.model.v1x2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.DestroyOpRequestPayload;

public class DestroyOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<DestroyOpRequestPayload,
        DestroyOpRequestPayload.DestroyOpRequestPayloadBuilder> {

  public DestroyOpRequestPayloadJsonDeserializer() {
    super(DestroyOpRequestPayload.kmipTag, DestroyOpRequestPayload.encodingType);
  }

  @Override
  protected DestroyOpRequestPayload.DestroyOpRequestPayloadBuilder createBuilder() {
    return DestroyOpRequestPayload.builder();
  }

  @Override
  protected void setValue(DestroyOpRequestPayload.DestroyOpRequestPayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    if (nodeTag.equals(KmipTag.Standard.UNIQUE_IDENTIFIER)) {
      builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DestroyOpRequestPayload build(
      DestroyOpRequestPayload.DestroyOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
