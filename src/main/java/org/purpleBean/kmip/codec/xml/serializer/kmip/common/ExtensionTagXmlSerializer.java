package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.ExtensionTag;

public class ExtensionTagXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ExtensionTag, Integer> {

    public ExtensionTagXmlSerializer() {
        super(ExtensionTag::getValue);
    }
}