package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.ObtainLeaseOpRequestPayload;

public class ObtainLeaseOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ObtainLeaseOpRequestPayload,
        ObtainLeaseOpRequestPayload.ObtainLeaseOpRequestPayloadBuilder> {

  public ObtainLeaseOpRequestPayloadJsonDeserializer() {
    super(ObtainLeaseOpRequestPayload.kmipTag, ObtainLeaseOpRequestPayload.encodingType);
  }

  @Override
  protected ObtainLeaseOpRequestPayload.ObtainLeaseOpRequestPayloadBuilder createBuilder() {
    return ObtainLeaseOpRequestPayload.builder();
  }

  @Override
  protected void setValue(ObtainLeaseOpRequestPayload.ObtainLeaseOpRequestPayloadBuilder builder,
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
  protected ObtainLeaseOpRequestPayload build(
      ObtainLeaseOpRequestPayload.ObtainLeaseOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
