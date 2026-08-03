package org.purplebean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.ExtensionInformation;
import org.purplebean.kmip.model.core.type.ExtensionName;
import org.purplebean.kmip.model.core.type.ExtensionTag;
import org.purplebean.kmip.model.core.type.ExtensionType;

/**
 * JSON deserializer for {@link ExtensionInformation}.
 */
public class ExtensionInformationJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ExtensionInformation,
        ExtensionInformation.ExtensionInformationBuilder> {

  /**
   * Constructs a new {@link ExtensionInformationJsonDeserializer}.
   */
  public ExtensionInformationJsonDeserializer() {
    super(ExtensionInformation.kmipTag, ExtensionInformation.encodingType);
  }

  @Override
  protected ExtensionInformation.ExtensionInformationBuilder createBuilder() {
    return ExtensionInformation.builder();
  }

  @Override
  protected void setValue(ExtensionInformation.ExtensionInformationBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.EXTENSION_NAME ->
          builder.extensionName(ctxt.readValue(p, ExtensionName.class));
      case KmipTag.Standard.EXTENSION_TAG ->
          builder.extensionTag(ctxt.readValue(p, ExtensionTag.class));
      case KmipTag.Standard.EXTENSION_TYPE ->
          builder.extensionType(ctxt.readValue(p, ExtensionType.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ExtensionInformation build(ExtensionInformation.ExtensionInformationBuilder builder) {
    return builder.build();
  }
}
