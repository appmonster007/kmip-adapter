package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.ExtensionInformation;
import org.purpleBean.kmip.model.core.type.ExtensionName;
import org.purpleBean.kmip.model.core.type.ExtensionTag;
import org.purpleBean.kmip.model.core.type.ExtensionType;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ExtensionInformationTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<ExtensionInformation, ExtensionInformation.ExtensionInformationBuilder> {

    public ExtensionInformationTtlvDeserializer() {
        super(ExtensionInformation.kmipTag);
    }

    @Override
    protected ExtensionInformation.ExtensionInformationBuilder createBuilder() {
        return ExtensionInformation.builder();
    }

    @Override
    protected void setValue(ExtensionInformation.ExtensionInformationBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.EXTENSION_NAME -> builder.extensionName(mapper.readValue(p, ExtensionName.class));
            case KmipTag.Standard.EXTENSION_TAG -> builder.extensionTag(mapper.readValue(p, ExtensionTag.class));
            case KmipTag.Standard.EXTENSION_TYPE -> builder.extensionType(mapper.readValue(p, ExtensionType.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ExtensionInformation build(ExtensionInformation.ExtensionInformationBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return ExtensionInformation.encodingType;
    }
}