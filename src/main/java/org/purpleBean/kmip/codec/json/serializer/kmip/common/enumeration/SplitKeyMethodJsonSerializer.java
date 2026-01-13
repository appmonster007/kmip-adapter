package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.SplitKeyMethod;

public class SplitKeyMethodJsonSerializer extends AbstractKmipDataTypeJsonSerializer<SplitKeyMethod, String> {

    public SplitKeyMethodJsonSerializer() {
        super(SplitKeyMethod::getDescription);
    }
}