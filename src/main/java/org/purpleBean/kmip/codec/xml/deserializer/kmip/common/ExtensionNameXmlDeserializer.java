package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.ExtensionName;

public class ExtensionNameXmlDeserializer extends AbstractKmipXmlDeserializer<ExtensionName, String> {

    public ExtensionNameXmlDeserializer() {
        super(ExtensionName.kmipTag, ExtensionName.encodingType, String.class, value -> ExtensionName.builder().value(value).build());
    }
}