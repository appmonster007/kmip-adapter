package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.DerivationData;

import java.nio.ByteBuffer;

public class DerivationDataXmlSerializer extends AbstractKmipDataTypeXmlSerializer<DerivationData, ByteBuffer> {

    public DerivationDataXmlSerializer() {
        super(DerivationData::getValue);
    }
}