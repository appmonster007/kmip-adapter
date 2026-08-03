package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.ExtensionInformation;
import org.purplebean.kmip.model.core.type.ExtensionName;
import org.purplebean.kmip.model.core.type.ExtensionTag;
import org.purplebean.kmip.model.core.type.ExtensionType;

/**
 * TTLV deserializer for {@link ExtensionInformation}.
 */
public class ExtensionInformationTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ExtensionInformation,
        ExtensionInformation.ExtensionInformationBuilder> {

  /**
   * Constructs a new {@link ExtensionInformationTtlvDeserializer}.
   */
  public ExtensionInformationTtlvDeserializer() {
    super(ExtensionInformation.kmipTag, ExtensionInformation.encodingType);
  }

  @Override
  protected ExtensionInformation.ExtensionInformationBuilder createBuilder() {
    return ExtensionInformation.builder();
  }

  @Override
  protected void setValue(ExtensionInformation.ExtensionInformationBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.EXTENSION_NAME ->
          builder.extensionName(mapper.readValue(p, ExtensionName.class));
      case KmipTag.Standard.EXTENSION_TAG ->
          builder.extensionTag(mapper.readValue(p, ExtensionTag.class));
      case KmipTag.Standard.EXTENSION_TYPE ->
          builder.extensionType(mapper.readValue(p, ExtensionType.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ExtensionInformation build(ExtensionInformation.ExtensionInformationBuilder builder) {
    return builder.build();
  }
}