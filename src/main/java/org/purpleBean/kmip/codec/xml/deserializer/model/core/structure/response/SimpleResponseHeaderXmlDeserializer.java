package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponseHeader;

public class SimpleResponseHeaderXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<SimpleResponseHeader,
        SimpleResponseHeader.SimpleResponseHeaderBuilder> {

  public SimpleResponseHeaderXmlDeserializer() {
    super(SimpleResponseHeader.kmipTag, SimpleResponseHeader.encodingType);
  }

  @Override
  protected SimpleResponseHeader.SimpleResponseHeaderBuilder createBuilder() {
    return SimpleResponseHeader.builder();
  }

  @Override
  protected void setValue(SimpleResponseHeader.SimpleResponseHeaderBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.PROTOCOL_VERSION ->
          builder.protocolVersion(ctxt.readValue(p, ProtocolVersion.class));
      default -> {
        while (p.nextToken() != JsonToken.END_OBJECT) ;
      }
    }
  }

  @Override
  protected SimpleResponseHeader build(SimpleResponseHeader.SimpleResponseHeaderBuilder builder) {
    return builder.build();
  }
}
