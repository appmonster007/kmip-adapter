package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

public class UniqueIdentifierXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<UniqueIdentifier,
        UniqueIdentifier.UniqueIdentifierBuilder> {

  public UniqueIdentifierXmlDeserializer() {
    super(UniqueIdentifier.kmipTag, UniqueIdentifier.encodingType);
  }

  @Override
  protected UniqueIdentifier.UniqueIdentifierBuilder createBuilder() {
    return UniqueIdentifier.builder();
  }

  @Override
  protected String getType(JsonNode node, DeserializationContext ctxt,
                           UniqueIdentifier.UniqueIdentifierBuilder builder) throws IOException {
    // In KMIP 2.1+, UniqueIdentifier may appear as TextString, Enumeration (e.g. "IDPlaceholder",
    // batch-item / ID-Placeholder references — same string shape as the separate
    // model.v2_1.enumeration.UniqueIdentifier class used for generic KmipDataType-dispatch
    // contexts, but accepted here too since this class is the fixed Java type of most
    // UniqueIdentifier fields), or Integer (batch item index). KMIP 3.0 §4.68 additionally
    // allows Identifier/Reference/NameReference — distinct TTLV Item Types (bytes 0x0C-0x0E)
    // with the same underlying character-sequence shape as TextString.
    JsonNode typeNode = node.get("type");
    if (typeNode != null && typeNode.isTextual()) {
      String type = typeNode.asText();
      if (EncodingType.TEXT_STRING
          .getDescription()
          .equals(type)
          || EncodingType.ENUMERATION
          .getDescription()
          .equals(type)
          || EncodingType.INTEGER
          .getDescription()
          .equals(type)
          || EncodingType.IDENTIFIER
          .getDescription()
          .equals(type)
          || EncodingType.REFERENCE
          .getDescription()
          .equals(type)
          || EncodingType.NAME_REFERENCE
          .getDescription()
          .equals(type)) {
        return type;
      }
    }
    return super.getType(node, ctxt, builder);
  }

  @Override
  protected void setValue(UniqueIdentifier.UniqueIdentifierBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
    EncodingType
        .fromName(type)
        .ifPresent(builder::sourceEncoding);
  }

  @Override
  protected UniqueIdentifier build(UniqueIdentifier.UniqueIdentifierBuilder builder) {
    return builder.build();
  }
}
