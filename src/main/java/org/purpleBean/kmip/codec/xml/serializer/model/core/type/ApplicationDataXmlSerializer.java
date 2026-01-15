package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.ApplicationData;

public class ApplicationDataXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ApplicationData, String> {

    public ApplicationDataXmlSerializer() {
        super(ApplicationData::getValue);
    }
}