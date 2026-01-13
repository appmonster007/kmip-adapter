package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.ExtensionName;

public class ExtensionNameJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ExtensionName, String> {

    public ExtensionNameJsonDeserializer() {
        super(ExtensionName.kmipTag, ExtensionName.encodingType, String.class, value -> ExtensionName.builder().value(value).build());
    }
}