package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.ApplicationData;

public class ApplicationDataXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ApplicationData, String> {

    public ApplicationDataXmlSerializer() {
        super(ApplicationData::getValue);
    }
}