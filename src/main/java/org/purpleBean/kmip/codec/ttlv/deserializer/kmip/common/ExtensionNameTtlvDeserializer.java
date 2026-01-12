package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.ExtensionName;

public class ExtensionNameTtlvDeserializer extends AbstractKmipTtlvDeserializer<ExtensionName, String> {

    public ExtensionNameTtlvDeserializer() {
        super(ExtensionName.kmipTag, ExtensionName.encodingType, String.class, value -> ExtensionName.builder().value(value).build());
    }
}