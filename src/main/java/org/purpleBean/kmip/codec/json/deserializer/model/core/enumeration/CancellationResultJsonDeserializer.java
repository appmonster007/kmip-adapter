package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CancellationResult;

public class CancellationResultJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CancellationResult, String> {

    public CancellationResultJsonDeserializer() {
        super(CancellationResult.kmipTag, CancellationResult.encodingType, String.class, value -> new CancellationResult(CancellationResult.fromName(value)));
    }
}