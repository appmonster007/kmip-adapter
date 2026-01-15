package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.ExtensionType;

public class ExtensionTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ExtensionType, Integer> {

    public ExtensionTypeTtlvDeserializer() {
        super(ExtensionType.kmipTag, ExtensionType.encodingType, Integer.class, value -> ExtensionType.builder().value(value).build());
    }
}