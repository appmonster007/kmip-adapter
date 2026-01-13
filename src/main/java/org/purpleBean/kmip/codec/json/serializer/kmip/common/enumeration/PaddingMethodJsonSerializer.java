package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.PaddingMethod;

public class PaddingMethodJsonSerializer extends AbstractKmipDataTypeJsonSerializer<PaddingMethod, String> {

    public PaddingMethodJsonSerializer() {
        super(PaddingMethod::getDescription);
    }
}