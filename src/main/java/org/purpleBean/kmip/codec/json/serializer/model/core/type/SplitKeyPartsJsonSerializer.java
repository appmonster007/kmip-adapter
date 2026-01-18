package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.SplitKeyParts;

public class SplitKeyPartsJsonSerializer extends AbstractKmipDataTypeJsonSerializer<SplitKeyParts, Integer> {

    public SplitKeyPartsJsonSerializer() {
        super(SplitKeyParts::getValue);
    }
}