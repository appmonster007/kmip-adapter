package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.QString;

import java.nio.ByteBuffer;

public class QStringTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<QString, ByteBuffer> {

    public QStringTtlvSerializer() {
        super(QString::getValue);
    }
}