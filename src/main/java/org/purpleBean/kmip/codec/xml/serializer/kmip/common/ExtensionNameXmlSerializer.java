package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.ExtensionName;

public class ExtensionNameXmlSerializer extends AbstractKmipXmlSerializer<ExtensionName, String> {

    public ExtensionNameXmlSerializer() {
        super(ExtensionName::getValue);
    }
}