package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import lombok.Getter;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.ItemType;

public class ItemTypeBenchmarkSubject extends KmipBenchmarkSubject<ItemType> {

    @Getter
    private KmipSpec spec = KmipSpec.V2_1;

    public ItemTypeBenchmarkSubject() throws Exception {
        ItemType subject = ItemType.Standard.STRUCTURE.inst();
        initialize(subject, ItemType.class);
    }

    @Override
    public String name() {
        return "ItemType";
    }
}