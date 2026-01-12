package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.ApplicationData;

public class ApplicationDataXmlSerializer extends AbstractKmipXmlSerializer<ApplicationData, String> {

    public ApplicationDataXmlSerializer() {
        super(ApplicationData::getValue);
    }
}