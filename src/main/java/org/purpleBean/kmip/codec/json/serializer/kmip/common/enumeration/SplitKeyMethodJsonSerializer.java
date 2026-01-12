package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.SplitKeyMethod;

public class SplitKeyMethodJsonSerializer extends AbstractKmipJsonSerializer<SplitKeyMethod, String> {

    public SplitKeyMethodJsonSerializer() {
        super(SplitKeyMethod::getDescription);
    }
}