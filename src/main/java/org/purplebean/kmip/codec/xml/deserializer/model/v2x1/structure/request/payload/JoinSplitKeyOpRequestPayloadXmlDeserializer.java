package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.Attributes;
import org.purplebean.kmip.model.v2x1.structure.request.payload.JoinSplitKeyOpRequestPayload;

/**
 * XML deserializer for {@link JoinSplitKeyOpRequestPayload}.
 */
public class JoinSplitKeyOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<JoinSplitKeyOpRequestPayload,
        JoinSplitKeyOpRequestPayload.JoinSplitKeyOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link JoinSplitKeyOpRequestPayloadXmlDeserializer}.
   */
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