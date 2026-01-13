package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.ExtensionTag;

public class ExtensionTagJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ExtensionTag, Integer> {

    public ExtensionTagJsonSerializer() {
        super(ExtensionTag::getValue);
    }
}