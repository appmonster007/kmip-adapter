package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.PaddingMethod;

public class PaddingMethodJsonSerializer extends AbstractKmipJsonSerializer<PaddingMethod, String> {

    public PaddingMethodJsonSerializer() {
        super(PaddingMethod::getDescription);
    }
}