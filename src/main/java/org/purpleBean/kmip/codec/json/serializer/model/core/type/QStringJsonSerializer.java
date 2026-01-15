package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.QString;

import java.nio.ByteBuffer;

public class QStringJsonSerializer extends AbstractKmipDataTypeJsonSerializer<QString, ByteBuffer> {

    public QStringJsonSerializer() {
        super(QString::getValue);
    }
}