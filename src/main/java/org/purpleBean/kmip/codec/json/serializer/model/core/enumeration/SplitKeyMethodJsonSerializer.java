package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyMethod;

public class SplitKeyMethodJsonSerializer extends AbstractKmipDataTypeJsonSerializer<SplitKeyMethod, String> {

    public SplitKeyMethodJsonSerializer() {
        super(SplitKeyMethod::getDescription);
    }
}