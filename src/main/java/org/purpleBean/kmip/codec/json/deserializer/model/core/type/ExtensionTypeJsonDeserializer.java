package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.ExtensionType;

public class ExtensionTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ExtensionType, Integer> {

    public ExtensionTypeJsonDeserializer() {
        super(ExtensionType.kmipTag, ExtensionType.encodingType, Integer.class, value -> ExtensionType.builder().value(value).build());
    }
}