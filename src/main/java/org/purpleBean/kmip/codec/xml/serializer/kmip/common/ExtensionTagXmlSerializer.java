package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.ExtensionTag;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class ExtensionTagXmlSerializer extends AbstractKmipXmlSerializer<ExtensionTag, Integer> {

    public ExtensionTagXmlSerializer() {
        super(ExtensionTag::getValue);
    }
}