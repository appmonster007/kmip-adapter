package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.ExtensionInformation;
import org.purpleBean.kmip.model.core.type.ExtensionName;
import org.purpleBean.kmip.model.core.type.ExtensionTag;
import org.purpleBean.kmip.model.core.type.ExtensionType;

import java.io.IOException;

public class ExtensionInformationXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ExtensionInformation, ExtensionInformation.ExtensionInformationBuilder> {

    public ExtensionInformationXmlDeserializer() {
        super(ExtensionInformation.kmipTag, ExtensionInformation.encodingType);
    }

    @Override
    protected ExtensionInformation.ExtensionInformationBuilder createBuilder() {
        return ExtensionInformation.builder();
    }

    @Override
    protected void setValue(ExtensionInformation.ExtensionInformationBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.EXTENSION_NAME -> builder.extensionName(ctxt.readValue(p, ExtensionName.class));
            case KmipTag.Standard.EXTENSION_TAG -> builder.extensionTag(ctxt.readValue(p, ExtensionTag.class));
            case KmipTag.Standard.EXTENSION_TYPE -> builder.extensionType(ctxt.readValue(p, ExtensionType.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ExtensionInformation build(ExtensionInformation.ExtensionInformationBuilder builder) {
        return builder.build();
    }
}