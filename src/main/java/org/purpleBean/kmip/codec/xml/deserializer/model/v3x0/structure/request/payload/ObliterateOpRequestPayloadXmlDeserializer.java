package org.purpleBean.kmip.codec.xml.deserializer.model.v3x0.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.request.payload.ObliterateOpRequestPayload;

public class ObliterateOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ObliterateOpRequestPayload,
        ObliterateOpRequestPayload.ObliterateOpRequestPayloadBuilder> {

  public ObliterateOpRequestPayloadXmlDeserializer() {
    super(ObliterateOpRequestPayload.kmipTag, ObliterateOpRequestPayload.encodingType);
  }

  @Override
  protected ObliterateOpRequestPayload.ObliterateOpRequestPayloadBuilder createBuilder() {
    return ObliterateOpRequestPayload.builder();
  }

  @Override
  protected void setValue(ObliterateOpRequestPayload.ObliterateOpRequestPayloadBuilder builder,
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
  protected ObliterateOpRequestPayload build(
      ObliterateOpRequestPayload.ObliterateOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}