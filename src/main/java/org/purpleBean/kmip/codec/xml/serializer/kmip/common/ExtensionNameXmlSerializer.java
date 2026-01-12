package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.ExtensionName;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class ExtensionNameXmlSerializer extends AbstractKmipXmlSerializer<ExtensionName, String> {

    public ExtensionNameXmlSerializer() {
        super(ExtensionName::getValue);
    }
}