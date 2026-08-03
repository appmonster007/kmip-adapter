package org.purplebean.kmip.codec.xml.deserializer.model.v1x2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.ActivateOpRequestPayload;

/**
 * XML deserializer for {@link ActivateOpRequestPayload}.
 */
public class ActivateOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ActivateOpRequestPayload,
        ActivateOpRequestPayload.ActivateOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link ActivateOpRequestPayloadXmlDeserializer}.
   */
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
