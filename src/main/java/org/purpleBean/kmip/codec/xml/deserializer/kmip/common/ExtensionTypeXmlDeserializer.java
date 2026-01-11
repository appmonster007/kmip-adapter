package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.ExtensionType;

public class ExtensionTypeXmlDeserializer extends AbstractKmipXmlDeserializer<ExtensionType, Integer> {

    public ExtensionTypeXmlDeserializer() {
        super(ExtensionType.kmipTag, ExtensionType.encodingType, Integer.class, value -> ExtensionType.builder().value(value).build());
    }
}