package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.EncodingOption;

public class EncodingOptionJsonSerializer extends AbstractKmipDataTypeJsonSerializer<EncodingOption, String> {

    public EncodingOptionJsonSerializer() {
        super(EncodingOption::getDescription);
    }
}