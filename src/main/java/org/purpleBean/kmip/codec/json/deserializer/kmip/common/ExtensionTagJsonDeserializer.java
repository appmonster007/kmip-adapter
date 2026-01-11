package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.ExtensionTag;

public class ExtensionTagJsonDeserializer extends AbstractKmipJsonDeserializer<ExtensionTag, Integer> {

    public ExtensionTagJsonDeserializer() {
        super(ExtensionTag.kmipTag, ExtensionTag.encodingType, Integer.class, value -> ExtensionTag.builder().value(value).build());
    }
}