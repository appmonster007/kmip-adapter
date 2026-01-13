package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.ApplicationData;

public class ApplicationDataJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ApplicationData, String> {

    public ApplicationDataJsonDeserializer() {
        super(ApplicationData.kmipTag, ApplicationData.encodingType, String.class, value -> ApplicationData.builder().value(value).build());
    }
}