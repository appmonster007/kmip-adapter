package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.ExtensionName;

public class ExtensionNameXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ExtensionName, String> {

    public ExtensionNameXmlDeserializer() {
        super(ExtensionName.kmipTag, ExtensionName.encodingType, String.class, value -> ExtensionName.builder().value(value).build());
    }
}