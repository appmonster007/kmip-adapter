package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.ApplicationData;

public class ApplicationDataXmlDeserializer extends AbstractKmipXmlDeserializer<ApplicationData, String> {

    public ApplicationDataXmlDeserializer() {
        super(ApplicationData.kmipTag, ApplicationData.encodingType, String.class, value -> ApplicationData.builder().value(value).build());
    }
}