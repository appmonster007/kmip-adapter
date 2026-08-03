package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.ActivateOpRequestPayload;

public class ActivateOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ActivateOpRequestPayload,
        ActivateOpRequestPayload.ActivateOpRequestPayloadBuilder> {

  public ActivateOpRequestPayloadXmlDeserializer() {
    super(ActivateOpRequestPayload.kmipTag, ActivateOpRequestPayload.encodingType);
  }

  @Override
  protected ActivateOpRequestPayload.ActivateOpRequestPayloadBuilder createBuilder() {
    return ActivateOpRequestPayload.builder();
  }

  @Override
  protected void setValue(ActivateOpRequestPayload.ActivateOpRequestPayloadBuilder builder,
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
  protected ActivateOpRequestPayload build(
      ActivateOpRequestPayload.ActivateOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
