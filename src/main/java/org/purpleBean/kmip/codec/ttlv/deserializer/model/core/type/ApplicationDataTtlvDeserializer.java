package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.ApplicationData;

public class ApplicationDataTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ApplicationData, String> {

    public ApplicationDataTtlvDeserializer() {
        super(ApplicationData.kmipTag, ApplicationData.encodingType, String.class, value -> ApplicationData.builder().value(value).build());
    }
}