package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.DataEnumeration;

public class DataEnumerationJsonSerializer extends AbstractKmipJsonSerializer<DataEnumeration, String> {

    public DataEnumerationJsonSerializer() {
        super(DataEnumeration::getDescription);
    }
}