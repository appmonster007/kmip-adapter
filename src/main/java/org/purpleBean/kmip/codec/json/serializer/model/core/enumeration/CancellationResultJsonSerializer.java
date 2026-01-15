package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.CancellationResult;

public class CancellationResultJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CancellationResult, String> {

    public CancellationResultJsonSerializer() {
        super(CancellationResult::getDescription);
    }
}