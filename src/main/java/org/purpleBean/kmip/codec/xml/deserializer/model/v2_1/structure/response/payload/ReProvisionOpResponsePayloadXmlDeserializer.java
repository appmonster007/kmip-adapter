package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.ReProvisionOpResponsePayload;

public class ReProvisionOpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ReProvisionOpResponsePayload,
        ReProvisionOpResponsePayload.ReProvisionOpResponsePayloadBuilder> {

  public ReProvisionOpResponsePayloadXmlDeserializer() {
    super(ReProvisionOpResponsePayload.kmipTag, ReProvisionOpResponsePayload.encodingType);
  }

  @Override
  protected ReProvisionOpResponsePayload.ReProvisionOpResponsePayloadBuilder createBuilder() {
    return ReProvisionOpResponsePayload.builder();
  }

  @Override
  protected void setValue(ReProvisionOpResponsePayload.ReProvisionOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ReProvisionOpResponsePayload build(
      ReProvisionOpResponsePayload.ReProvisionOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}