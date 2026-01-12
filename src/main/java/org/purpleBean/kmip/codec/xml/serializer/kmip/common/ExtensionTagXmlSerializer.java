package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.ExtensionTag;

public class ExtensionTagXmlSerializer extends AbstractKmipXmlSerializer<ExtensionTag, Integer> {

    public ExtensionTagXmlSerializer() {
        super(ExtensionTag::getValue);
    }
}