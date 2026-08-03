package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.DiscoverVersionsOpRequestPayload;

public class DiscoverVersionsOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<DiscoverVersionsOpRequestPayload,
        DiscoverVersionsOpRequestPayload.DiscoverVersionsOpRequestPayloadBuilder> {

  public DiscoverVersionsOpRequestPayloadXmlDeserializer() {
    super(DiscoverVersionsOpRequestPayload.kmipTag, DiscoverVersionsOpRequestPayload.encodingType);
  }

  @Override
  protected DiscoverVersionsOpRequestPayload.DiscoverVersionsOpRequestPayloadBuilder createBuilder() {
    return DiscoverVersionsOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      DiscoverVersionsOpRequestPayload.DiscoverVersionsOpRequestPayloadBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);

    if (nodeTag.equals(KmipTag.Standard.PROTOCOL_VERSION)) {
      builder.protocolVersion(ctxt.readValue(p, ProtocolVersion.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DiscoverVersionsOpRequestPayload build(
      DiscoverVersionsOpRequestPayload.DiscoverVersionsOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
