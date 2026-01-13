package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.ExtensionTag;

public class ExtensionTagXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ExtensionTag, Integer> {

    public ExtensionTagXmlDeserializer() {
        super(ExtensionTag.kmipTag, ExtensionTag.encodingType, Integer.class, value -> ExtensionTag.builder().value(value).build());
    }
}