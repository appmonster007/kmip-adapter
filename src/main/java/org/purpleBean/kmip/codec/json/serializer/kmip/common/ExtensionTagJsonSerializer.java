package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.ExtensionTag;

public class ExtensionTagJsonSerializer extends AbstractKmipJsonSerializer<ExtensionTag, Integer> {

    public ExtensionTagJsonSerializer() {
        super(ExtensionTag::getValue);
    }
}