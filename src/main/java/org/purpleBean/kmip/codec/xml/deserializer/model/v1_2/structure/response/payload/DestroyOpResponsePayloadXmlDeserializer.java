package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.DestroyOpResponsePayload;

public class DestroyOpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<DestroyOpResponsePayload,
        DestroyOpResponsePayload.DestroyOpResponsePayloadBuilder> {

  public DestroyOpResponsePayloadXmlDeserializer() {
    super(DestroyOpResponsePayload.kmipTag, DestroyOpResponsePayload.encodingType);
  }

  @Override
  protected DestroyOpResponsePayload.DestroyOpResponsePayloadBuilder createBuilder() {
    return DestroyOpResponsePayload.builder();
  }

  @Override
  protected void setValue(DestroyOpResponsePayload.DestroyOpResponsePayloadBuilder builder,
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
  protected DestroyOpResponsePayload build(
      DestroyOpResponsePayload.DestroyOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
