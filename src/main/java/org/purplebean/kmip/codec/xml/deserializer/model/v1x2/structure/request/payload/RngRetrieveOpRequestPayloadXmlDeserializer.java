package org.purplebean.kmip.codec.xml.deserializer.model.v1x2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.DataLength;
import org.purplebean.kmip.model.v1x2.structure.request.payload.RngRetrieveOpRequestPayload;

/**
 * XML deserializer for {@link RngRetrieveOpRequestPayload}.
 */
public class RngRetrieveOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<RngRetrieveOpRequestPayload,
        RngRetrieveOpRequestPayload.RngRetrieveOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link RngRetrieveOpRequestPayloadXmlDeserializer}.
   */
  public RngRetrieveOpRequestPayloadXmlDeserializer() {
    super(RngRetrieveOpRequestPayload.kmipTag, RngRetrieveOpRequestPayload.encodingType);
  }

  @Override
  protected RngRetrieveOpRequestPayload.RngRetrieveOpRequestPayloadBuilder createBuilder() {
    return RngRetrieveOpRequestPayload.builder();
  }

  @Override
  protected void setValue(RngRetrieveOpRequestPayload.RngRetrieveOpRequestPayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);

    if (nodeTag.equals(KmipTag.Standard.DATA_LENGTH)) {
      builder.dataLength(ctxt.readValue(p, DataLength.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected RngRetrieveOpRequestPayload build(
      RngRetrieveOpRequestPayload.RngRetrieveOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
