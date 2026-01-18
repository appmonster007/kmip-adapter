package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.DerivationData;

import java.nio.ByteBuffer;

public class DerivationDataJsonSerializer extends AbstractKmipDataTypeJsonSerializer<DerivationData, ByteBuffer> {

    public DerivationDataJsonSerializer() {
        super(DerivationData::getValue);
    }
}