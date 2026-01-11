package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.CancellationResult;

public class CancellationResultJsonDeserializer extends AbstractKmipJsonDeserializer<CancellationResult, String> {

    public CancellationResultJsonDeserializer() {
        super(CancellationResult.kmipTag, CancellationResult.encodingType, String.class, value -> new CancellationResult(CancellationResult.fromName(value)));
    }
}