package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.Offset;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.ReKeyOpRequestPayload;

public class ReKeyOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ReKeyOpRequestPayload,
        ReKeyOpRequestPayload.ReKeyOpRequestPayloadBuilder> {

  public ReKeyOpRequestPayloadXmlDeserializer() {
    super(ReKeyOpRequestPayload.kmipTag, ReKeyOpRequestPayload.encodingType);
  }

  @Override
  protected ReKeyOpRequestPayload.ReKeyOpRequestPayloadBuilder createBuilder() {
    return ReKeyOpRequestPayload.builder();
  }

  @Override
  protected void setValue(ReKeyOpRequestPayload.ReKeyOpRequestPayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.OFFSET -> builder.offset(ctxt.readValue(p, Offset.class));
      case KmipTag.Standard.ATTRIBUTES -> builder.attributes(ctxt.readValue(p, Attributes.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ReKeyOpRequestPayload build(
      ReKeyOpRequestPayload.ReKeyOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}