package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.QString;

import java.nio.ByteBuffer;

public class QStringJsonSerializer extends AbstractKmipDataTypeJsonSerializer<QString, ByteBuffer> {

    public QStringJsonSerializer() {
        super(QString::getValue);
    }
}