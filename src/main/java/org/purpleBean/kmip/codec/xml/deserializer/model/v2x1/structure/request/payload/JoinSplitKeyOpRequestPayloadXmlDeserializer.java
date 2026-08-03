package org.purpleBean.kmip.codec.xml.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.Attributes;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.JoinSplitKeyOpRequestPayload;

public class JoinSplitKeyOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<JoinSplitKeyOpRequestPayload,
        JoinSplitKeyOpRequestPayload.JoinSplitKeyOpRequestPayloadBuilder> {

  public JoinSplitKeyOpRequestPayloadXmlDeserializer() {
    super(JoinSplitKeyOpRequestPayload.kmipTag, JoinSplitKeyOpRequestPayload.encodingType);
  }

  @Override
  protected JoinSplitKeyOpRequestPayload.JoinSplitKeyOpRequestPayloadBuilder createBuilder() {
    return JoinSplitKeyOpRequestPayload.builder();
  }

  @Override
  protected void setValue(JoinSplitKeyOpRequestPayload.JoinSplitKeyOpRequestPayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(ctxt.readValue(p, ObjectType.class));
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.ATTRIBUTES -> builder.attributes(ctxt.readValue(p, Attributes.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected JoinSplitKeyOpRequestPayload build(
      JoinSplitKeyOpRequestPayload.JoinSplitKeyOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}