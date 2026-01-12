package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.CancellationResult;

public class CancellationResultJsonSerializer extends AbstractKmipJsonSerializer<CancellationResult, String> {

    public CancellationResultJsonSerializer() {
        super(CancellationResult::getDescription);
    }
}