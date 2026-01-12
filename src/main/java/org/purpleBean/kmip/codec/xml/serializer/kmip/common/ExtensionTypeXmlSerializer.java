package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.ExtensionType;

public class ExtensionTypeXmlSerializer extends AbstractKmipXmlSerializer<ExtensionType, Integer> {

    public ExtensionTypeXmlSerializer() {
        super(ExtensionType::getValue);
    }
}