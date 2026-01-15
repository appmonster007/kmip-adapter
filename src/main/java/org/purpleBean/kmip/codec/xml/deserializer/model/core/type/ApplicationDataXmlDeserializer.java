package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.ApplicationData;

public class ApplicationDataXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ApplicationData, String> {

    public ApplicationDataXmlDeserializer() {
        super(ApplicationData.kmipTag, ApplicationData.encodingType, String.class, value -> ApplicationData.builder().value(value).build());
    }
}