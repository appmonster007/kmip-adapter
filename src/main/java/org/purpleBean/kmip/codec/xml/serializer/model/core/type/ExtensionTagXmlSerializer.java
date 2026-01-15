package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.ExtensionTag;

public class ExtensionTagXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ExtensionTag, Integer> {

    public ExtensionTagXmlSerializer() {
        super(ExtensionTag::getValue);
    }
}